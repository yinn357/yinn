package top.yinn.modulars.system.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import top.yinn.core.utils.StrPool;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.constant.CacheKeyConstant;
import top.yinn.modulars.system.enums.UserErrorEnum;
import top.yinn.modulars.system.mapper.UserRoleMapper;
import top.yinn.modulars.system.model.entity.UserRoleEntity;
import top.yinn.redis.constant.CacheRegionConstant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 角色分配
 * 账号角色绑定
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleService extends BaseServiceImpl<UserRoleMapper, UserRoleEntity> {

	/**
	 * 取拥有角色Ids
	 *
	 * @param userId 用户Id
	 * @return 失败返回空列表
	 */
	public Set<Long> listRoleIdsByUserId(Long userId) {
		UserErrorEnum.USER_ID_INVALID.assertNotNull(userId);

		return this.list(
				new LambdaQueryWrapper<UserRoleEntity>()
						.select(UserRoleEntity::getRoleId)
						.eq(UserRoleEntity::getUserId, userId)
		).stream().map(UserRoleEntity::getRoleId).collect(Collectors.toSet());
	}

	/**
	 * 绑定角色ID与菜单ID关联关系，增量更新
	 *
	 * @param userId  角色ID
	 * @param roleIds 新菜单ID集合
	 */
	@CacheEvict(value = CacheRegionConstant.USER_RESOURCE + StrPool.COLON + CacheKeyConstant.Auth.USER_ROLE, key = "(#userId)")
	public void cleanAndBind(Long userId, Collection<Long> roleIds) {
		LambdaQueryWrapper<UserRoleEntity> roleIdsQuery =
				new LambdaQueryWrapper<UserRoleEntity>()
						.select(UserRoleEntity::getRoleId)
						.eq(UserRoleEntity::getUserId, userId);

		if (CollUtil.isEmpty(roleIds)) {
			// 清除绑定，直接删除所有关联关系就行
			this.remove(roleIdsQuery);
			return;
		}

		// 先删除不再需要的关联关系
		this.remove(
				new LambdaQueryWrapper<UserRoleEntity>()
						.eq(UserRoleEntity::getUserId, userId)
						.notIn(UserRoleEntity::getRoleId, roleIds)
		);

		// 取出需要增量更新的部分
		Set<Long> existingRoleIds = this.list(roleIdsQuery).stream()
				.map(UserRoleEntity::getRoleId).collect(Collectors.toSet());
		roleIds.removeAll(existingRoleIds);

		if (CollUtil.isEmpty(roleIds)) {
			// 没有需要增量更新的部分
			return;
		}

		// 批量插入需要增量更新的部分
		List<UserRoleEntity> entityList = new ArrayList<>(roleIds.size());
		for (Long roleId : roleIds) {
			entityList.add(
					UserRoleEntity.builder()
							.userId(userId)
							.roleId(roleId)
							.build()
			);
		}
		this.saveBatch(entityList);
	}

}
