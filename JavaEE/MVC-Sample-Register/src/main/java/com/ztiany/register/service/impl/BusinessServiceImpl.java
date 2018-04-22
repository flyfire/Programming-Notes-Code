package com.ztiany.register.service.impl;

import com.ztiany.register.dao.UserDao;
import com.ztiany.register.dao.impl.UserDaoImpl;
import com.ztiany.register.domain.User;
import com.ztiany.register.exception.UserExistException;
import com.ztiany.register.service.BusinessService;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.4.22 18:05
 */
public class BusinessServiceImpl implements BusinessService {

    private final UserDao mUserDao = new UserDaoImpl();

    @Override
    public void register(User user) throws UserExistException {
        if (user == null) {
            throw new NullPointerException();
        }
        //如果已经注册过
        if (mUserDao.findUser(user.getUsername()) != null) {
            throw new UserExistException();
        }
        //保存数据
        mUserDao.save(user);
    }


    @Override
    public User login(String username, String password) {
        return mUserDao.findUser(username, password);
    }
}
