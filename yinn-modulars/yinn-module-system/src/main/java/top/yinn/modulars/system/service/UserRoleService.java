package top.yinn.modulars.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.UserRoleMapper;
import top.yinn.modulars.system.model.entity.UserRoleEntity;


/**
 * 角色分配
 * 账号角色绑定
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleService extends BaseServiceImpl<UserRoleMapper, UserRoleEntity> {


}
