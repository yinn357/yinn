package top.yinn.modulars.test.service;

import org.springframework.stereotype.Service;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.test.mapper.AuthUserMapper;
import top.yinn.modulars.test.model.entity.AuthUserEntity;

/**
 * @Author Yinn
 */
@Service
public class AuthUserService extends BaseServiceImpl<AuthUserMapper, AuthUserEntity> {
}
