package io.github.mthli.slicedemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.FrameLayout;

import io.github.mthli.slice.CustomRoundRectDrawableWithShadow;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        FrameLayout frameTop = (FrameLayout) findViewById(R.id.frame_top);
        FrameLayout frameCenter = (FrameLayout) findViewById(R.id.frame_center);
        FrameLayout frameBottom = (FrameLayout) findViewById(R.id.frame_bottom);

        CustomRoundRectDrawableWithShadow drawableTop = new CustomRoundRectDrawableWithShadow(getResources(), Color.WHITE, 16.0f, 4.0f, 8.0f);
        CustomRoundRectDrawableWithShadow drawableCenter = new CustomRoundRectDrawableWithShadow(getResources(), Color.WHITE, 16.0f, 4.0f, 8.0f);
        CustomRoundRectDrawableWithShadow drawableBottom = new CustomRoundRectDrawableWithShadow(getResources(), Color.WHITE, 16.0f, 4.0f, 8.0f);

        drawableTop.showLeftBottomRect(true);
        drawableTop.showRightBottomRect(true);
        drawableTop.showBottomEdgeShadow(false);
        frameTop.setBackground(drawableTop);

        drawableCenter.showLeftTopRect(true);
        drawableCenter.showRightTopRect(true);
        drawableCenter.showRightBottomRect(true);
        drawableCenter.showLeftBottomRect(true);
        drawableCenter.showTopEdgeShadow(false);
        drawableCenter.showBottomEdgeShadow(false);
        frameCenter.setBackground(drawableCenter);

        drawableBottom.showLeftTopRect(true);
        drawableBottom.showRightTopRect(true);
        drawableBottom.showTopEdgeShadow(false);
        frameBottom.setBackground(drawableBottom);
    }
}
