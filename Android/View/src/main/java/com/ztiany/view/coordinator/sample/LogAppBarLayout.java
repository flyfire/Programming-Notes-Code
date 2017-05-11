package com.ztiany.view.coordinator.sample;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.WindowInsetsCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

/**
 * @author Ztiany
 *         Email: ztiany3@gmail.com
 *         Date : 2017-05-11 23:27
 */
@CoordinatorLayout.DefaultBehavior(LogAppBarLayout.Behavior.class)
public class LogAppBarLayout extends AppBarLayout {


    public LogAppBarLayout(Context context) {
        super(context);
    }

    public LogAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static class Behavior extends AppBarLayout.Behavior {


        private static final String TAG = Behavior.class.getSimpleName()+"-------------";

        public Behavior() {
            super();
        }

        public Behavior(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams params) {
            super.onAttachedToLayoutParams(params);
            Log.d(TAG, "onAttachedToLayoutParams() called with: params = [" + params + "]");
        }

        @Override
        public void onDetachedFromLayoutParams() {
            super.onDetachedFromLayoutParams();
            Log.d(TAG, "onDetachedFromLayoutParams() called");
        }

        @Override
        public int getScrimColor(CoordinatorLayout parent, AppBarLayout child) {
            Log.d(TAG, "getScrimColor() called with: parent = [" + parent + "], child = [" + child + "]");
            return super.getScrimColor(parent, child);
        }

        @Override
        public float getScrimOpacity(CoordinatorLayout parent, AppBarLayout child) {
            Log.d(TAG, "getScrimOpacity() called with: parent = [" + parent + "], child = [" + child + "]");
            return super.getScrimOpacity(parent, child);
        }

        @Override
        public boolean blocksInteractionBelow(CoordinatorLayout parent, AppBarLayout child) {
            Log.d(TAG, "blocksInteractionBelow() called with: parent = [" + parent + "], child = [" + child + "]");
            return super.blocksInteractionBelow(parent, child);
        }

        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, AppBarLayout child, View dependency) {
            Log.d(TAG, "layoutDependsOn() called with: parent = [" + parent + "], child = [" + child + "], dependency = [" + dependency + "]");
            return super.layoutDependsOn(parent, child, dependency);
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, AppBarLayout child, View dependency) {
            Log.d(TAG, "onDependentViewChanged() called with: parent = [" + parent + "], child = [" + child + "], dependency = [" + dependency + "]");
            return super.onDependentViewChanged(parent, child, dependency);
        }

        @Override
        public void onDependentViewRemoved(CoordinatorLayout parent, AppBarLayout child, View dependency) {
            Log.d(TAG, "onDependentViewRemoved() called with: parent = [" + parent + "], child = [" + child + "], dependency = [" + dependency + "]");
            super.onDependentViewRemoved(parent, child, dependency);
        }

        @Override
        public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
            Log.d(TAG, "onNestedScrollAccepted() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], directTargetChild = [" + directTargetChild + "], target = [" + target + "], nestedScrollAxes = [" + nestedScrollAxes + "]");
            super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
        }

        @Override
        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY) {
            Log.d(TAG, "onNestedPreFling() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], target = [" + target + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "]");
            return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
        }

        @NonNull
        @Override
        public WindowInsetsCompat onApplyWindowInsets(CoordinatorLayout coordinatorLayout, AppBarLayout child, WindowInsetsCompat insets) {
            Log.d(TAG, "onApplyWindowInsets() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], insets = [" + insets + "]");
            return super.onApplyWindowInsets(coordinatorLayout, child, insets);
        }

        @Override
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, AppBarLayout child, Rect rectangle, boolean immediate) {
            Log.d(TAG, "onRequestChildRectangleOnScreen() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], rectangle = [" + rectangle + "], immediate = [" + immediate + "]");
            return super.onRequestChildRectangleOnScreen(coordinatorLayout, child, rectangle, immediate);
        }

        @Override
        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout child, @NonNull Rect rect) {
            Log.d(TAG, "getInsetDodgeRect() called with: parent = [" + parent + "], child = [" + child + "], rect = [" + rect + "]");
            return super.getInsetDodgeRect(parent, child, rect);
        }

        @Override
        public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
            Log.d(TAG, "onStartNestedScroll() called with: parent = [" + parent + "], child = [" + child + "], directTargetChild = [" + directTargetChild + "], target = [" + target + "], nestedScrollAxes = [" + nestedScrollAxes + "]");
            return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes);
        }

        @Override
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dx, int dy, int[] consumed) {
            Log.d(TAG, "onNestedPreScroll() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], target = [" + target + "], dx = [" + dx + "], dy = [" + dy + "], consumed = [" + Arrays.toString(consumed) + "]");
            super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        }

        @Override
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
            Log.d(TAG, "onNestedScroll() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], target = [" + target + "], dxConsumed = [" + dxConsumed + "], dyConsumed = [" + dyConsumed + "], dxUnconsumed = [" + dxUnconsumed + "], dyUnconsumed = [" + dyUnconsumed + "]");
            super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }

        @Override
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout abl, View target) {
            Log.d(TAG, "onStopNestedScroll() called with: coordinatorLayout = [" + coordinatorLayout + "], abl = [" + abl + "], target = [" + target + "]");
            super.onStopNestedScroll(coordinatorLayout, abl, target);
        }

        @Override
        public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
            Log.d(TAG, "onNestedFling() called with: coordinatorLayout = [" + coordinatorLayout + "], child = [" + child + "], target = [" + target + "], velocityX = [" + velocityX + "], velocityY = [" + velocityY + "], consumed = [" + consumed + "]");
            return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
        }

        @Override
        public void setDragCallback(@Nullable DragCallback callback) {
            Log.d(TAG, "setDragCallback() called with: callback = [" + callback + "]");
            super.setDragCallback(callback);
        }

        @Override
        public boolean onMeasureChild(CoordinatorLayout parent, AppBarLayout child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
            Log.d(TAG, "onMeasureChild() called with: parent = [" + parent + "], child = [" + child + "], parentWidthMeasureSpec = [" + parentWidthMeasureSpec + "], widthUsed = [" + widthUsed + "], parentHeightMeasureSpec = [" + parentHeightMeasureSpec + "], heightUsed = [" + heightUsed + "]");
            return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
        }

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, AppBarLayout abl, int layoutDirection) {
            Log.d(TAG, "onLayoutChild() called with: parent = [" + parent + "], abl = [" + abl + "], layoutDirection = [" + layoutDirection + "]");
            return super.onLayoutChild(parent, abl, layoutDirection);
        }

        @Override
        public Parcelable onSaveInstanceState(CoordinatorLayout parent, AppBarLayout abl) {
            Log.d(TAG, "onSaveInstanceState() called with: parent = [" + parent + "], abl = [" + abl + "]");
            return super.onSaveInstanceState(parent, abl);
        }

        @Override
        public void onRestoreInstanceState(CoordinatorLayout parent, AppBarLayout appBarLayout, Parcelable state) {
            Log.d(TAG, "onRestoreInstanceState() called with: parent = [" + parent + "], appBarLayout = [" + appBarLayout + "], state = [" + state + "]");
            super.onRestoreInstanceState(parent, appBarLayout, state);
        }

        @Override
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
            Log.d(TAG, "onInterceptTouchEvent() called with: parent = [" + parent + "], child = [" + child + "], ev = [" + ev + "]");
            return super.onInterceptTouchEvent(parent, child, ev);
        }

        @Override
        public boolean onTouchEvent(CoordinatorLayout parent, AppBarLayout child, MotionEvent ev) {
            Log.d(TAG, "onTouchEvent() called with: parent = [" + parent + "], child = [" + child + "], ev = [" + ev + "]");
            return super.onTouchEvent(parent, child, ev);
        }

        @Override
        protected void layoutChild(CoordinatorLayout parent, AppBarLayout child, int layoutDirection) {
            Log.d(TAG, "layoutChild() called with: parent = [" + parent + "], child = [" + child + "], layoutDirection = [" + layoutDirection + "]");
            super.layoutChild(parent, child, layoutDirection);
        }

        @Override
        public boolean setTopAndBottomOffset(int offset) {
            Log.d(TAG, "setTopAndBottomOffset() called with: offset = [" + offset + "]");
            return super.setTopAndBottomOffset(offset);
        }

        @Override
        public boolean setLeftAndRightOffset(int offset) {
            Log.d(TAG, "setLeftAndRightOffset() called with: offset = [" + offset + "]");
            return super.setLeftAndRightOffset(offset);
        }

        @Override
        public int getTopAndBottomOffset() {
            Log.d(TAG, "getTopAndBottomOffset() called");
            return super.getTopAndBottomOffset();
        }

        @Override
        public int getLeftAndRightOffset() {
            Log.d(TAG, "getLeftAndRightOffset() called");
            return super.getLeftAndRightOffset();
        }
    }
}