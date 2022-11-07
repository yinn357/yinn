package top.yinn.modulars.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import top.yinn.modulars.system.model.entity.DictEntity;


/**
 * 字典表
 *
 * @author Yinn
 */
@Mapper
public interface DictMapper extends BaseMapper<DictEntity> {

}
