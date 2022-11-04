package top.yinn.database.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yinn.core.constant.YinnConstant;
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

	/**
	 * 根据任何一个字段查询一条数据
	 *
	 * @param column 列
	 * @param val    值
	 * @return Entity Or null
	 */
	public E getOne(SFunction<E, ?> column, Object val) {
		return this.getOne(
				new LambdaQueryWrapper<E>()
						.eq(column, val)
						.last(YinnConstant.Database.SQL_LIMIT_1)
		);
	}
}
