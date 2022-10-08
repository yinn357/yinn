package top.yinn.modulars.system.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import top.yinn.core.context.UserContextHolder;
import top.yinn.modulars.system.service.MenuService;

import java.util.List;
import java.util.Set;

/**
 * 自定义权限验证接口扩展
 * 用于后台管理
 *
 * @Author Yinn
 */
@Component
@RequiredArgsConstructor
public class SaTokenExtendConfiguration implements StpInterface {

	private final MenuService menuService;

	/**
	 * 返回一个账号所拥有的权限码集合
	 */
	@Override
	public List<String> getPermissionList(Object loginId, String loginType) {
		// 查询角色的权限
		Set<String> permission = menuService.getRolePermission(UserContextHolder.getUserContext().getRoleIds());
		return CollUtil.newArrayList(permission);
	}

	/**
	 * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
	 */
	@Override
	public List<String> getRoleList(Object loginId, String loginType) {
		return UserContextHolder.getUserContext().getRoles();
	}
}
