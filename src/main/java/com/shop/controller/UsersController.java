package com.shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.pojo.Users;
import com.shop.service.UsersService;
import com.shop.util.ShopMsgResult;

@Controller
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 * 
	 * <p>
	 * Title: getUserList
	 * </p>
	 * <p>
	 * Description: 测试数据,查询全部
	 * </p>
	 * 
	 * @return
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public List<Users> getUserList() {
		return usersService.getListUsers();

	}

	// 用户注册
	@RequestMapping("/register")
	public String registerUser(Users user, Model model) {
		usersService.saveUser(user);
		model.addAttribute("msg", "用户注册成功,请您到邮箱激活!!");
		return "msg";
	}

	@RequestMapping("/active")
	public String activeUser(String code, Model model) {
		Users user = usersService.findUsersByCode(code);
		if (user != null) {
			user.setState(1);// 改变用户状态
			usersService.updateUser(user);
			model.addAttribute("msg", "恭喜您,您的用户已成功激活!!");
			return "msg";
		} else {
			model.addAttribute("msg", "恭喜您,用户激活失败!!");
			return "msg";
		}
	}

	/**
	 * 
	 * <p>
	 * Title: login
	 * </p>
	 * <p>
	 * Description: 登录功能
	 * </p>
	 * 
	 * @param username
	 * @param password
	 * @param captcha
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ShopMsgResult login(String username, String password, String captcha, HttpSession session) {
		ShopMsgResult result = new ShopMsgResult();
		// 1.获取验证码,验证码输入有误
		String sessionCode = (String) session.getAttribute("sessionCode");
		// 忽略大小的比较
		if (captcha.equalsIgnoreCase(sessionCode)) {
			// 判断登录
			Users users = usersService.loginUser(username, password, 1);
			// 判断能否从数据库中查询出数据
			if (users != null) {
				// 登录成功给提示信息
				result.setStates(200);// 查询成功
				session.setAttribute("userinfo", users);// 把用户信息放到session里
			} else {// 登录失败
				result.setStates(500);
				result.setMsg("用户名或者密码错误");
			}
		} else {// 验证失败
			result.setStates(500);//
			result.setMsg("验证码输入有误!!");
		}
		return result;
	}

	/**
	 * 
	 * <p>
	 * Title: loginOut
	 * </p>
	 * <p>
	 * Description: 退出功能
	 * </p>
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String loginOut(HttpSession session) {
		// 清空session信息
		session.setAttribute("userinfo", null);
		// session.invalidate();// 清空
		return "index";
	}

}
