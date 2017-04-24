package com.ztiany.view.draw.matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-05-07 18:09      <br/>
 * Description：
 */
public class RotateMatrixDemoView extends View {

    private static final String TAG = RotateMatrixDemoView.class.getSimpleName();

    private Paint mPaint;
    private Rect mRect;
    private Matrix mMatrix;

    public RotateMatrixDemoView(Context context) {
       this(context , null);
    }

    public RotateMatrixDemoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RotateMatrixDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMatrix = new Matrix();
        mRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mRect.set(0, 0, 400, 400);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(mRect, mPaint);

        mMatrix.setTranslate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.setMatrix(mMatrix);

        mPaint.setColor(Color.BLACK);
        mRect.set(0, 0, 400, 400);
        canvas.drawRect(mRect, mPaint);

        mMatrix.preRotate(20);
        canvas.setMatrix(mMatrix);

        mPaint.setColor(Color.GRAY);
        mRect.set(0, 0, 400, 400);
        canvas.drawRect(mRect, mPaint);

        mMatrix.preTranslate(-getMeasuredWidth() / 2, -getMeasuredHeight() / 2);

        canvas.setMatrix(mMatrix);

        mRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mPaint.setColor(0x550000FF);

        canvas.drawRect(mRect, mPaint);
        mPaint.setColor(Color.RED);

        mRect.set(0, 0, 400, 400);
        canvas.drawRect(mRect, mPaint);
        Log.d(TAG, "getMeasuredWidth():" + getMeasuredWidth());
        Log.d(TAG, "mMatrix:" + mMatrix);

    }
}
