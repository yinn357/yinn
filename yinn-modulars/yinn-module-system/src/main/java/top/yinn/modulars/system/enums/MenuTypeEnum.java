package top.yinn.modulars.system.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import top.yinn.core.enums.BaseEnum;


/**
 * 菜单类型枚举类
 *
 * @author Yinn
 */
@AllArgsConstructor
@Getter
public enum MenuTypeEnum implements BaseEnum<String> {

    /**
     * 可以认为是父级菜单
     */
    DIR("D", "目录"),

    /**
     * 可以认为是子菜单
     */
    MENU("M", "菜单"),

    /**
     * 可以认为是页内按钮
     */
    BUTTON("B", "按钮"),

    /**
     * 外链
     */
    EXTERNAL_LINK("E", "外链"),
    ;

    @EnumValue
    private final String value;
    private final String label;

}
