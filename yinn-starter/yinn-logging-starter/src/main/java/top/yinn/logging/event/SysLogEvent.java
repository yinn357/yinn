package top.yinn.logging.event;


import org.springframework.context.ApplicationEvent;
import top.yinn.logging.entity.OptLogDTO;

/**
 * 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	public SysLogEvent(OptLogDTO source) {
		super(source);
	}
}
