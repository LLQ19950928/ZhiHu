package view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/30.
 */

public class ScrollingHide extends FloatingActionButton.Behavior {

    public ScrollingHide() {
    }

    public ScrollingHide(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child,
                                       View directTargetChild, View target,
                                       int nestedScrollAxes) {

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout,
                                   FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    /*
    Fab向上隐藏，向下显示
     */
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               FloatingActionButton child, View target,
                               int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed,
                dyConsumed, dxUnconsumed, dyUnconsumed);
    }
}
