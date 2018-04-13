package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.UsersMapper;
import com.shop.pojo.Users;
import com.shop.pojo.UsersExample;
import com.shop.pojo.UsersExample.Criteria;
import com.shop.service.UsersService;
import com.shop.util.MailUtils;
import com.shop.util.UUIDUtils;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;

	/**
	 * 
	 * <p>
	 * Title: getListUsers
	 * </p>
	 * <p>
	 * Description:查询全部用户
	 * </p>
	 * 
	 * @return
	 * @see com.shop.service.UsersService#getListUsers()
	 */
	public List<Users> getListUsers() {
		UsersExample example = new UsersExample();

		return usersMapper.selectByExample(example);
	}

	/**
	 * 
	 * <p>
	 * Title: saveUser
	 * </p>
	 * <p>
	 * Description:用户注册
	 * </p>
	 * 
	 * @param user
	 * @see com.shop.service.UsersService#saveUser(com.shop.pojo.Users)
	 */
	public void saveUser(Users user) {
		// 激活码
		// 用户状态
		// 数据补全
		user.setState(0);// 0.未激活 1.已激活
		// 使用uuid的方式生成一个128位字符
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		// 发送一下邮件
		try {
			MailUtils.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		usersMapper.insert(user);
	}

	/**
	 * 
	 * <p>
	 * Title: findUsersByCode
	 * </p>
	 * <p>
	 * Description:根据激活码查询用户信息
	 * </p>
	 * 
	 * @param code
	 * @return
	 * @see com.shop.service.UsersService#findUsersByCode(java.lang.String)
	 */
	public Users findUsersByCode(String code) {
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<Users> list = usersMapper.selectByExample(example);
		if (list.size() > 0) {
			Users user = list.get(0);
			return user;
		}
		return null;
	}

	/**
	 * 修改用户信息
	 * <p>
	 * Title: updateUser
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param user
	 * @see com.shop.service.UsersService#updateUser(com.shop.pojo.Users)
	 */
	public void updateUser(Users user) {
		usersMapper.updateByPrimaryKey(user);

	}

	/**
	 * 
	 * <p>
	 * Title: loginUser
	 * </p>
	 * <p>
	 * Description:用户登录功能
	 * </p>
	 * 
	 * @param username
	 * @param password
	 * @param state
	 *            0 未激活 1.已激活
	 * @return
	 * @see com.shop.service.UsersService#loginUser(java.lang.String,
	 *      java.lang.String, int)
	 */

	public Users loginUser(String username, String password, int state) {
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		// 添加查询条件
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		criteria.andStateEqualTo(state);
		// 执行查询方法
		List<Users> list = usersMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);// 返回当前对象
		}
		return null;
	}

}
