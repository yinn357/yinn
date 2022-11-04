package top.yinn.logging.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import top.yinn.logging.entity.OptLogDTO;

import java.util.function.Consumer;

/**
 * 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

	private Consumer<OptLogDTO> consumer;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		OptLogDTO optLog = (OptLogDTO) event.getSource();

		consumer.accept(optLog);
	}
}
