package top.yinn.core.constant;

/**
 * @Author Yinn
 */
public interface YinnConstant {

	interface Version {
		/**
		 * HTTP API 版本 v1
		 */
		String HTTP_API_VERSION_V1 = "/api/v1";

		/**
		 * DUBBO API 版本 v1
		 */
		String DUBBO_VERSION_V1 = "1.0.0";
	}

	interface Message {
		String NULL = "暂无数据";
		String SUCCESS = "操作成功";
	}

	interface Database {
		String FIELD_ID = "id";
		String CREATE_TIME = "createTime";
		String CREATE_USER = "createUser";
		String UPDATE_TIME = "updateTime";
		String UPDATE_USER = "updateUser";
		String DELETED = "deleted";
		String VERSION = "version";
		String SQL_LIMIT_1 = " LIMIT 1";
	}

	interface Jackson {
		String DATE_FORMAT = "yyyy-MM-dd";
		String TIME_FORMAT = "HH:mm:ss";
		String DATE_TIME_FORMAT = Jackson.DATE_FORMAT + " " + Jackson.TIME_FORMAT;
	}

	/**
	 * CRUD权限 命名
	 */
	interface Permission {
		String CREATE = "create";
		String RETRIEVE = "retrieve";
		String UPDATE = "update";
		String DELETE = "delete";
	}
}
