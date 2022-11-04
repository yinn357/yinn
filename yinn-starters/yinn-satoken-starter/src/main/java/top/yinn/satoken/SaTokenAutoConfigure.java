package top.yinn.satoken;

import cn.dev33.satoken.jwt.StpLogicJwtForMixin;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.jwt.StpLogicJwtForStateless;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @Author Yinn
 */

public class SaTokenAutoConfigure {

	/**
	 * Sa-Token 整合 jwt (Simple 简单模式)
	 */
	@Bean
	@ConditionalOnProperty(name = "yinn.sa-token.jwt", havingValue = "Simple")
	public StpLogic getStpLogicJwtForSimple() {
		return new StpLogicJwtForSimple();
	}

	/**
	 * Sa-Token 整合 jwt (Mixin 混入模式)
	 */
	@Bean
	@ConditionalOnProperty(name = "yinn.sa-token.jwt", havingValue = "Mixin", matchIfMissing = true)
	public StpLogic getStpLogicJwtForMixin() {
		return new StpLogicJwtForMixin();
	}

	/**
	 * Sa-Token 整合 jwt (Stateless 无状态模式)
	 */
	@Bean
	@ConditionalOnProperty(name = "yinn.sa-token.jwt", havingValue = "Stateless")
	public StpLogic getStpLogicJwtForStateless() {
		return new StpLogicJwtForStateless();
	}
}

