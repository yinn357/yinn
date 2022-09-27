package top.yinn.modulars.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinn.core.constant.YinnConstant;
import top.yinn.modulars.system.constant.SysConstant;
import top.yinn.modulars.system.service.UserRoleService;


/**
 * 后台管理-角色分配
 * 账号角色绑定管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
@SaCheckLogin
@Slf4j
@Api(value = "角色分配 账号角色绑定管理接口", tags = {"角色分配 账号角色绑定管理接口"})
@RequestMapping(SysConstant.SYS_MODULE_CONTEXT_PATH + YinnConstant.Version.HTTP_API_VERSION_V1 + "/userRoles")
@RestController
public class AdminUserRoleController {

	/**
	 * 功能权限串前缀
	 */
	private static final String PERMISSION_PREFIX = "system:UserRole:";

	private final UserRoleService userRoleService;


}