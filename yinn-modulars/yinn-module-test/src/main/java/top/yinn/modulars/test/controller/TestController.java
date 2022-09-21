package top.yinn.modulars.test.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Yinn
 */

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
		return "name:" + name;
	}


}
