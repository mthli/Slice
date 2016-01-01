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

        FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        CustomRoundRectDrawableWithShadow drawable = new CustomRoundRectDrawableWithShadow(getResources(), Color.WHITE, 16.0f, 4.0f, 8.0f);

        frame.setBackground(drawable);
    }
}
