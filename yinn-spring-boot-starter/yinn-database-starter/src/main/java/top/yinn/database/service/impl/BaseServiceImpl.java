package top.yinn.database.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yinn.database.entity.SuperEntity;
import top.yinn.database.service.BaseService;

/**
 * 服务实现类基础模板
 * M = MAPPER 持久层 interface
 * E = ENTITY 实体类
 *
 * @Author Yinn
 */
public class BaseServiceImpl<M extends BaseMapper<E>, E extends SuperEntity<?>> extends ServiceImpl<M, E> implements BaseService<E> {

}
