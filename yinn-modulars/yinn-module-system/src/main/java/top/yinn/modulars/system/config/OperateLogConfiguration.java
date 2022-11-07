package top.yinn.modulars.system.config;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yinn.logging.event.SysLogListener;
import top.yinn.modulars.system.model.dto.OperateLogInsertOrUpdateDTO;
import top.yinn.modulars.system.service.OperateLogService;

/**
 * 操作日志配置类
 *
 * @Author Yinn
 */
@Configuration
public class OperateLogConfiguration {

	/**
	 * 配置日志监听器组件
	 * 保存操作日志
	 */
	@Bean
	@ConditionalOnMissingBean
	public SysLogListener sysLogListener(OperateLogService operateLogService) {
		return new SysLogListener(optLogDTO ->
				operateLogService.saveOrUpdate(BeanUtil.toBean(optLogDTO, OperateLogInsertOrUpdateDTO.class))
		);
	}
}
