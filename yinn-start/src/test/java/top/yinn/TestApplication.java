package top.yinn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.yinn.modulars.test.model.entity.AuthUserEntity;
import top.yinn.modulars.test.service.AuthUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Yinn
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YinnApplication.class)
public class TestApplication {

	@Resource
	AuthUserService authUserService;

	@Test
	public void getAll() {
		List<AuthUserEntity> list = authUserService.list();
		System.out.println(list);
	}
}
