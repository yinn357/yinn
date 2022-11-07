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
import top.yinn.modulars.system.mapper.LoginLogMapper;
import top.yinn.modulars.system.model.dto.LoginLogDTO;
import top.yinn.modulars.system.model.dto.LoginLogInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.LoginLogEntity;
import top.yinn.modulars.system.model.vo.LoginLogVO;


/**
 * 登录日志
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogService extends BaseServiceImpl<LoginLogMapper, LoginLogEntity> {


	/**
	 * 后台-分页列表
	 */
	public PageResult<LoginLogVO> list(PageParam pageParam, LoginLogDTO dto) {
		Page<LoginLogEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));

		return PageResult.convert(entityPage, LoginLogVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public LoginLogVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public LoginLogVO getOneById(Long id, boolean checkId) {
		LoginLogEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, LoginLogVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	public Long saveOrUpdate(LoginLogInsertOrUpdateDTO dto) {
		LoginLogEntity entity = BeanUtil.toBean(dto, LoginLogEntity.class);

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
	private Wrapper<LoginLogEntity> buildQueryWrapper(LoginLogDTO dto) {
		return new LambdaQueryWrapper<LoginLogEntity>()
				// 操作IP
				.like(StrUtil.isNotBlank(dto.getRequestIp()), LoginLogEntity::getRequestIp, StrUtil.cleanBlank(dto.getRequestIp()))
				// 登录人ID
				.eq(ObjectUtil.isNotNull(dto.getUserId()), LoginLogEntity::getUserId, dto.getUserId())
				// 登录人姓名
				.like(StrUtil.isNotBlank(dto.getUserName()), LoginLogEntity::getUserName, StrUtil.cleanBlank(dto.getUserName()))
				// 登录人账号
				.like(StrUtil.isNotBlank(dto.getAccount()), LoginLogEntity::getAccount, StrUtil.cleanBlank(dto.getAccount()))
				// 登录描述
				.like(StrUtil.isNotBlank(dto.getDescription()), LoginLogEntity::getDescription, StrUtil.cleanBlank(dto.getDescription()))
				// 登录时间
				.eq(ObjectUtil.isNotNull(dto.getLoginDate()), LoginLogEntity::getLoginDate, dto.getLoginDate())
				// 浏览器请求头
				.like(StrUtil.isNotBlank(dto.getUa()), LoginLogEntity::getUa, StrUtil.cleanBlank(dto.getUa()))
				// 浏览器名称
				.like(StrUtil.isNotBlank(dto.getBrowser()), LoginLogEntity::getBrowser, StrUtil.cleanBlank(dto.getBrowser()))
				// 浏览器版本
				.like(StrUtil.isNotBlank(dto.getBrowserVersion()), LoginLogEntity::getBrowserVersion, StrUtil.cleanBlank(dto.getBrowserVersion()))
				// 操作系统
				.like(StrUtil.isNotBlank(dto.getOperatingSystem()), LoginLogEntity::getOperatingSystem, StrUtil.cleanBlank(dto.getOperatingSystem()))
				// 登录地点
				.like(StrUtil.isNotBlank(dto.getLocation()), LoginLogEntity::getLocation, StrUtil.cleanBlank(dto.getLocation()))
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), LoginLogEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(LoginLogEntity::getCreateTime);
	}
}
