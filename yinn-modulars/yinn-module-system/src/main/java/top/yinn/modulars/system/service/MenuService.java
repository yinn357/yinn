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
import top.yinn.core.base.PageParam;
import top.yinn.core.base.PageResult;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.MenuMapper;
import top.yinn.modulars.system.model.dto.MenuDTO;
import top.yinn.modulars.system.model.dto.MenuInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.MenuEntity;
import top.yinn.modulars.system.model.vo.MenuVO;


/**
 * 菜单权限表
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService extends BaseServiceImpl<MenuMapper, MenuEntity> {


	/**
	 * 后台-分页列表
	 */
	public PageResult<MenuVO> list(PageParam pageParam, MenuDTO dto) {
		Page<MenuEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));

		return PageResult.convert(entityPage, MenuVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public MenuVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public MenuVO getOneById(Long id, boolean checkId) {
		MenuEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, MenuVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	public Long saveOrUpdate(MenuInsertOrUpdateDTO dto) {
		MenuEntity entity = BeanUtil.toBean(dto, MenuEntity.class);

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
	private Wrapper<MenuEntity> buildQueryWrapper(MenuDTO dto) {
		return new LambdaQueryWrapper<MenuEntity>()
				// 菜单名称
				.like(StrUtil.isNotBlank(dto.getMenuName()), MenuEntity::getMenuName, StrUtil.cleanBlank(dto.getMenuName()))
				// 父菜单ID
				.eq(ObjectUtil.isNotNull(dto.getParentId()), MenuEntity::getParentId, dto.getParentId())
				// 显示顺序
				.eq(ObjectUtil.isNotNull(dto.getOrderNum()), MenuEntity::getOrderNum, dto.getOrderNum())
				// 路由地址
				.like(StrUtil.isNotBlank(dto.getPath()), MenuEntity::getPath, StrUtil.cleanBlank(dto.getPath()))
				// 组件路径
				.like(StrUtil.isNotBlank(dto.getComponent()), MenuEntity::getComponent, StrUtil.cleanBlank(dto.getComponent()))
				// 路由参数
				.like(StrUtil.isNotBlank(dto.getQueryParam()), MenuEntity::getQueryParam, StrUtil.cleanBlank(dto.getQueryParam()))
				// 是否为外链（1是 0否）
				.eq(ObjectUtil.isNotNull(dto.getIsFrame()), MenuEntity::getIsFrame, dto.getIsFrame())
				// 是否缓存（1缓存 0不缓存）
				.eq(ObjectUtil.isNotNull(dto.getIsCache()), MenuEntity::getIsCache, dto.getIsCache())
				// 菜单类型（M目录 C菜单 F按钮）
				.like(StrUtil.isNotBlank(dto.getMenuType()), MenuEntity::getMenuType, StrUtil.cleanBlank(dto.getMenuType()))
				// 菜单状态（1显示 0隐藏）
				.eq(ObjectUtil.isNotNull(dto.getVisible()), MenuEntity::getVisible, dto.getVisible())
				// 菜单状态（1正常 0停用）
				.eq(ObjectUtil.isNotNull(dto.getStatus()), MenuEntity::getStatus, dto.getStatus())
				// 权限标识
				.like(StrUtil.isNotBlank(dto.getPerms()), MenuEntity::getPerms, StrUtil.cleanBlank(dto.getPerms()))
				// 菜单图标
				.like(StrUtil.isNotBlank(dto.getIcon()), MenuEntity::getIcon, StrUtil.cleanBlank(dto.getIcon()))
				// 备注
				.like(StrUtil.isNotBlank(dto.getRemark()), MenuEntity::getRemark, StrUtil.cleanBlank(dto.getRemark()))
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), MenuEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(MenuEntity::getCreateTime);
	}
}
