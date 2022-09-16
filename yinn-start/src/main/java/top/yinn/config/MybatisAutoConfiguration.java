package top.yinn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import top.yinn.common.database.datasource.BaseMybatisConfiguration;
import top.yinn.common.database.properties.DatabaseProperties;

/**
 * mybatis框架相关的配置
 *
 * @Author Yinn
 */

@Slf4j
@Configuration
public class MybatisAutoConfiguration extends BaseMybatisConfiguration {

	public MybatisAutoConfiguration(DatabaseProperties databaseProperties) {
		super(databaseProperties);
	}
}
