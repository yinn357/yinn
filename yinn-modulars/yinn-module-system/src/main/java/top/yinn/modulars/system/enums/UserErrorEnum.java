package top.yinn.modulars.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yinn.core.enums.BaseEnum;

/**
 * 后台管理异常枚举类
 *
 * @author Yinn
 */
@AllArgsConstructor
@Getter
public enum UserErrorEnum implements BaseEnum<Integer> {


	INCORRECT_PIN_OR_PWD(40001, "账号或密码不正确"),

	BANNED_USER(40001, "用户被封禁"),

	INCORRECT_OLD_PASSWORD(40001, "原密码有误"),

	USER_ID_INVALID(40001, "无效的用户ID"),

	NO_ROLE_AVAILABLE_FOR_CURRENT_USER(40001, "当前用户没有可用角色"),

	NO_MENU_AVAILABLE_FOR_CURRENT_ROLE(40001, "当前角色没有可用菜单"),


	;

	private final Integer value;
	private final String label;

}