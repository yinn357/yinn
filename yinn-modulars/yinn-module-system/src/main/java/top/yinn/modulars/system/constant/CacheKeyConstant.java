package top.yinn.modulars.system.constant;

import top.yinn.core.utils.StrPool;

/**
 * 缓存的key常量
 *
 * @Author Yinn
 */
public interface CacheKeyConstant {

	String ALL = "all";

	/**
	 * 登录/授权/认证相关
	 */
	interface Auth {
		String USER = "user";
		String ROLE = "role";
		String MENU = "menu";
		String USER_ROLE = USER + StrPool.UNDERSCORE + ROLE;
		String ROlE_MENU = ROLE + StrPool.UNDERSCORE + MENU;
	}

}
