package xyz.hbs.huarongdao.configure;

/**
 * 整个应用通用的常量 <br>
 * <b>类描述:</b>
 *
 * <pre>
 * |
 * </pre>
 *
 * @see
 * @since
 */
public class CommonConstant {
	/**
	 * 用户对象放到Session中的键名称
	 */
	public static final String USER_CONTEXT = "USER_CONTEXT";
	public static final String USER_IDENTIFY_CODE = "USER_IDENTIFY_CODE";

	
	
	/**
	 * 将登录前的URL放到Session中的键名称
	 */
	public static final String LOGIN_TO_URL = "toUrl";

	/**
	 * 每页的记录数
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * 默认学院/系部/班级
	 * */
	public static final String DEFAULT_SCHOOL_NAME="(默认学院)";
	public static final String DEFAULT_DEPARTMENT_NAME="(默认系部)";
	public static final String DEFAULT_NATURALCLASS_NAME="(默认班级)";
	
	
	public static final int HOMEWORK_FLAG_INDIVIDUALITY=1;//个人作业
	public static final int HOMEWORK_FLAG_GROUP=2;//小组作业
	
	//文件个数
	public static final int HOMEWORK_FILE_COUNT_INFINITE=-1;//无限制
	
	public static final int HOMEWORK_FILE_TYPE_FILE=0;//文件，限制文件类型
	public static final int HOMEWORK_FILE_TYPE_FILE_NOT_LIMITED=1;//文件,不限制文件类型
	public static final int HOMEWORK_FILE_TYPE_DIRECTORY=2;//目录
	
	public static final int HOMEWORK_FILE_NAME_FORMAT_NOT_LIMITED=2;//文件名称不受限制
	
	public static final String LAST_ACCESS_TIME = "LastAccessTime";
	public static final String ACCESS_COUNT = "AccessCount";
	
	public static final int  FREQUENT_ACCESS_WAITING_TIME=30;//频繁访问需要等待30秒

	/**
	 * 上传项目文件路径
	 */
	public static String PROJECT_UPLOAD_FILES_PATH = "static/project/uploadfiles/";
}