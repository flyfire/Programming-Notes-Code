package com.ztiany.view.nestedscrolling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.view.R;
import com.ztiany.view.nestedscrolling.sample1.Sample1Fragment;
import com.ztiany.view.nestedscrolling.sticky.StickyNavFragment;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-09 23:17
 */
public class NestedScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_scroll_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.nested_scroll_fl_content, StickyNavFragment.newInstance(), Sample1Fragment.class.getName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 0, "Sample");
        menu.add(Menu.NONE, 2, 1, "StickNavLayout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                replace(Sample1Fragment.newInstance());
                break;
            }
            case 2: {
                replace(StickyNavFragment.newInstance());
                break;
            }
        }
        return true;
    }

    private void replace(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nested_scroll_fl_content, fragment, fragment.getClass().getName())
                .commit();
    }

}
