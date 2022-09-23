package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yinn.modulars.system.model.entity.UserEntity;


/**
 * 用户
 *
 * @author Yinn
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
