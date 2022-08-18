package top.yinn.j2cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 覆盖 SpringCache 相关配置
 */
public class MyCacheConfig extends CachingConfigurerSupport {

    final String COLON = ":";

    /**
     * 解决注解：Cacheable 没有指定key时，会将key生成为 ${value}:SimpleKey []
     * eg： @Cacheable(value = "yinn") ->  yinn:SimpleKey []
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(COLON);
            sb.append(method.getName());
            for (Object obj : objects) {
                if (obj != null) {
                    sb.append(COLON);
                    sb.append(obj.toString());
                }
            }
            return sb.toString();
        };
    }

}
