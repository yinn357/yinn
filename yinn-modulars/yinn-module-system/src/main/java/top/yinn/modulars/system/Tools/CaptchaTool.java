package top.yinn.modulars.system.Tools;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import net.oschina.j2cache.CacheChannel;
import org.springframework.stereotype.Component;
import top.yinn.j2cache.constant.CacheKeyConstant;

/**
 * 验证码助手类；可将验证码答案缓存至 Redis
 *
 * @author Yinn
 */
@Component
@RequiredArgsConstructor
public class CaptchaTool {

	private final CacheChannel cacheChannel;

	private static final String CACHE_KEY_CAPTCHA_ANSWER = "uuid_%s" ;


	/**
	 * 生成一个验证码图片对象
	 *
	 * @param uuid UUID
	 * @return ShearCaptcha
	 */
	public ShearCaptcha generate(String uuid) {
		// 定义图形验证码的长、宽、验证码字符数、干扰线宽度
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(196, 50, 4, 4);

		// 将验证码答案保存至 redis , 有效期15分钟
		cacheChannel.set(CacheKeyConstant.CAPTCHA, String.format(CACHE_KEY_CAPTCHA_ANSWER, uuid), captcha);

		return captcha;
	}

	/**
	 * 校验验证码是否输入正确
	 *
	 * @param uuid          UUID
	 * @param captchaAnswer 验证码答案
	 * @return 是否正确
	 */
	public boolean validate(String uuid, String captchaAnswer, boolean removeWhenEquals) {
		if (StrUtil.hasBlank(uuid, captchaAnswer)) {
			return false;
		}

		String cacheKey = String.format(CACHE_KEY_CAPTCHA_ANSWER, uuid);
		String answerInRedis = (String) cacheChannel.get(CacheKeyConstant.CAPTCHA, cacheKey).getValue();
		boolean equals = StrUtil.equalsIgnoreCase(answerInRedis, captchaAnswer);
		if (equals && removeWhenEquals) {
			cacheChannel.evict(CacheKeyConstant.CAPTCHA, cacheKey);
		}

		return equals;
	}
}