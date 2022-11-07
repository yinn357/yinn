package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.OperateLogMapper;
import top.yinn.modulars.system.model.dto.OperateLogDTO;
import top.yinn.modulars.system.model.dto.OperateLogInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.OperateLogEntity;
import top.yinn.modulars.system.model.vo.OperateLogVO;


/**
 * 系统日志
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateLogService extends BaseServiceImpl<OperateLogMapper, OperateLogEntity> {


	/**
	 * 后台-分页列表
	 */
	public PageResult<OperateLogVO> list(PageParam pageParam, OperateLogDTO dto) {
		Page<OperateLogEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));

		return PageResult.convert(entityPage, OperateLogVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public OperateLogVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public OperateLogVO getOneById(Long id, boolean checkId) {
		OperateLogEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, OperateLogVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	public Long saveOrUpdate(OperateLogInsertOrUpdateDTO dto) {
		OperateLogEntity entity = BeanUtil.toBean(dto, OperateLogEntity.class);

		this.saveOrUpdate(entity);

		return entity.getId();
	}



    /*
    ----------------------------------------------------------------
                        私有方法 private methods
    ----------------------------------------------------------------
     */


	/**
	 * 构建查询条件 QueryWrapper
	 */
	private Wrapper<OperateLogEntity> buildQueryWrapper(OperateLogDTO dto) {
		return new LambdaQueryWrapper<OperateLogEntity>()
				// 操作IP
				.like(StrUtil.isNotBlank(dto.getRequestIp()), OperateLogEntity::getRequestIp, StrUtil.cleanBlank(dto.getRequestIp()))
				// 日志类型#LogType{OPT:操作类型;EX:异常类型}
				.like(StrUtil.isNotBlank(dto.getType()), OperateLogEntity::getType, StrUtil.cleanBlank(dto.getType()))
				// 操作人
				.like(StrUtil.isNotBlank(dto.getUserName()), OperateLogEntity::getUserName, StrUtil.cleanBlank(dto.getUserName()))
				// 操作描述
				.like(StrUtil.isNotBlank(dto.getDescription()), OperateLogEntity::getDescription, StrUtil.cleanBlank(dto.getDescription()))
				// 类路径
				.like(StrUtil.isNotBlank(dto.getClassPath()), OperateLogEntity::getClassPath, StrUtil.cleanBlank(dto.getClassPath()))
				// 请求方法
				.like(StrUtil.isNotBlank(dto.getActionMethod()), OperateLogEntity::getActionMethod, StrUtil.cleanBlank(dto.getActionMethod()))
				// 请求地址
				.like(StrUtil.isNotBlank(dto.getRequestUri()), OperateLogEntity::getRequestUri, StrUtil.cleanBlank(dto.getRequestUri()))
				// 请求类型#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}
				.like(StrUtil.isNotBlank(dto.getHttpMethod()), OperateLogEntity::getHttpMethod, StrUtil.cleanBlank(dto.getHttpMethod()))
				// 请求参数
				.like(StrUtil.isNotBlank(dto.getParams()), OperateLogEntity::getParams, StrUtil.cleanBlank(dto.getParams()))
				// 返回值
				.like(StrUtil.isNotBlank(dto.getResult()), OperateLogEntity::getResult, StrUtil.cleanBlank(dto.getResult()))
				// 异常详情信息
				.like(StrUtil.isNotBlank(dto.getExDesc()), OperateLogEntity::getExDesc, StrUtil.cleanBlank(dto.getExDesc()))
				// 异常描述
				.like(StrUtil.isNotBlank(dto.getExDetail()), OperateLogEntity::getExDetail, StrUtil.cleanBlank(dto.getExDetail()))
				// 开始时间
				.eq(ObjectUtil.isNotNull(dto.getStartTime()), OperateLogEntity::getStartTime, dto.getStartTime())
				// 完成时间
				.eq(ObjectUtil.isNotNull(dto.getFinishTime()), OperateLogEntity::getFinishTime, dto.getFinishTime())
				// 消耗时间
				.eq(ObjectUtil.isNotNull(dto.getConsumingTime()), OperateLogEntity::getConsumingTime, dto.getConsumingTime())
				// 浏览器
				.like(StrUtil.isNotBlank(dto.getUa()), OperateLogEntity::getUa, StrUtil.cleanBlank(dto.getUa()))
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), OperateLogEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(OperateLogEntity::getCreateTime);
	}
}
