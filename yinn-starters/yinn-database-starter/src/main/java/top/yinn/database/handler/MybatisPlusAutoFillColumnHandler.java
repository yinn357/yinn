package top.yinn.database.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import top.yinn.core.constant.YinnConstant;
import top.yinn.core.context.UserContextHolder;

import java.time.LocalDateTime;

/**
 * @Author Yinn
 */
public class MybatisPlusAutoFillColumnHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		this.strictInsertFill(metaObject, YinnConstant.Database.CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
		this.strictInsertFill(metaObject, YinnConstant.Database.CREATE_USER, Long.class, UserContextHolder.getUserId());
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.strictUpdateFill(metaObject, YinnConstant.Database.UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
		this.strictUpdateFill(metaObject, YinnConstant.Database.UPDATE_USER, Long.class, UserContextHolder.getUserId());
	}
}
