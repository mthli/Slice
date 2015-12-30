package io.github.mthli.slicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
    private FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        frame = (FrameLayout) findViewById(R.id.frame);
        //  TODO frame.setBackground();
    }
}
