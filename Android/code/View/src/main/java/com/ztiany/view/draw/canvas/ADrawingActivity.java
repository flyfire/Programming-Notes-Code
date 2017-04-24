package com.ztiany.view.draw.canvas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


public class ADrawingActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFrameLayout = new FrameLayout(this);
        mFrameLayout.addView(new DrawTextView(this));
        setContentView(mFrameLayout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 0, "DrawText");
        menu.add(Menu.NONE, 2, 0, "DrawArc");
        menu.add(Menu.NONE, 3, 0, "DrawBitmap");
        menu.add(Menu.NONE, 4, 0, "DrawCircle");
        menu.add(Menu.NONE, 5, 0, "DrawOval");
        menu.add(Menu.NONE, 6, 0, "DrawPicture");
        menu.add(Menu.NONE, 7, 0, "DrawPoint");
        menu.add(Menu.NONE, 8, 0, "DrawRect");
        menu.add(Menu.NONE, 9, 0, "BitmapMesh");
        menu.add(Menu.NONE, 10, 0, "ClipCanvas");
        menu.add(Menu.NONE, 11, 0, "DashView");
        menu.add(Menu.NONE, 12, 0, "MeshView");
        menu.add(Menu.NONE, 13, 0, "SaveLayer");
        menu.add(Menu.NONE, 14, 0, "Save");
        menu.add(Menu.NONE, 15, 0, "ScaleCanvas");
        menu.add(Menu.NONE, 16, 0, "SkewCanvas");
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mFrameLayout.removeAllViews();
        View view = null;
        switch (item.getItemId()) {
            case 1:
                view = new DrawTextView(this);
                break;
            case 2:
                view = new DrawArcView(this);
                break;
            case 3:
                view = new DrawBitmapView(this);

                break;
            case 4:
                view = new DrawCircle(this);
                break;
            case 5:
                view = new DrawOvalView(this);
                break;
            case 6:
                view = new DrawPictureView(this);
                break;
            case 7:
                view = new DrawPointView(this);
                break;
            case 8:
                view = new DrawRectView(this);
                break;
            case 9:
                view = new BitmapMeshView(this);

                break;
            case 10:
                view = new ClipCanvasView(this);

                break;
            case 11:
                view = new DashView(this);

                break;
            case 12:
                view = new MeshView(this);
                break;
            case 13:
                view = new SaveLayerView(this);
                break;
            case 14:
                view = new SaveView(this);
                break;
            case 15:
                view = new ScaleView(this);
                break;
            case 16:
                view = new SkewView(this);
                break;
        }
        mFrameLayout.addView(view);
        return true;
    }
}
