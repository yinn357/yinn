package top.yinn.modulars.system.controller.auth;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinn.core.context.UserContext;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.ApiResult;
import top.yinn.core.utils.IPUtil;
import top.yinn.modulars.system.model.dto.UserLoginDTO;
import top.yinn.modulars.system.model.vo.UserLoginVO;
import top.yinn.modulars.system.model.vo.UserTokenVO;
import top.yinn.modulars.system.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Author Yinn
 */
@RequiredArgsConstructor
@Slf4j
@Api(value = "登录授权接口", tags = {"登录授权接口"})
@RequestMapping("/auth")
@RestController
public class AuthController {

	private final AuthService authService;

	@ApiOperation(value = "登录", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/login")
	public ApiResult<UserTokenVO> login(HttpServletRequest request, @RequestBody @Valid UserLoginDTO dto) {
		// 从请求中得到客户端IP地址
		dto.setClientIP(IPUtil.getClientIPAddress(request));
		// 用户登录, 失败抛异常, 成功返回用户信息
		UserLoginVO loginVO = authService.login(dto);

		// 构造用户上下文
		UserContext userContext = BeanUtil.toBean(loginVO, UserContext.class);

		// SaToken 登录
		StpUtil.login(loginVO.getId());
		StpUtil.getSession().set(UserContext.CAMEL_NAME, userContext);

		// 返回登录token
		UserTokenVO tokenVO = UserTokenVO.builder()
				.tokenInfo(StpUtil.getTokenInfo())
				.roles(loginVO.getRoles())
				.permissions(loginVO.getPermissions())
				.build();


		return ApiResult.data(ExceptionCode.LOGIN_SUCCESS.getLabel(), tokenVO);
	}
}
