package com.ztiany.view.scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ztiany.view.R;


public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Scroll").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setContentView(R.layout.activity_scroller);
                return true;
            }
        });

        menu.add("OverScroll").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setContentView(R.layout.activity_over_scroller);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}
