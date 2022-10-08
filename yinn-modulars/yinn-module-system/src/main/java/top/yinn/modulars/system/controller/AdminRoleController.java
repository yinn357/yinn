package top.yinn.modulars.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
import top.yinn.modulars.system.model.dto.RoleDTO;
import top.yinn.modulars.system.model.dto.RoleInsertOrUpdateDTO;
import top.yinn.modulars.system.model.vo.RoleVO;
import top.yinn.modulars.system.service.RoleService;

import javax.validation.Valid;


/**
 * 后台管理-角色管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
@Slf4j
@Api(value = "角色管理接口", tags = {"角色管理接口"})
@RequestMapping(SysConstant.SYS_MODULE_CONTEXT_PATH + YinnConstant.Version.HTTP_API_VERSION_V1 + "/roles")
@RestController
public class AdminRoleController {

	/**
	 * 功能权限串前缀
	 */
	private static final String PERMISSION_PREFIX = "system:role:" ;

	private final RoleService roleService;

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "分页列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public ApiResult<PageResult<RoleVO>> list(PageParam pageParam, RoleDTO dto) {
		return ApiResult.data(roleService.list(pageParam, dto));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "详情", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}")
	public ApiResult<RoleVO> getById(@PathVariable Long id) {
		return ApiResult.data(roleService.getOneById(id, true));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.CREATE)
	@ApiOperation(value = "新增", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ApiResult<?> insert(@RequestBody @Valid RoleInsertOrUpdateDTO dto) {
		dto.setId(null);
		roleService.saveOrUpdate(dto);
		return ApiResult.success();
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.UPDATE)
	@ApiOperation(value = "编辑", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value = "/{id}")
	public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid RoleInsertOrUpdateDTO dto) {
		dto.setId(id);
		roleService.saveOrUpdate(dto);
		return ApiResult.success();
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.DELETE)
	@ApiOperation(value = "删除", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping
	public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
		roleService.removeBatchByIds(dto.getIds());
		return ApiResult.success();
	}

}
