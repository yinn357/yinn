package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.core.utils.StrPool;
import top.yinn.core.utils.TreeBuildUtils;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.constant.CacheKeyConstant;
import top.yinn.modulars.system.constant.SysConstant;
import top.yinn.modulars.system.mapper.MenuMapper;
import top.yinn.modulars.system.model.dto.MenuDTO;
import top.yinn.modulars.system.model.dto.MenuInsertOrUpdateDTO;
import top.yinn.modulars.system.model.entity.MenuEntity;
import top.yinn.modulars.system.model.vo.MenuVO;
import top.yinn.redis.constant.CacheRegionConstant;
import top.yinn.redis.enums.CacheRegion;
import top.yinn.redis.util.RedisUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 菜单权限表
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService extends BaseServiceImpl<MenuMapper, MenuEntity> {


	private final RoleMenuService roleMenuService;

	private final RedisUtil redisUtil;

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

	/**
	 * 获取角色ID 对应的权限
	 *
	 * @param roleId 角色ID
	 * @return 失败返回空列表
	 */
	// 内部调用该注解不生效
	@Cacheable(value = CacheRegionConstant.USER_RESOURCE + StrPool.COLON + CacheKeyConstant.Auth.ROlE_MENU, key = "(#roleId)")
	public List<MenuEntity> listByRoleId(Long roleId) {
		List<MenuEntity> menuList = redisUtil.get(CacheKeyConstant.Auth.ROlE_MENU, roleId, CacheRegion.USER_RESOURCE);
		if (CollUtil.isEmpty(menuList)) {
			if (SysConstant.SUPER_ADMIN_ROLE_ID.equals(roleId)) {
				menuList = this.list();
			} else {
				Set<Long> menuIds = roleMenuService.listMenuIdsByRoleId(roleId);
				menuList = this.listByIds(menuIds);
			}
			redisUtil.set(CacheKeyConstant.Auth.ROlE_MENU, roleId, menuList, CacheRegion.USER_RESOURCE);
		}
		return menuList;
	}

	/**
	 * 获取角色对应的权限标识
	 *
	 * @param roleIds 多个角色Id
	 * @return 超级管理员返回所以权限标识
	 */
	public Set<String> getRolePermission(Set<Long> roleIds) {
		Set<String> perms = CollUtil.newHashSet();
		roleIds.forEach(roleId -> {
			// 角色菜单列表
			List<MenuEntity> list = this.listByRoleId(roleId);

			perms.addAll(list.stream()
					.map(MenuEntity::getPerms)
					.filter(StrUtil::isNotEmpty)
					.collect(Collectors.toSet()));
		});

		return perms;
	}

	@Override
	@Cacheable(value = CacheRegionConstant.USER_RESOURCE + StrPool.COLON + CacheKeyConstant.Auth.MENU, key = CacheKeyConstant.METHOD_NAME)
	public List<MenuEntity> list() {
		return super.list();
	}

	/**
	 * 菜单列表封装为树形结构
	 *
	 * @return 树形结构数据
	 */
	public List<Tree<Long>> tree(List<MenuEntity> menuList) {
		return TreeBuildUtils.build(menuList, (menu, tree) -> {
			tree.setId(menu.getId());
			tree.setParentId(menu.getParentId());
			tree.setName(menu.getMenuName());
			tree.setWeight(menu.getOrderNum());
			tree.putExtra("icon", menu.getIcon());
			tree.putExtra("menuType", menu.getMenuType());
			tree.putExtra("path", menu.getPath());
			tree.putExtra("isFrame", menu.getIsFrame());
			tree.putExtra("component", menu.getComponent());
		});
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
				// 菜单类型（D目录 M菜单 B按钮 E外链）
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
