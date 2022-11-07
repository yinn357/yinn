package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.DictMapper;
import top.yinn.modulars.system.model.dto.DictDTO;
import top.yinn.modulars.system.model.dto.DictInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.DictEntity;
import top.yinn.modulars.system.model.vo.DictVO;


/**
 * 字典表
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictService extends BaseServiceImpl<DictMapper, DictEntity> {


	/**
	 * 后台-分页列表
	 */
	public PageResult<DictVO> list(PageParam pageParam, DictDTO dto) {
		Page<DictEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));

		return PageResult.convert(entityPage, DictVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public DictVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public DictVO getOneById(Long id, boolean checkId) {
		DictEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, DictVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	public Long saveOrUpdate(DictInsertOrUpdateDTO dto) {
		DictEntity entity = BeanUtil.toBean(dto, DictEntity.class);

		this.saveOrUpdate(entity);

		return entity.getId();
	}



    /*
    ----------------------------------------------------------------
                        私有方法 private methods
    ----------------------------------------------------------------
     */


	/**
	 * 构建查询条件 QueryWrapper
	 */
	private Wrapper<DictEntity> buildQueryWrapper(DictDTO dto) {
		return new LambdaQueryWrapper<DictEntity>()
				// 字典编号
				.like(StrUtil.isNotBlank(dto.getTypeCode()), DictEntity::getTypeCode, StrUtil.cleanBlank(dto.getTypeCode()))
				// 字典名称
				.like(StrUtil.isNotBlank(dto.getTypeName()), DictEntity::getTypeName, StrUtil.cleanBlank(dto.getTypeName()))
				// 是否内置 0否  1是
				.eq(ObjectUtil.isNotNull(dto.getIzLock()), DictEntity::getIzLock, dto.getIzLock())
				// 备注
				.like(StrUtil.isNotBlank(dto.getRemark()), DictEntity::getRemark, StrUtil.cleanBlank(dto.getRemark()))
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), DictEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(DictEntity::getCreateTime);
	}
}
