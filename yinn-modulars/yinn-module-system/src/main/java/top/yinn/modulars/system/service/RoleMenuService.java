package top.yinn.modulars.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.RoleMenuMapper;
import top.yinn.modulars.system.model.entity.RoleMenuEntity;


/**
 * 角色的菜单/权限
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleMenuService extends BaseServiceImpl<RoleMenuMapper, RoleMenuEntity> {


}
