package top.yinn.modulars.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinn.core.constant.YinnConstant;
import top.yinn.core.model.ApiResult;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.modulars.system.constant.SysConstant;
import top.yinn.modulars.system.model.dto.LoginLogDTO;
import top.yinn.modulars.system.model.vo.LoginLogVO;
import top.yinn.modulars.system.service.LoginLogService;


/**
 * 后台管理-登录日志管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
@Slf4j
@Api(value = "登录日志管理接口", tags = {"登录日志管理接口"})
@RequestMapping(SysConstant.SYS_MODULE_CONTEXT_PATH + YinnConstant.Version.HTTP_API_VERSION_V1 + "/loginLogs")
@RestController
public class AdminLoginLogController {

	/**
	 * 功能权限串前缀
	 */
	private static final String PERMISSION_PREFIX = "system:loginLog:" ;

	private final LoginLogService loginLogService;

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "分页列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public ApiResult<PageResult<LoginLogVO>> list(PageParam pageParam, LoginLogDTO dto) {
		return ApiResult.data(loginLogService.list(pageParam, dto));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "详情", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}")
	public ApiResult<LoginLogVO> getById(@PathVariable Long id) {
		return ApiResult.data(loginLogService.getOneById(id, true));
	}

}
