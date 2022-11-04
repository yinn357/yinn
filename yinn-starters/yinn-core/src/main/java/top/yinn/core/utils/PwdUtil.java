package top.yinn.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import top.yinn.core.exception.code.ExceptionCode;

/**
 * 密码加密工具类
 * 可以根据自己的业务需要, 修改加密算法(如加盐等)
 *
 * @Author Yinn
 */
public class PwdUtil {

	/**
	 * 密码加密
	 */
	public static String encrypt(String pwd, String salt) {
		if (StrUtil.isEmpty(pwd)) {
			return "";
		}
		if (StrUtil.isNotEmpty(salt)) {
			pwd = salt + pwd + salt;
		}
		return DigestUtil.bcrypt(pwd);
	}

	/**
	 * 密码加密
	 */
	public static String encrypt(String pwd) {
		return encrypt(pwd, null);
	}


	/**
	 * 明文与密文对比
	 *
	 * @param pwd    明文密码
	 * @param hashed 密文
	 * @param salt   盐
	 * @return 对比结果
	 */
	public static boolean check(String pwd, String hashed, String salt) {
		ExceptionCode.ILLEGALA_ARGUMENT_EX.assertNotBlank(pwd);
		if (StrUtil.isNotEmpty(salt)) {
			pwd = salt + pwd + salt;
		}
		return DigestUtil.bcryptCheck(pwd, hashed);
	}

	/**
	 * 明文与密文对比
	 *
	 * @param pwd    明文密码
	 * @param hashed 密文
	 * @return 对比结果
	 */
	public static boolean check(String pwd, String hashed) {
		return check(pwd, hashed, null);
	}

}
