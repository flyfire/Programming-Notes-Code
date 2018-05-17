package com.ztiany.mall.service;

import com.ztiany.mall.domain.User;
import com.ztiany.mall.exception.DataAccessException;

/**
 * @author Ztiany
 * Email ztiany3@gmail.com
 * Date 18.5.13 21:54
 */
public interface UserService {

    User login(String username, String password) throws DataAccessException;

    boolean checkUserExist(String username) throws DataAccessException;
}
