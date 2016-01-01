/*
 * Copyright (C) 2016 Matthew Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.mthli.slice;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

public class Slice {
    private static final String TAG = Slice.class.getName();
    private static final boolean SDK_LOLLIPOP = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;

    public static final float DEFAULT_RADIUS = 0.0f;
    public static final int DEFAULT_BACKGROUND_COLOR = Color.WHITE; // TODO
    public static final float DEFAULT_ELEVATION = 0.0f;
    public static final float DEFAULT_MAX_ELEVATION = 0.0f;

    private Context context;
    private Drawable drawable;

    public Slice(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        if (SDK_LOLLIPOP) {
            drawable = new CustomRoundRectDrawable(DEFAULT_BACKGROUND_COLOR, DEFAULT_RADIUS);
        } else {
            drawable = new CustomRoundRectDrawableWithShadow(context.getResources(), DEFAULT_BACKGROUND_COLOR, DEFAULT_RADIUS, DEFAULT_ELEVATION, DEFAULT_MAX_ELEVATION);
        }
    }

    private float dp2px(float dp) {
        return 0.0f; // TODO
    }

    public float getRadius() {
        if (SDK_LOLLIPOP) {
            return ((CustomRoundRectDrawable) drawable).getRadius();
        } else {
            return ((CustomRoundRectDrawableWithShadow) drawable).getRadius();
        }
    }

    public void setRadius(float radiusDp) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).setRadius(dp2px(radiusDp));
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).setRadius(dp2px(radiusDp));
        }
    }

    public void showLeftTopRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showLeftTopRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showLeftTopRect(show);
        }
    }

    public void showRightTopRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showRightTopRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showRightTopRect(show);
        }
    }

    public void showRightBottomRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showRightBottomRect(show);
        } else {
            ((CustomRoundRectDrawable) drawable).showRightBottomRect(show);
        }
    }

    public void showLeftButtomRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showLeftBottomRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showLeftBottomRect(show);
        }
    }

    public void showLeftEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showLeftEdgeShadow(show);
        } else {
            Log.i(TAG, "Only work for pre API 21.");
        }
    }

    public void showTopEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showTopEdgeShadow(show);
        } else {
            Log.i(TAG, "Only work for pre API 21.");
        }
    }

    public void showRightEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showRightEdgeShadow(show);
        } else {
            Log.i(TAG, "Only work for pre API 21.");
        }
    }

    public void showBottomEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showBottomEdgeShadow(show);
        } else {
            Log.i(TAG, "Only work for pre API 21.");
        }
    }
}
