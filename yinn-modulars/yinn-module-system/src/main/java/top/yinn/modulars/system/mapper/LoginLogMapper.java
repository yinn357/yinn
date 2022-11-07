package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import top.yinn.modulars.system.model.entity.LoginLogEntity;


/**
 * 登录日志
 *
 * @author Yinn
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLogEntity> {

}
