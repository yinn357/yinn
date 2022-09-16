package top.yinn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.yinn.core.utils.SpringUtils;

/**
 * @Author Yinn
 */

@SpringBootApplication
public class YinnApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(YinnApplication.class, args);
		SpringUtils.appLog(context);
	}
}
