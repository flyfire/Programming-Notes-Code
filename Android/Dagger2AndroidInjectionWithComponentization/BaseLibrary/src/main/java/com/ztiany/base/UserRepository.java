package com.ztiany.base;

import com.ztiany.base.data.User;
import com.ztiany.base.data.UserDataSource;

import javax.inject.Inject;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 11:16
 */
class UserRepository implements UserDataSource {

    @Inject
    UserRepository() {

    }

    @Override
    public void user(String id, final Callback callback) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    User user = new User();
                    user.setAvatar("没有图片");
                    user.setId("10013");
                    user.setName("Ztiany");
                    callback.onLoadUser(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
