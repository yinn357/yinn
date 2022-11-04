package top.yinn.j2cache.constant;

/**
 * @Author Yinn
 * 关于缓存的 Region集合.
 */

public interface CacheRegionConstant {
	/**
	 * 默认 2H
	 */
	String DEFAULT = "default";

	/**
	 * 资源信息 2h
	 */
	String RESOURCE = "resource";

	/**
	 * 系统配置 30d
	 */
	String CONFIG = "config";

	/**
	 * 字典值 7d
	 */
	String DICT = "dict";

	/**
	 * 用户权限信息等 2h
	 */
	String USER_RESOURCE = "user_resource";

	/**
	 * 验证码 15m
	 */
	String CAPTCHA = "captcha";

	/**
	 * 半小时 30m
	 */
	String HALF_HOUR = "half_hour";

	/**
	 * 一小时 1h
	 */
	String ONE_HOUR = "one_hour";

	/**
	 * 一天 1d
	 */
	String ONE_DAY = "one_day";

	/**
	 * 一周 7d
	 */
	String ONE_WEEK = "one_week";
}
