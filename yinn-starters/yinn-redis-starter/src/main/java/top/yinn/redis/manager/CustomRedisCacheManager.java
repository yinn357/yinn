package top.yinn.redis.manager;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import top.yinn.core.enums.BaseEnum;
import top.yinn.core.utils.StrPool;
import top.yinn.core.utils.StringUtils;
import top.yinn.redis.enums.CacheRegion;

import java.time.Duration;
import java.util.Optional;

/**
 * 自定义RedisCacheManager：
 * 按缓存名称设置生存时间
 * 若@Cacheable value中包含’{@link CacheRegion}‘中label 设置对应缓存时间
 * 不存在则设置默认缓存时间
 *
 * @Author Yinn
 */
public class CustomRedisCacheManager extends RedisCacheManager {
	/**
	 * 提供默认构造器
	 */
	public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
		super(cacheWriter, defaultCacheConfiguration);
	}


	/**
	 * 重写父类createRedisCache方法
	 *
	 * @param name        @Cacheable中的value
	 * @param cacheConfig
	 * @return
	 */
	@Override
	protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
		// 默认时长
		Long ttl = CacheRegion.DEFAULT.getValue();
		// 缓存key不等空,并且包含":"
		if (StringUtils.isNotEmpty(name)) {
			String[] split = StringUtils.split(name, StrPool.COLON);
			// 根据label 获取 枚举
			Optional<CacheRegion> cacheRegion = BaseEnum.findByLabel(CacheRegion.class, split[0]);
			if (cacheRegion.isPresent()) {
				ttl = cacheRegion.get().getValue();
			}
		}
		return super.createRedisCache(name, cacheConfig.entryTtl(Duration.ofMinutes(ttl)));
	}
}
