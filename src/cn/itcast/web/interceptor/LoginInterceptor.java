package cn.itcast.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

//登陆校验拦截器
//指定不校验 login|regist
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获得session中的登陆标识
		Object obj = ActionContext.getContext().getSession().get("user");
		if(obj!=null){
			//获得到=>已经登陆,放行
			return invocation.invoke();
		}
		//未获得到=>未登陆,重定向到登陆页面
		return "toLogin";
	}

}
