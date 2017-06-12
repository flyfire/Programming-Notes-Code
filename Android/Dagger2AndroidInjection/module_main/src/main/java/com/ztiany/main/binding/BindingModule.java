package com.ztiany.main.binding;

import com.ztiany.base.di.ActivityScope;
import com.ztiany.base.di.FragmentScope;
import com.ztiany.main.binding.data.BindingDataSource;
import com.ztiany.main.binding.data.BindingRepository;
import com.ztiany.main.binding.presentation.BindingDetailFragment;
import com.ztiany.main.binding.presentation.BindingListFragment;
import com.ztiany.main.binding.presentation.BindingListPresenter;
import com.ztiany.main.binding.presentation.Contract;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-23 15:14
 */
@Module
abstract class BindingModule {

    /**
     * 提供BindingDataSource
     */
    @Binds
    @ActivityScope
    abstract BindingDataSource provideBindingDataSource(BindingRepository repository);

    /**
     * 给BindingDetailFragment生成Component
     */
    @FragmentScope
    @ContributesAndroidInjector
    abstract BindingDetailFragment bindBindingDetailFragment();

    /**
     * 给BindingListFragment生成Component
     */
    @FragmentScope
    @ContributesAndroidInjector()
    abstract BindingListFragment bindBindingListFragment();

    /**
     * 提供给BindingListFragment的presenter
     */
    @Binds
    abstract Contract.Presenter provideListPresenter(BindingListPresenter presenter);

}
