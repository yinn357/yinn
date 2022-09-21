package top.yinn.modulars.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.yinn.modulars.test.model.entity.AuthUserEntity;

/**
 * @Author Yinn
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUserEntity> {

}
