package win.likie.point.utils;

/**
 * 系统参数类
 */
public interface  SysParamUtil {
	
	//分页每页数量
	public String PAGE_SIZE = "10";//14
	
	//系统初始加载页
	public String WEB_INDEX = "/framework/index";
	
	//系统页面跳转注解
	public String FORWARD_DO = "/framework/controller/public/page_lct.do";
	
	//WEB-INF下jsp目录路径
	public String WEB_PAGE = "/WEB-INF/pages";
	
	//没有权限提示语  
	public String NO_POWER = "您没有操作此功能的权限，请返回!";
	
	//未登录提示语  
	public String NO_LOGIN = "您没有登录或登录已失效，请重新登录!";
	
	//产品订购数属性编码
	public String PRODUCT_BUY_NUM = "LICENSE_NUM";
}
