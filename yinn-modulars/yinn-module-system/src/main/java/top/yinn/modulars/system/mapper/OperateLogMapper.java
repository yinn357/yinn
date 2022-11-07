package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import top.yinn.modulars.system.model.entity.OperateLogEntity;


/**
 * 系统日志
 *
 * @author Yinn
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLogEntity> {

}
