package top.yinn.modulars.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.yinn.core.constant.YinnConstant;
import top.yinn.core.model.ApiResult;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.database.id.IdsDTO;
import top.yinn.modulars.system.constant.SysConstant;
import top.yinn.modulars.system.model.dto.DictDTO;
import top.yinn.modulars.system.model.dto.DictInsertOrUpdateDTO;
import top.yinn.modulars.system.model.vo.DictVO;
import top.yinn.modulars.system.service.DictService;

import javax.validation.Valid;


/**
 * 后台管理-字典表管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
@Slf4j
@Api(value = "字典表管理接口", tags = {"字典表管理接口"})
@RequestMapping(SysConstant.SYS_MODULE_CONTEXT_PATH + YinnConstant.Version.HTTP_API_VERSION_V1 + "/dicts")
@RestController
public class AdminDictController {

	/**
	 * 功能权限串前缀
	 */
	private static final String PERMISSION_PREFIX = "system:dict:" ;

	private final DictService dictService;

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "分页列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public ApiResult<PageResult<DictVO>> list(PageParam pageParam, DictDTO dto) {
		return ApiResult.data(dictService.list(pageParam, dto));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "详情", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}")
	public ApiResult<DictVO> getById(@PathVariable Long id) {
		return ApiResult.data(dictService.getOneById(id, true));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.CREATE)
	@ApiOperation(value = "新增", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ApiResult<?> insert(@RequestBody @Valid DictInsertOrUpdateDTO dto) {
		dto.setId(null);
		dictService.saveOrUpdate(dto);
		return ApiResult.success();
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.UPDATE)
	@ApiOperation(value = "编辑", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value = "/{id}")
	public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid DictInsertOrUpdateDTO dto) {
		dto.setId(id);
		dictService.saveOrUpdate(dto);
		return ApiResult.success();
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.DELETE)
	@ApiOperation(value = "删除", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping
	public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
		dictService.removeBatchByIds(dto.getIds());
		return ApiResult.success();
	}

}
