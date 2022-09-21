package top.yinn.database.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yinn.database.entity.Entity;
import top.yinn.database.service.BaseService;

/**
 * @Author Yinn
 */
public class BaseServiceImpl<M extends BaseMapper<E>, E extends Entity<?>> extends ServiceImpl<M, E> implements BaseService<E> {

}
