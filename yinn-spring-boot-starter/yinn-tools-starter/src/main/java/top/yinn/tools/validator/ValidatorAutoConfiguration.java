package top.yinn.tools.validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 验证器配置 开启快速返回
 *
 */
@ConditionalOnProperty(prefix = "yinn.validator", name = "enabled" ,havingValue = "true" ,matchIfMissing = true)
public class ValidatorAutoConfiguration {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                //快速失败返回模式
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    // /**
    //  * Method:  开启快速返回
    //  * Description:
    //  * 如果参数校验有异常，直接抛异常，不会进入到 controller，使用全局异常拦截进行拦截
    //  *
    //  * @param
    //  * @return org.springframework.validation.beanvalidation.MethodValidationPostProcessor
    //  */
    // @Bean
    // public MethodValidationPostProcessor methodValidationPostProcessor(Validator validator) {
    //     MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
    //     /**设置validator模式为快速失败返回*/
    //     postProcessor.setValidator(validator);
    //     return postProcessor;
    // }

    // @Bean
    // public IConstraintExtract constraintExtract() {
    //     return new DefaultConstraintExtractImpl(validator());
    // }
    //
    // @Bean
    // public FormValidatorController getFormValidatorController(RequestMappingHandlerMapping requestMappingHandlerMapping) {
    //     return new FormValidatorController(constraintExtract(), requestMappingHandlerMapping);
    // }
}