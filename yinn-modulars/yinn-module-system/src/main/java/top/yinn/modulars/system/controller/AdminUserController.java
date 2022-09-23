package top.yinn.modulars.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yinn.core.base.ApiResult;
import top.yinn.core.base.PageParam;
import top.yinn.core.base.PageResult;
import top.yinn.core.constant.YinnConstant;
import top.yinn.modulars.system.constant.SysConstant;
import top.yinn.modulars.system.model.dto.UserDTO;
import top.yinn.modulars.system.model.vo.UserVO;
import top.yinn.modulars.system.service.UserService;


/**
 * 后台管理-用户管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
@SaCheckLogin
@Slf4j
@Api(value = "用户管理接口", tags = {"用户管理接口"})
@RequestMapping(SysConstant.SYS_MODULE_CONTEXT_PATH + YinnConstant.Version.HTTP_API_VERSION_V1 + "/users")
@RestController
public class AdminUserController {

    /**
     * 功能权限串前缀
     */
    private static final String PERMISSION_PREFIX = "system:User:";

    private final UserService userService;

    @SaCheckLogin
    // @SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
    @ApiOperation(value = "分页列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public ApiResult<PageResult<UserVO>> list(PageParam pageParam, UserDTO dto) {
        return ApiResult.data(userService.list(pageParam, dto));
    }
}
