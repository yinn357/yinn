package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.core.utils.StrPool;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.constant.CacheKeyConstant;
import top.yinn.modulars.system.mapper.DictDetailMapper;
import top.yinn.modulars.system.model.dto.DictDetailDTO;
import top.yinn.modulars.system.model.dto.DictDetailInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.DictDetailEntity;
import top.yinn.modulars.system.model.vo.DictDetailVO;
import top.yinn.redis.constant.CacheRegionConstant;

import java.util.Collection;
import java.util.List;


/**
 * 字典表-明细
 *
 * @author Yinn
 */
@CacheConfig(cacheNames = CacheRegionConstant.DICT + StrPool.COLON + CacheKeyConstant.Dict.DICT)
@Slf4j
@Service
@RequiredArgsConstructor
public class DictDetailService extends BaseServiceImpl<DictDetailMapper, DictDetailEntity> {


	/**
	 * 后台-分页列表
	 */
	public PageResult<DictDetailVO> list(PageParam pageParam, DictDetailDTO dto) {
		Page<DictDetailEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));

		return PageResult.convert(entityPage, DictDetailVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public DictDetailVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public DictDetailVO getOneById(Long id, boolean checkId) {
		DictDetailEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, DictDetailVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	@CacheEvict(key = "(#dto.typeCode)")
	public Long saveOrUpdate(DictDetailInsertOrUpdateDTO dto) {
		DictDetailEntity entity = BeanUtil.toBean(dto, DictDetailEntity.class);

		this.saveOrUpdate(entity);

		return entity.getId();
	}

	@Override
	public boolean removeByIds(Collection<?> list) {
		return this.remove(new LambdaQueryWrapper<DictDetailEntity>()
				.eq(DictDetailEntity::getId, list)
		);
	}

	/**
	 * 根据字典类型 code 查询字典详细
	 *
	 * @param typeCode 字典类型
	 * @return list
	 */
	@Cacheable(key = "(#typeCode)")
	public List<DictDetailVO> listByTypeCode(String typeCode) {
		List<DictDetailEntity> list = this.list(DictDetailEntity::getTypeCode, typeCode);
		return BeanUtil.copyToList(list, DictDetailVO.class);
	}


    /*
    ----------------------------------------------------------------
                        私有方法 private methods
    ----------------------------------------------------------------
     */


	/**
	 * 构建查询条件 QueryWrapper
	 */
	private Wrapper<DictDetailEntity> buildQueryWrapper(DictDetailDTO dto) {
		return new LambdaQueryWrapper<DictDetailEntity>()
				// 类型ID
				.like(StrUtil.isNotBlank(dto.getTypeId()), DictDetailEntity::getTypeId, StrUtil.cleanBlank(dto.getTypeId()))
				// 类型code 冗余字段
				.like(StrUtil.isNotBlank(dto.getTypeCode()), DictDetailEntity::getTypeCode, StrUtil.cleanBlank(dto.getTypeCode()))
				// 字典名称
				.like(StrUtil.isNotBlank(dto.getDictName()), DictDetailEntity::getDictName, StrUtil.cleanBlank(dto.getDictName()))
				// 字典值code #非必填,用于部分特殊配置
				.like(StrUtil.isNotBlank(dto.getDictCode()), DictDetailEntity::getDictCode, StrUtil.cleanBlank(dto.getDictCode()))
				// 字典值
				.like(StrUtil.isNotBlank(dto.getDictValue()), DictDetailEntity::getDictValue, StrUtil.cleanBlank(dto.getDictValue()))
				// 是否内置 0否  1是
				.eq(ObjectUtil.isNotNull(dto.getIzLock()), DictDetailEntity::getIzLock, dto.getIzLock())
				// 排序
				.eq(ObjectUtil.isNotNull(dto.getSortNo()), DictDetailEntity::getSortNo, dto.getSortNo())
				// 备注
				.like(StrUtil.isNotBlank(dto.getRemark()), DictDetailEntity::getRemark, StrUtil.cleanBlank(dto.getRemark()))
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), DictDetailEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(DictDetailEntity::getCreateTime);
	}
}
