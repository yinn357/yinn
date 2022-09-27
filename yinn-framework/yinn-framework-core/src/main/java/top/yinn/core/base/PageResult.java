package top.yinn.core.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页查询结果
 *
 * @author Yinn
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T extends Serializable> implements Serializable {

	@ApiModelProperty(value = "当前页")
	private long current;

	@ApiModelProperty(value = "当前页数量")
	private long size;

	@ApiModelProperty(value = "总量")
	private long total;

	@ApiModelProperty(value = "记录")
	private List<T> records;


	/**
	 * 构造方法 - 用于空集合结果
	 *
	 * @param pageParam 分页查询参数
	 */
	public PageResult(PageParam pageParam) {
		this.current = pageParam.getPageNum();
		this.size = pageParam.getPageSize();
		this.total = 0L;
		this.records = Collections.emptyList();
	}

	/**
	 * MyBatis Page转换为 PageResult
	 *
	 * @param entityPage Mybatis分页对象
	 * @param targetType 列表目标实体
	 * @param <T>
	 * @return PageResult
	 */
	public static <T extends Serializable> PageResult<T> convert(IPage<?> entityPage, Class<T> targetType) {
		return new PageResult<T>()
				.setCurrent(entityPage.getCurrent())
				.setSize(entityPage.getSize())
				.setTotal(entityPage.getTotal())
				.setRecords(BeanUtil.copyToList(entityPage.getRecords(), targetType))
				;
	}

}