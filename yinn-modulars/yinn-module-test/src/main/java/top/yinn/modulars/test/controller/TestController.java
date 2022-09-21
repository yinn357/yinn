package top.yinn.modulars.test.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Yinn
 */

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

	@ApiOperation(value = "详情", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/{id}")
	public Object getById(@PathVariable Long id) {
		return "id:" + id;
	}

	@ApiOperation(value = "详情By名称", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(value = "/name/{name}")
	public Object getByName(@PathVariable String name) {
		log.info("123123123123");
		return "name:" + name;
	}


}
