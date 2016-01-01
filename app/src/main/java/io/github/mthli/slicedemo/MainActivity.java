package io.github.mthli.slicedemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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

        drawableTop.setLeftButtomRect(true);
        drawableTop.setRightBottomRect(true);
        drawableTop.setBottomEdgeShadow(false);
        frameTop.setBackground(drawableTop);

        drawableCenter.setLeftTopRect(true);
        drawableCenter.setRightTopRect(true);
        drawableCenter.setRightBottomRect(true);
        drawableCenter.setLeftButtomRect(true);
        drawableCenter.setTopEdgeShadow(false);
        drawableCenter.setBottomEdgeShadow(false);
        frameCenter.setBackground(drawableCenter);

        drawableBottom.setLeftTopRect(true);
        drawableBottom.setRightTopRect(true);
        drawableBottom.setTopEdgeShadow(false);
        frameBottom.setBackground(drawableBottom);
    }
}
