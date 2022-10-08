package top.yinn.modulars.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.RoleMenuMapper;
import top.yinn.modulars.system.model.entity.RoleMenuEntity;

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


}
