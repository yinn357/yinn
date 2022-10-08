package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.utils.PwdUtil;
import top.yinn.modulars.system.enums.UserErrorEnum;
import top.yinn.modulars.system.model.dto.UserLoginDTO;
import top.yinn.modulars.system.model.entity.RoleEntity;
import top.yinn.modulars.system.model.entity.UserEntity;
import top.yinn.modulars.system.model.vo.UserLoginVO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户登录授权服务
 *
 * @Author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserService userService;

	private final RoleService roleService;

	private final MenuService menuService;

	/**
	 * 用户登录
	 */
	public UserLoginVO login(UserLoginDTO dto) {
		//TODO 校验验证码

		// 根据账号查询用户信息
		UserEntity loginUser = userService.getOne(UserEntity::getAccount, dto.getAccount());
		// 用户是否存在
		// 不要直接提示“账号不存在”或“密码不正确”，避免撞库攻击
		UserErrorEnum.INCORRECT_PIN_OR_PWD.assertNotNull(loginUser);
		// 比对用户密码
		boolean pwdCheck = PwdUtil.check(dto.getPassword(), loginUser.getPassword());
		UserErrorEnum.INCORRECT_PIN_OR_PWD.assertIsFalse(pwdCheck);

		// 用户账号状态
		UserErrorEnum.BANNED_USER.assertIsTrue(!loginUser.getStatus());

		// 更新用户最后登录时间
		userService.updateLastLoginTime(loginUser.getId(), LocalDateTimeUtil.now());

		// 查询用户角色/权限信息
		// 查询用户下角色
		List<RoleEntity> roleList = roleService.listByUserId(loginUser.getId());
		// 查询角色的权限
		Set<String> permission = menuService.getRolePermission(roleList.stream().map(RoleEntity::getId).collect(Collectors.toSet()));

		// 包装返回体
		UserLoginVO userLoginVO = BeanUtil.toBean(loginUser, UserLoginVO.class);

		userLoginVO.setRoleIds(roleList.stream().map(RoleEntity::getId).collect(Collectors.toSet()));
		userLoginVO.setRoles(roleList.stream().map(RoleEntity::getCode).collect(Collectors.toList()));
		userLoginVO.setPermissions(permission);

		return userLoginVO;
	}

}
