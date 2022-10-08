package top.yinn.modulars.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.enums.UserErrorEnum;
import top.yinn.modulars.system.mapper.UserRoleMapper;
import top.yinn.modulars.system.model.entity.UserRoleEntity;

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

}
