package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yinn.modulars.system.model.entity.MenuEntity;


/**
 * 菜单权限表
 *
 * @author Yinn
 */
@Mapper
public interface MenuMapper extends BaseMapper<MenuEntity> {

}
