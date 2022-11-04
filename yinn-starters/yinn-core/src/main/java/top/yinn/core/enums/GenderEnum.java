package top.yinn.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 生理性别枚举类
 *
 * @author Yinn
 */
@AllArgsConstructor
@Getter
public enum GenderEnum implements BaseEnum<String> {

	/**
	 * 未知
	 */
	UNKNOWN("N", "未知"),

	/**
	 * 男
	 */
	MALE("M", "男"),

	/**
	 * 女
	 */
	FEMALE("W", "女"),
	;

	@EnumValue
	private final String value;
	private final String label;

}
