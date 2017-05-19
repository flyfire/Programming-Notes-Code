package com.ztiany.dagger.multibinding;

import android.app.Activity;

public interface HasActivitySubcomponentBuilders {

    ActivityComponentBuilder getActivityComponentBuilder(Class<? extends Activity> activityClass);

} 