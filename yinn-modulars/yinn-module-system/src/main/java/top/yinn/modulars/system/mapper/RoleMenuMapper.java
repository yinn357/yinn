package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yinn.modulars.system.model.entity.RoleMenuEntity;


/**
 * 角色的菜单/权限
 *
 * @author Yinn
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {

}
