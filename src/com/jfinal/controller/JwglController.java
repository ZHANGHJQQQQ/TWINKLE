package com.jfinal.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.common.model.Jwgl;
import com.jfinal.interceptor.JLSuccessInterceptor;
import com.jfinal.interceptor.JwglInterceptor;
import com.jfinal.service.JwglService;
import com.jfinal.validator.JEValidator;
import com.twinkle.init.BaseController;
import com.twinkle.init.Constant;

public class JwglController extends BaseController {

	static JwglService jwglService = new JwglService();

	public void index() { // ------首页
		render("/pages/page_jwgl.html");
	}

	@Before({ JEValidator.class, JwglInterceptor.class })
	public void login() { // ------登录页面

		String admin = getPara("admin");
		String pass = getPara("pass");
		String checks = getPara("checks"); // null---off on---on

		Jwgl jwgl;

		if ("on".equals(checks)) {

			jwgl = jwglService.toLogin(admin, pass);

			if (jwgl == null) {
				render("comm/error/error.html");
				return;
			}

			if (jwgl.getCode() != Constant.SUCCESS) {						
				setAttr("TipMsg", jwgl.getTip());
				render("/pages/page_jwgl.html");
				return;
			} else {
				setAttr("jwgl", jwgl);
				render("/pages/page_jwgl_info.html");
				return;
			}
		}

		jwgl = jwglService.findByAdmin(admin, pass);
		
		if (jwgl == null) {
			setAttr("TipMsg", "用户名或者密码错误! <br> 如果是第一次登录，请勾选 ‘新登录’");
			render("/pages/page_jwgl.html");
			return;
		}

		setAttr("jwgl", jwgl);
		render("/pages/page_jwgl_info.html");
	}

	@Before(JLSuccessInterceptor.class)
	public void login_success() { // ---------登录成功页面
		render("/pages/page_jwgl_info.html");
	}


	public void api() { // 纯api接口 -----优先检测登录数据，没有再纯新登录

		String admin = getPara("admin");
		String pass = getPara("pass");
		String news = getPara("news");
		
		if (news != null && "yes".equals(news)) {
			renderJson(JSON.toJSONString(jwglService.toLogin(admin, pass)));
			return ;
		} 	
			renderJson(JSON.toJSONString(jwglService.findByAdmin(admin, pass)));

	}



}
