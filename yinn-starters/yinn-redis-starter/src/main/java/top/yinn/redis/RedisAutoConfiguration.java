package top.yinn.redis;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.redisson.api.RedissonClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import top.yinn.core.utils.StrPool;
import top.yinn.redis.lock.RedisDistributedLock;
import top.yinn.redis.lock.impl.RedisDistributedLockImpl;
import top.yinn.redis.manager.CustomRedisCacheManager;
import top.yinn.redis.util.RedisUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


/**
 * Redis 自动配置类
 *
 * @author Yinn
 */

@EnableCaching
@AutoConfigureBefore({RedissonAutoConfiguration.class})
@ConditionalOnClass(RedisConnectionFactory.class)
public class RedisAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(name = {"redisTemplate"})
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(factory);

        // 指定相应的序列化方案
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer(getObjectMapper());

        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);

        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);

        return redisTemplate;
    }

    /**
     * 重新定义 ObjectMapper对象，使其兼容不可序列化的类型
     */
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.registerModule(new Jdk8Module());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setTimeZone(Calendar.getInstance().getTimeZone());
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        return mapper;
    }

    /**
     * 实例化自定义的缓存管理器
     */
    @Bean
    @ConditionalOnMissingBean({RedisCacheManager.class})
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .computePrefixWith(name -> name.concat(StrPool.COLON))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
        //return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
        return new CustomRedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }

    /**
     * 基于 Redisson 的分布式锁
     */
    @Bean
    @ConditionalOnMissingBean({RedisDistributedLock.class})
    public RedisDistributedLock redisDistributedLock(RedissonClient redissonClient) {
        return new RedisDistributedLockImpl(redissonClient);
    }

    /**
     * redis 工具类
     */
    @Bean
    @ConditionalOnMissingBean({RedisUtil.class})
    public RedisUtil redisUtil(RedisTemplate redisTemplate) {
        return new RedisUtil(redisTemplate);
    }

    /**
     * 缓存键名生成规则
     */
    @Bean
    @ConditionalOnMissingBean({KeyGenerator.class})
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder(64);
            sb.append(target.getClass().getName());
            sb.append(StrPool.COLON);
            sb.append(method.getName());
            for (Object obj : objects) {
                if (obj != null) {
                    sb.append(StrPool.COLON).append(obj);
                }
            }
            return sb.toString();
        };
    }
}
