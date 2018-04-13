package com.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.pojo.Orderitem;
import com.shop.pojo.Orders;
import com.shop.pojo.Users;
import com.shop.service.OrderService;
import com.shop.util.Cart;
import com.shop.util.CartItem;
import com.shop.util.PaymentUtil;
import com.shop.util.UUIDUtils;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/saveOrder")
	public String saveOrder(HttpSession session, Model model) {
		// 1.从购物车里取出信息,如果购物车里没有数据,给用户友好提示,购物车里没有东西
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			model.addAttribute("msg", "您的购物车里没有商品!!!");
			return "msg";
		}
		// 2.判断用户是否登录,
		Users users = (Users) session.getAttribute("userinfo");
		if (users == null) {
			model.addAttribute("msg", "您还未登录,登录成功后再购买吧");
			return "msg";
		}
		// 3.从购物车中取信息
		Orders orders = new Orders();
		// 保存用户信息
		orders.setOwner(users);
		// 保存订单信息
		orders.setState(1);// 未支付
		orders.setOrdertime(new Date());// 订单创建时间
		orders.setTotal(cart.getTotalCount());// 订单总价格
		// 遍历订单项
		List<Orderitem> orderitemlist = new ArrayList<Orderitem>();
		for (CartItem cartItem : cart.getCartItems()) {
			// 把购物车订单项中的信息保存到我们的订单项表里 orderitem
			Orderitem orderitem = new Orderitem();
			orderitem.setCount(cartItem.getCount());// 购买数量
			orderitem.setSubtotal(cartItem.getSubtotal());// 商品小计价格
			orderitem.setOrders(orders);// 保存订单oid
			orderitem.setProduct(cartItem.getProduct()); // 保存商品
			orderitemlist.add(orderitem);// 把订单项放到list集合中
		}
		orders.setOrderitem(orderitemlist);// 订单项
		orderService.saveOrder(orders);// 保存订单信息
		// 清空购物车
		cart.cleanCart();

		// 查询订单信息
		Orders order = orderService.findByOrderId(orders.getOid());
		model.addAttribute("order", order);

		return "order";
	}

	/**
	 * 
	 * <p>
	 * Title: orderPay
	 * </p>
	 * <p>
	 * Description: 订单支付
	 * </p>
	 * 
	 * @param order
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/orderPay")
	public void orderPay(Orders orders, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 更新order的信息
		orderService.updateOrder(orders);
		/*
		 * Properties props = new Properties(); InputStream input =
		 * Resources.getResourceAsStream("merchantInfo.properties");
		 * props.load(input);
		 */
		/******* 支付订单 *****/
		/*
		 * 准备13参数
		 */
		String p0_Cmd = "Buy"; // 业务类型， 固定值“Buy” .
		String p1_MerId = "10001126856"; // 商户编号

		// 应该在这把订单号从页面中获取到,出于数据库的设计
		String p2_Order = UUIDUtils.getUUID(); // 订单号
		String p3_Amt = "0.01"; // 支付金额
		String p4_Cur = "CNY"; // 交易币种
		String p5_Pid = ""; // 商品名称
		String p6_Pcat = ""; // 商品种类
		String p7_Pdesc = ""; // 商品描述
		String p8_Url = "http://192.168.1.102:8080/shop/back"; // 商户接收支付成功数据的地址
		String p9_SAF = ""; // 送货地址
		String pa_MP = "";
		String pd_FrpId = request.getParameter("pd_FrpId"); // 支付通道编码
		String pr_NeedResponse = "1";
		/*
		 * 计算hmac
		 */
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		/*
		 * 连接易宝的网址和13+1个参数
		 */
		StringBuilder url = new StringBuilder("https://www.yeepay.com/app-merchant-proxy/node");
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);
		System.out.println(url);
		/*
		 * 重定向到易宝
		 */
		response.sendRedirect(url.toString());
	}

	@RequestMapping("/back")
	public String back(HttpServletRequest request, Model model) throws IOException {
		/*
		 * 易宝会提供一系列的结果参数，我们获取其中4个参数即可 获取支付结果：r1_Code表示支付成功 获取支付金额：r3_Amt
		 * 获取电商的订单号：r6_Order 获取结果返回类型：r9_BType 1表示重定向返回，2表示点对点返回，
		 * 但点对点我们收不到，因为我们的ip都是局域网ip
		 */
		String r1_Code = request.getParameter("r1_Code");
		String r3_Amt = request.getParameter("r3_Amt");
		String r6_Order = request.getParameter("r6_Order");
		String r9_BType = request.getParameter("r9_BType");

		if (r1_Code.equals("1")) { // 判断支付结果是否支付成功
			if (r9_BType.equals("1")) {// 判断支付结果回调方式是否为重定向
				Orders order = orderService.findByOrderId(Integer.parseInt(r6_Order));
				order.setState(2);// 支付成功
				orderService.updateOrder(order);
				model.addAttribute("msg", "恭喜您，您的订单:" + r6_Order + "成功支付" + r3_Amt + "元！");
			}
		} else {
			model.addAttribute("msg", "对不起，您的订单支付失败，请稍候重试！");
		}
		return "msg";
	}
}
