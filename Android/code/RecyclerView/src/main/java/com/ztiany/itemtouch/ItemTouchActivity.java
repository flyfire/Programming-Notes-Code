package com.ztiany.itemtouch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.recyclerview.R;



public class ItemTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        if (savedInstanceState == null) {
            switchFragment(LinearFragment.newInstance());
        }
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem linear = menu.add(Menu.NONE, 1, 0, "Linear").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switchFragment(LinearFragment.newInstance());
                return true;
            }
        });

        MenuItem grid = menu.add(Menu.NONE, 2, 1, "Grid").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switchFragment(GridFragment.newInstance());
                return true;
            }
        });

        MenuItemCompat.setShowAsAction(linear, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        MenuItemCompat.setShowAsAction(grid, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);

        return super.onCreateOptionsMenu(menu);
    }
}
