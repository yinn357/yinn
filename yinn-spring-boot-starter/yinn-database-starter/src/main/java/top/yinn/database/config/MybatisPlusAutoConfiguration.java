package top.yinn.database.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.yinn.database.handler.MybatisPlusAutoFillColumnHandler;

/**
 * @Author Yinn
 */
public class MybatisPlusAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

		// 分页插件
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
		// 设置sql的limit为无限制
		paginationInnerInterceptor.setMaxLimit(-1L);
		interceptor.addInnerInterceptor(paginationInnerInterceptor);
		return interceptor;
	}

	/**
	 * 字段自动填充
	 */
	@Bean
	@ConditionalOnMissingBean
	public MybatisPlusAutoFillColumnHandler mybatisPlusAutoFillColumnHandler() {
		return new MybatisPlusAutoFillColumnHandler();
	}

	/**
	 * 长度都是8位的字符串
	 *
	 * @param machineCode
	 * @return
	 */
	// @Bean("codeGenerate")
	// public CodeGenerate codeGenerate(@Value("${id-generator.machine-code:1}") Long machineCode) {
	// 	return new CodeGenerate(machineCode.intValue());
	// }
}
