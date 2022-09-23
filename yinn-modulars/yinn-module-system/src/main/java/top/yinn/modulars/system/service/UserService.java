package top.yinn.modulars.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.core.base.PageParam;
import top.yinn.core.base.PageResult;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.UserMapper;
import top.yinn.modulars.system.model.dto.UserDTO;
import top.yinn.modulars.system.model.entity.UserEntity;
import top.yinn.modulars.system.model.vo.UserVO;


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
	 * 后台管理-分页列表
	 */
	public PageResult<UserVO> list(PageParam pageParam, UserDTO dto) {

		return null;
	}
}
