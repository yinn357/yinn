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
import top.yinn.core.utils.PwdUtil;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.UserMapper;
import top.yinn.modulars.system.model.dto.UserDTO;
import top.yinn.modulars.system.model.dto.UserInsertOrUpdateDTO;
import top.yinn.modulars.system.model.dto.UserRoleBindRelationDTO;
import top.yinn.modulars.system.model.entity.UserEntity;
import top.yinn.modulars.system.model.vo.UserVO;

import java.time.LocalDateTime;


/**
 * 用户
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService extends BaseServiceImpl<UserMapper, UserEntity> {

	private final UserRoleService userRoleService;

	/**
	 * 后台用户管理-分页列表
	 */
	public PageResult<UserVO> list(PageParam pageParam, UserDTO dto) {
		Page<UserEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				buildQueryWrapper(dto));
		return PageResult.convert(entityPage, UserVO.class);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id 主键ID
	 * @return VO Or null
	 */
	public UserVO getOneById(Long id) {
		return this.getOneById(id, false);
	}

	/**
	 * 根据 ID 取详情
	 *
	 * @param id      主键ID
	 * @param checkId 是否在 ID 无效时抛出异常
	 * @return VO Or 抛出无效Id异常
	 */
	public UserVO getOneById(Long id, boolean checkId) {
		UserEntity entity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(entity);
		}

		return BeanUtil.toBean(entity, UserVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param dto
	 * @return 用户Id
	 */
	public Long saveOrUpdate(UserInsertOrUpdateDTO dto) {
		UserEntity entity = BeanUtil.toBean(dto, UserEntity.class);
		// 用户密码加密
		entity.setPassword(PwdUtil.encrypt(entity.getPassword()));
		this.saveOrUpdate(entity);

		return entity.getId();
	}


	/**
	 * 更新用户登录时间
	 *
	 * @param userId        用户Id
	 * @param lastLoginTime 最后登录最近
	 * @return
	 */
	public boolean updateLastLoginTime(Long userId, LocalDateTime lastLoginTime) {
		UserEntity userEntity = UserEntity.builder()
				.id(userId)
				.lastLoginTime(lastLoginTime)
				.build();
		return this.updateById(userEntity);
	}

	/**
	 * 后台管理-绑定用户与角色关联关系
	 */
	public void bindRoles(UserRoleBindRelationDTO dto) {
		userRoleService.cleanAndBind(dto.getUserId(), dto.getRoleIds());
	}

    /*
    ----------------------------------------------------------------
                        私有方法 private methods
    ----------------------------------------------------------------
     */


	/**
	 * 构建查询条件QueryWrapper
	 */
	private Wrapper<UserEntity> buildQueryWrapper(UserDTO dto) {
		return new LambdaQueryWrapper<UserEntity>()
				.like(StrUtil.isNotEmpty(dto.getNickName()), UserEntity::getNickName, dto.getNickName())
				.like(StrUtil.isNotEmpty(dto.getAccount()), UserEntity::getAccount, dto.getAccount())
				.orderByDesc(UserEntity::getCreateTime)
				// 时间区间
				.between(ObjectUtil.isNotNull(dto.getBeginTime()) && ObjectUtil.isNotNull(dto.getEndTime()), UserEntity::getCreateTime, dto.getBeginTime(), dto.getEndTime())
				// 排序
				.orderByDesc(UserEntity::getCreateTime);
	}
}
