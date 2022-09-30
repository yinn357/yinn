package top.yinn.modulars.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.yinn.core.base.ApiResult;
import top.yinn.core.base.PageParam;
import top.yinn.core.base.PageResult;
import top.yinn.core.constant.YinnConstant;
import top.yinn.database.id.IdsDTO;
import top.yinn.modulars.system.constant.SysConstant;
import top.yinn.modulars.system.model.dto.UserDTO;
import top.yinn.modulars.system.model.dto.UserInsertOrUpdateDTO;
import top.yinn.modulars.system.model.vo.UserVO;
import top.yinn.modulars.system.service.UserService;

import javax.validation.Valid;


/**
 * 后台管理-用户管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
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

    // @SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
    @ApiOperation(value = "分页列表", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public ApiResult<PageResult<UserVO>> list(PageParam pageParam, UserDTO dto) {
        return ApiResult.data(userService.list(pageParam, dto));
    }

    // @SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
    @ApiOperation(value = "详情", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/{id}")
    public ApiResult<UserVO> getById(@PathVariable Long id) {
        return ApiResult.data(userService.getOneById(id, true));
    }

    // @SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.CREATE)
    @ApiOperation(value = "新增", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ApiResult<?> insert(@RequestBody @Valid UserInsertOrUpdateDTO dto) {
        dto.setId(null);
        userService.saveOrUpdate(dto);
        return ApiResult.success();
    }

    // @SaCheckPermission( value = PERMISSION_PREFIX + YinnConstant.Permission.UPDATE)
    @ApiOperation(value = "编辑", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(value = "/{id}")
    public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid UserInsertOrUpdateDTO dto) {
        dto.setId(id);
        userService.saveOrUpdate(dto);
        return ApiResult.success();
    }

    // @SaCheckPermission( value = PERMISSION_PREFIX + YinnConstant.Permission.DELETE)
    @ApiOperation(value = "删除", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @DeleteMapping
    public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
        userService.removeBatchByIds(dto.getIds());
        return ApiResult.success();
    }

}
