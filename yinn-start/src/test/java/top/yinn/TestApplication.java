package top.yinn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
	public void getAll() {
		List<AuthUserEntity> list = authUserService.list();
		System.out.println(list);
	}
}
