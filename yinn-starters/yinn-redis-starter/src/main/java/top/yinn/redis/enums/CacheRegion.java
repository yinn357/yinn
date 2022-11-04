package top.yinn.redis.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yinn.core.enums.BaseEnum;
import top.yinn.redis.constant.CacheRegionConstant;

/**
 * Redis 存储区域及缓存过期时间(TTL) 枚举类
 *
 * @Author Yinn
 */
@AllArgsConstructor
@Getter
public enum CacheRegion implements BaseEnum<Long> {

	/**
	 * 默认 2H
	 */
	DEFAULT(60L * 2, CacheRegionConstant.DEFAULT),
	/**
	 * 资源信息 2h
	 */
	RESOURCE(60L * 2, CacheRegionConstant.RESOURCE),
	/**
	 * 临时 2m
	 */
	TEMPORARY(2L, CacheRegionConstant.TEMPORARY),
	/**
	 * 系统配置 30d
	 */
	CONFIG(60L * 24 * 30, CacheRegionConstant.CONFIG),
	/**
	 * 字典值 7d
	 */
	DICT(60L * 24 * 7, CacheRegionConstant.DICT),
	/**
	 * 用户权限信息等 2h
	 */
	USER_RESOURCE(60L * 2, CacheRegionConstant.USER_RESOURCE),
	/**
	 * 验证码 15m
	 */
	CAPTCHA(15L, CacheRegionConstant.CAPTCHA),
	/**
	 * 半小时 30m
	 */
	HALF_HOUR(30L, CacheRegionConstant.HALF_HOUR),
	/**
	 * 一小时 1h
	 */
	ONE_HOUR(60L, CacheRegionConstant.ONE_HOUR),
	/**
	 * 一天 1d
	 */
	ONE_DAY(60L * 24, CacheRegionConstant.ONE_DAY),
	/**
	 * 一周 7d
	 */
	ONE_WEEK(60L * 24 * 7, CacheRegionConstant.ONE_WEEK),
	;

	/**
	 * 过期时间(分)
	 */
	private final Long value;
	/**
	 * 缓存区域
	 */
	@EnumValue
	private final String label;
}
