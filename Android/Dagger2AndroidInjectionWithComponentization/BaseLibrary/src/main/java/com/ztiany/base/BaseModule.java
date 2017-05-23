package com.ztiany.base;

import com.ztiany.base.data.UserDataSource;
import com.ztiany.base.presention.ErrorHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-22 11:16
 */
@Module
public class BaseModule {

    @Provides
    @Singleton
    UserDataSource provideUserDataSource(UserRepository userRepository) {
        return userRepository;
    }

    @Provides
    @Singleton
    ErrorHandler provideErrorHandler(AppErrorHandler appErrorHandler) {
        return appErrorHandler;
    }

}
