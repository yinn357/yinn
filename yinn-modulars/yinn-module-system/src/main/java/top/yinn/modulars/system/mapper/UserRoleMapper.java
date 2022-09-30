package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import top.yinn.modulars.system.model.entity.UserRoleEntity;


/**
 * 角色分配 账号角色绑定
 *
 * @author Yinn
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}
