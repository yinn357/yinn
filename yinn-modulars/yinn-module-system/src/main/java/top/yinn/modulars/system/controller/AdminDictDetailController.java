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
import top.yinn.modulars.system.model.dto.DictDetailDTO;
import top.yinn.modulars.system.model.dto.DictDetailInsertOrUpdateDTO;
import top.yinn.modulars.system.model.vo.DictDetailVO;
import top.yinn.modulars.system.service.DictDetailService;

import javax.validation.Valid;


/**
 * 后台管理-字典表-明细管理接口
 *
 * @author Yinn
 */
@RequiredArgsConstructor
@Slf4j
@Api(value = "字典表-明细管理接口", tags = {"字典表-明细管理接口"})
@RequestMapping(SysConstant.SYS_MODULE_CONTEXT_PATH + YinnConstant.Version.HTTP_API_VERSION_V1 + "/dictDetails")
@RestController
public class AdminDictDetailController {

	/**
	 * 功能权限串前缀
	 */
	private static final String PERMISSION_PREFIX = "system:dictDetail:" ;

	private final DictDetailService dictDetailService;

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "分页列表", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping
	public ApiResult<PageResult<DictDetailVO>> list(PageParam pageParam, DictDetailDTO dto) {
		return ApiResult.data(dictDetailService.list(pageParam, dto));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.RETRIEVE)
	@ApiOperation(value = "详情", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}")
	public ApiResult<DictDetailVO> getById(@PathVariable Long id) {
		return ApiResult.data(dictDetailService.getOneById(id, true));
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.CREATE)
	@ApiOperation(value = "新增", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ApiResult<?> insert(@RequestBody @Valid DictDetailInsertOrUpdateDTO dto) {
		dto.setId(null);
		dictDetailService.saveOrUpdate(dto);
		return ApiResult.success();
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.UPDATE)
	@ApiOperation(value = "编辑", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(value = "/{id}")
	public ApiResult<?> update(@PathVariable Long id, @RequestBody @Valid DictDetailInsertOrUpdateDTO dto) {
		dto.setId(id);
		dictDetailService.saveOrUpdate(dto);
		return ApiResult.success();
	}

	@SaCheckPermission(value = PERMISSION_PREFIX + YinnConstant.Permission.DELETE)
	@ApiOperation(value = "删除", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping
	public ApiResult<?> delete(@RequestBody @Valid IdsDTO<Long> dto) {
		dictDetailService.removeBatchByIds(dto.getIds());
		return ApiResult.success();
	}

}
