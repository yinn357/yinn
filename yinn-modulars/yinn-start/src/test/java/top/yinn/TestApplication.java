package top.yinn;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.yinn.core.model.PageParam;
import top.yinn.core.model.PageResult;
import top.yinn.modulars.system.model.vo.UserVO;
import top.yinn.modulars.system.service.UserService;
import top.yinn.modulars.test.model.entity.AuthUserEntity;
import top.yinn.modulars.test.service.AuthUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Yinn
 */

@SpringBootTest
public class TestApplication {

	@Resource
	AuthUserService authUserService;

	@Resource
	UserService userService;

	@Test
	public void getAll() {
		List<AuthUserEntity> list = authUserService.list();
		System.out.println(list);
	}

	@Test
	public void getUserPage() {
		PageParam pageParam = new PageParam().setPageNum(1).setPageSize(2);
		PageResult<UserVO> list = userService.list(pageParam, null);
		System.out.println(list);
	}

	@Test
	public void testSecureUtil() {
		String pwd = "123456";
		String v = "$2a$10$gTN4UHGHYvMM9TQRQMaznOHL/wnEHS7pbEp4AJ1glYKYAZgzEY.KK";
		String bcrypt = DigestUtil.bcrypt(pwd);
		boolean b = DigestUtil.bcryptCheck(pwd, v);
		System.out.println(b);
		System.out.println(bcrypt);
	}
}
