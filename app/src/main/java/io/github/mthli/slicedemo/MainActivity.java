package io.github.mthli.slicedemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.FrameLayout;

import io.github.mthli.slice.CustomRoundRectDrawable;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
        final CustomRoundRectDrawable drawable = new CustomRoundRectDrawable(Color.WHITE, 16.0f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            frame.setBackground(drawable);
            frame.setElevation(8.0f);
        } else {
            // TODO
        }
    }
}
