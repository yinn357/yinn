package top.yinn.modulars.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.yinn.database.service.impl.BaseServiceImpl;
import top.yinn.modulars.system.mapper.MenuMapper;
import top.yinn.modulars.system.model.entity.MenuEntity;

/**
 * 菜单权限表
 *
 * @author Yinn
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService extends BaseServiceImpl<MenuMapper, MenuEntity> {


}
