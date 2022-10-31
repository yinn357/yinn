package top.yinn.modulars.system.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import top.yinn.core.constant.YinnConstant;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.utils.StrPool;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.j2cache.constant.CacheRegionConstant;
import top.yinn.modulars.system.mapper.RoleMenuMapper;
import top.yinn.modulars.system.model.entity.RoleMenuEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 角色的菜单/权限
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleMenuService extends BaseServiceImpl<RoleMenuMapper, RoleMenuEntity> {


	/**
	 * 取拥有菜单/权限Ids
	 *
	 * @param roleId 角色Id
	 * @return 失败返回空列表
	 */
	public Set<Long> listMenuIdsByRoleId(Long roleId) {
		ExceptionCode.INVALID_ID.assertNotNull(roleId);

		return this.list(
				new LambdaQueryWrapper<RoleMenuEntity>()
						.select(RoleMenuEntity::getMenuId)
						.eq(RoleMenuEntity::getRoleId, roleId)
		).stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toSet());
	}

	/**
	 * 绑定角色ID与菜单ID关联关系，增量更新
	 *
	 * @param roleId  角色ID
	 * @param menuIds 新菜单ID集合
	 */
	@CacheEvict(value = CacheRegionConstant.USER_RESOURCE + StrPool.COLON + YinnConstant.User.ROlE_MENU, key = "(#roleId)")
	public void cleanAndBind(Long roleId, Collection<Long> menuIds) {
		LambdaQueryWrapper<RoleMenuEntity> menuIdsQuery =
				new LambdaQueryWrapper<RoleMenuEntity>()
						.select(RoleMenuEntity::getMenuId)
						.eq(RoleMenuEntity::getRoleId, roleId);

		if (CollUtil.isEmpty(menuIds)) {
			// 清除绑定，直接删除所有关联关系就行
			this.remove(menuIdsQuery);
			return;
		}

		// 先删除不再需要的关联关系
		this.remove(
				new LambdaQueryWrapper<RoleMenuEntity>()
						.eq(RoleMenuEntity::getRoleId, roleId)
						.notIn(RoleMenuEntity::getMenuId, menuIds)
		);

		// 取出需要增量更新的部分
		Set<Long> existingMenuIds = this.list(menuIdsQuery).stream()
				.map(RoleMenuEntity::getMenuId).collect(Collectors.toSet());
		menuIds.removeAll(existingMenuIds);

		if (CollUtil.isEmpty(menuIds)) {
			// 没有需要增量更新的部分
			return;
		}

		// 批量插入需要增量更新的部分
		List<RoleMenuEntity> entityList = new ArrayList<>(menuIds.size());
		for (Long menuId : menuIds) {
			entityList.add(
					RoleMenuEntity.builder()
							.roleId(roleId)
							.menuId(menuId)
							.build()
			);
		}
		this.saveBatch(entityList);
	}


}
