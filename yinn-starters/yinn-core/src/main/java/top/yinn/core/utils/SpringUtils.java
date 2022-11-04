package top.yinn.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
@Slf4j
@SuppressWarnings("squid:S1166")
public class SpringUtils {
    private static ApplicationContext applicationContext;
    private static ApplicationContext parentApplicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext ctx) {
        Assert.notNull(ctx, "SpringUtil injection ApplicationContext is null");
        applicationContext = ctx;
        parentApplicationContext = ctx.getParent();
    }

    public static Object getBean(String name) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        try {
            return applicationContext.getBean(name);
        } catch (Exception e) {
            return parentApplicationContext.getBean(name);
        }
    }

    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "SpringUtil name is null or empty");
        Assert.notNull(type, "SpringUtil type is null");
        try {
            return applicationContext.getBean(name, type);
        } catch (Exception e) {
            return parentApplicationContext.getBean(name, type);
        }
    }

	public static <T> T getBean(Class<T> type) {
		Assert.notNull(type, "SpringUtil type is null");
		try {
			return applicationContext.getBean(type);
		} catch (Exception e) {
			return parentApplicationContext.getBean(type);
		}
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> type) {
		Assert.notNull(type, "SpringUtil type is null");
		try {
			return applicationContext.getBeansOfType(type);
		} catch (Exception e) {
			return parentApplicationContext.getBeansOfType(type);
		}
	}


	/**
	 * 输出项目基本信息
	 *
	 * @param application main
	 * @author Yinn
	 */
	public static void appLog(ConfigurableApplicationContext application) {
		try {
			Environment env = application.getEnvironment();
			String serverPort = env.getProperty("server.port");
			String contextPath = Optional
					.ofNullable(env.getProperty("server.servlet.context-path"))
					.filter(StrUtil::isNotBlank)
					.orElse("/");
			String hostAddress = "localhost";
			try {
				hostAddress = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				log.warn("The host name could not be determined, using `localhost` as fallback");
			}

			StaticLog.info("\n----------------------------------------------------------\n\t" +
							"Application '{}' is running! Access URLs:\n\t" +
							"Local: \t\thttp://localhost:{}{}\n\t" +
							"External: \thttp://{}:{}{}\n\t" +
							"Doc:       \thttp://{}:{}{}doc.html\n\t" +
							"Profile(s): {}\n----------------------------------------------------------",
					env.getProperty("spring.application.name"),
					serverPort,
					contextPath,
					hostAddress,
					serverPort,
					contextPath,
					hostAddress,
					serverPort,
					contextPath,
					env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
