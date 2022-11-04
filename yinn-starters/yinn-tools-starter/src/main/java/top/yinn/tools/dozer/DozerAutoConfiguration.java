package top.yinn.tools.dozer;


import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import top.yinn.tools.ToolsProperties;

import java.io.IOException;


/**
 * Dozer spring auto configuration.
 * <p>
 * ConditionalOnClass：该注解的参数对应的类必须存在，否则不解析该注解修饰的配置类；
 * ConditionalOnMissingBean：该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；
 * <p>
 * http://dozer.sourceforge.net/documentation/usage.html
 * http://www.jianshu.com/p/bf8f0e8aee23
 *
 */

@EnableConfigurationProperties(ToolsProperties.class)
@ConditionalOnClass({DozerBeanMapperFactoryBean.class, Mapper.class})
@ConditionalOnMissingBean({Mapper.class})
@ConditionalOnProperty(prefix = "yinn.dozer", name = "enabled", havingValue = "true", matchIfMissing = false)
public class DozerAutoConfiguration {

    @Value("${yinn.dozer.path:classpath:dozer/*.xml}")
    private String dozerPath;

    @Autowired
    ResourcePatternResolver resourcePatternResolver;

    @Bean
    public DozerBeanMapperFactoryBean dozerMapper() throws IOException {
        //多个文件
        Resource[] resources = resourcePatternResolver.getResources(dozerPath);

        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean = new DozerBeanMapperFactoryBean();
        dozerBeanMapperFactoryBean.setMappingFiles(resources);
        return dozerBeanMapperFactoryBean;
    }

    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        DozerUtils dozerUtils = new DozerUtils(mapper);
        return dozerUtils;
    }
}
