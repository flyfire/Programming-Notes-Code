package com.ztiany.mall.service.impl;

import com.ztiany.mall.config.AppConfig;
import com.ztiany.mall.dao.UserDao;
import com.ztiany.mall.domain.User;
import com.ztiany.mall.exception.DataAccessException;
import com.ztiany.mall.service.UserService;
import com.ztiany.mall.utils.BeanFactory;
import com.ztiany.mall.utils.MD5Utils;

import java.sql.SQLException;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.13 23:02
 */
public class UserServiceImpl implements UserService {

    @Override
    public User login(String username, String password) throws DataAccessException {
        UserDao userDao = BeanFactory.getBean(AppConfig.USER_DAO);
        try {
            return userDao.findUser(username, MD5Utils.md5(password));
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    @Override
    public boolean checkUserExist(String username) throws DataAccessException {
        UserDao userDao = BeanFactory.getBean(AppConfig.USER_DAO);
        try {
            return userDao.checkUserExist(username) != 0;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

}
