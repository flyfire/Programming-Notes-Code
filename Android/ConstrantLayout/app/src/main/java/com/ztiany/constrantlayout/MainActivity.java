package com.ztiany.constrantlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_align);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "align");
        menu.add(Menu.NONE, 2, 2, "bias");
        menu.add(Menu.NONE, 3, 3, "chain");
        menu.add(Menu.NONE, 4, 4, "ratio");
        menu.add(Menu.NONE, 5, 5, "match constraint");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                setContentView(R.layout.activity_main_align);
                break;
            case 2:
                setContentView(R.layout.activity_main_align_bias);
                break;
            case 3:
                setContentView(R.layout.activity_main_chain_style);
                break;
            case 4:
                setContentView(R.layout.activity_main_ratio);
                break;
            case 5:
                setContentView(R.layout.activity_match_constraint);
                break;
        }

        return true;
    }
}
