package com.ztiany.drawables;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.drawables.bitmap.BitmapFragment;
import com.ztiany.drawables.layer.LayerFragment;
import com.ztiany.drawables.level.LevelFragment;
import com.ztiany.drawables.rotate.RotateFragment;
import com.ztiany.drawables.selector.SelectorFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            showFragment(LayerFragment.newInstance());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Selector").setOnMenuItemClickListener(menuItemClickListener);
        menu.add("Rotate").setOnMenuItemClickListener(menuItemClickListener);
        menu.add("Bitmap").setOnMenuItemClickListener(menuItemClickListener);
        menu.add("Level").setOnMenuItemClickListener(menuItemClickListener);
        return true;
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit();
    }

    private MenuItem.OnMenuItemClickListener menuItemClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String title = item.getTitle().toString();
            switch (title) {
                case "Selector":
                    showFragment(SelectorFragment.newInstance());
                    break;
                case "Rotate":
                    showFragment(RotateFragment.newInstance());
                    break;
                case "Level":
                    showFragment(LevelFragment.newInstance());
                    break;
                case "Bitmap":
                    showFragment(BitmapFragment.newInstance());
                    break;
            }
            return true;
        }
    };

}
