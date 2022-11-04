package top.yinn.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.NodeParser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 扩展 hutool TreeUtil 封装系统树构建
 *
 * @author Yinn
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeBuildUtils extends TreeUtil {

    /**
     * 根据前端定制差异化字段
     */
    public static final TreeNodeConfig DEFAULT_CONFIG = TreeNodeConfig.DEFAULT_CONFIG.setNameKey("label");
    /**
     * 默认关系属性名
     */
    private static final String DEFAULT_PROPERTY_NAME = "parentId";

    /**
     * 封装树结构
     * 列表第一条数据的parentId属性值为rootId
     *
     * @param list       列表数据
     * @param nodeParser 转换器
     */
    public static <T, K> List<Tree<K>> build(List<T> list, NodeParser<T, K> nodeParser) {
        return build(list, DEFAULT_PROPERTY_NAME, nodeParser);
    }

    /**
     * 封装树结构
     * 列表第一条数据的关系属性值为rootId
     *
     * @param list                    列表数据
     * @param associationPropertyName 关系属性名
     *                                用于获取列表第一条数据的关系属性值为rootId
     * @param nodeParser              转换器
     */
    public static <T, K> List<Tree<K>> build(List<T> list, String associationPropertyName, NodeParser<T, K> nodeParser) {
        K k = ReflectUtils.invokeGetter(list.get(0), associationPropertyName);
        return build(list, k, nodeParser);
    }

    /**
     * 封装树结构
     *
     * @param list       列表数据
     * @param rootId     最顶层id
     * @param nodeParser 转换器
     * @param <T>        传入对象类型
     * @param <K>        rootId类型
     * @return 树结构
     */
    public static <T, K> List<Tree<K>> build(List<T> list, K rootId, NodeParser<T, K> nodeParser) {
        if (CollUtil.isEmpty(list)) {
            return null;
        }
        return TreeUtil.build(list, rootId, DEFAULT_CONFIG, nodeParser);
    }

}
