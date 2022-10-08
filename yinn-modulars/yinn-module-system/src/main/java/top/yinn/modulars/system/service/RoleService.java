package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.base.PageParam;
import top.yinn.core.base.PageResult;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.RoleMapper;
import top.yinn.modulars.system.model.dto.RoleDTO;
import top.yinn.modulars.system.model.dto.RoleInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.RoleEntity;
import top.yinn.modulars.system.model.vo.RoleVO;

import java.util.List;
import java.util.Set;


/**
 * 角色
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService extends BaseServiceImpl<RoleMapper, RoleEntity> {


	private final UserRoleService userRoleService;

	/**
	 * 后台-分页列表
	 */
	public PageResult<RoleVO> list(PageParam pageParam, RoleDTO dto) {
		Page<RoleEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));

		return PageResult.convert(entityPage, RoleVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public RoleVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public RoleVO getOneById(Long id, boolean checkId) {
		RoleEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, RoleVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	public Long saveOrUpdate(RoleInsertOrUpdateDTO dto) {
		RoleEntity entity = BeanUtil.toBean(dto, RoleEntity.class);

		this.saveOrUpdate(entity);

		return entity.getId();
	}

	/**
	 * 取用户ID拥有角色对应的
	 *
	 * @param userId 用户ID
	 * @return 失败返回空列表
	 */
	public List<RoleEntity> listByUserId(Long userId) {
		Set<Long> roleIds = userRoleService.listRoleIdsByUserId(userId);
		if (CollUtil.isEmpty(roleIds)) {
			return CollUtil.newArrayList();
		}
		return this.list(
				new LambdaQueryWrapper<RoleEntity>()
						.select(RoleEntity::getId, RoleEntity::getName, RoleEntity::getCode)
						.in(RoleEntity::getId, roleIds)
		);
	}


    /*
    ----------------------------------------------------------------
                        私有方法 private methods
    ----------------------------------------------------------------
     */


	/**
	 * 构建查询条件 QueryWrapper
	 */
	private Wrapper<RoleEntity> buildQueryWrapper(RoleDTO dto) {
		return new LambdaQueryWrapper<RoleEntity>()
				// 角色名称
				.like(StrUtil.isNotBlank(dto.getName()), RoleEntity::getName, StrUtil.cleanBlank(dto.getName()))
				// 角色编码
				.like(StrUtil.isNotBlank(dto.getCode()), RoleEntity::getCode, StrUtil.cleanBlank(dto.getCode()))
				// 角色描述
				.like(StrUtil.isNotBlank(dto.getDescribe()), RoleEntity::getDescribe, StrUtil.cleanBlank(dto.getDescribe()))
				// 是否内置角色 （1是 0否）
				.eq(ObjectUtil.isNotNull(dto.getReadonly()), RoleEntity::getReadonly, dto.getReadonly())
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), RoleEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(RoleEntity::getCreateTime);
	}
}
