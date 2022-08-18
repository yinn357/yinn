package top.yinn.common.database.datasource;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import top.yinn.common.database.mybatis.typehandler.FullLikeTypeHandler;
import top.yinn.common.database.mybatis.typehandler.LeftLikeTypeHandler;
import top.yinn.common.database.mybatis.typehandler.RightLikeTypeHandler;
import top.yinn.common.database.parsers.TenantWebMvcConfigurer;
import top.yinn.common.database.properties.DatabaseProperties;
import top.yinn.core.base.id.IdGenerate;
import top.yinn.core.base.id.SnowflakeIdGenerate;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis 常用重用拦截器
 *
 */
public abstract class BaseMybatisConfiguration {
    private final DatabaseProperties databaseProperties;

    public BaseMybatisConfiguration(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    /**
     * 分页插件，自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();

        if (this.databaseProperties.getIsBlockAttack()) {
            // 攻击 SQL 阻断解析器 加入解析链
            sqlParserList.add(new BlockAttackSqlParser());
        }

        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }


    /**
     * Mybatis Plus 注入器
     *
     * @param idGenerate
     * @return
     */
    @Bean("myMetaObjectHandler")
    public MetaObjectHandler getMyMetaObjectHandler(@Qualifier("snowflakeIdGenerate") IdGenerate<Long> idGenerate) {
        return new MyMetaObjectHandler(idGenerate);
    }

    /**
     * id生成 机器码， 单机配置1即可。 集群部署，每个实例自增1即可。
     *
     * @param machineCode
     * @return
     */
    @Bean("snowflakeIdGenerate")
    public IdGenerate getIdGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
        return new SnowflakeIdGenerate(machineCode);
    }

    /**
     * 租户信息拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "pinda.database.isMultiTenant", havingValue = "true", matchIfMissing = true)
    public TenantWebMvcConfigurer getTenantWebMvcConfigurer() {
        return new TenantWebMvcConfigurer();
    }

    /**
     * Mybatis 自定义的类型处理器
     * 用于左模糊查询时使用
     * <p>
     * eg：
     * and name like #{name,typeHandler=leftLike}
     *
     * @return
     */
    @Bean
    public LeftLikeTypeHandler getLeftLikeTypeHandler() {
        return new LeftLikeTypeHandler();
    }

    /**
     * Mybatis 自定义的类型处理器
     * 用于右模糊查询时使用
     * <p>
     * eg：
     * and name like #{name,typeHandler=rightLike}
     *
     * @return
     */
    @Bean
    public RightLikeTypeHandler getRightLikeTypeHandler() {
        return new RightLikeTypeHandler();
    }

    /**
     * Mybatis 自定义的类型处理器
     * 用于全模糊查询时使用
     * <p>
     * eg：
     * and name like #{name,typeHandler=fullLike}
     *
     * @return
     */
    @Bean
    public FullLikeTypeHandler getFullLikeTypeHandler() {
        return new FullLikeTypeHandler();
    }
}
