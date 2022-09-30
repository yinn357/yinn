package top.yinn.modulars.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.base.PageParam;
import top.yinn.core.base.PageResult;
import top.yinn.core.exception.code.ExceptionCode;
import top.yinn.core.utils.PwdUtil;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.UserMapper;
import top.yinn.modulars.system.model.dto.UserDTO;
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


	/**
	 * 后台用户管理-分页列表
	 */
	public PageResult<UserVO> list(PageParam pageParam, UserDTO dto) {
		Page<UserEntity> entityPage = this.page(
				new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),
				new LambdaQueryWrapper<UserEntity>()
						.like(StrUtil.isNotEmpty(dto.getNickName()), UserEntity::getNickName, dto.getNickName())
						.like(StrUtil.isNotEmpty(dto.getAccount()), UserEntity::getAccount, dto.getAccount())
						.orderByDesc(UserEntity::getCreateTime)
		);
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
		UserEntity userEntity = this.getById(id);
		if (checkId) {
			ExceptionCode.INVALID_ID.assertNotNull(userEntity);
		}

		return BeanUtil.toBean(userEntity, UserVO.class);
	}

	/**
	 * 用户管理-新增或更新
	 *
	 * @param userDTO
	 * @return 用户Id
	 */
	public Long saveOrUpdate(UserDTO userDTO) {
		UserEntity userEntity = BeanUtil.toBean(userDTO, UserEntity.class);
		// 用户密码加密
		userEntity.setPassword(PwdUtil.encrypt(userEntity.getPassword()));
		this.saveOrUpdate(userEntity);

		return userEntity.getId();
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
}
