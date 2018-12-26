/*
 * Copyright (C) 2018 Matthew Lee
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

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

@SuppressWarnings("UnusedReturnValue")
public final class Slice {
    private static final String TAG = Slice.class.getName();
    private static final boolean SDK_LOLLIPOP = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    private static final boolean SDK_JB_MR1 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;

    @SuppressWarnings("WeakerAccess")
    public static final float DEFAULT_RADIUS_DP = 2.0f;
    @SuppressWarnings("WeakerAccess")
    public static final float DEFAULT_ELEVATION_DP = 2.0f;
    @SuppressWarnings("WeakerAccess")
    public static final int DEFAULT_RIPPLE_COLOR = 0x40000000;
    @SuppressWarnings("WeakerAccess")
    public static final int DEFAULT_BACKGROUND_COLOR = 0xFFFAFAFA;

    private View view;
    private Drawable drawable;

    public Slice(@NonNull View view) {
        this.view = view;
        init();
    }

    @SuppressWarnings("NewApi")
    private void init() {
        if (SDK_LOLLIPOP) {
            drawable = new CustomRoundRectDrawable(DEFAULT_BACKGROUND_COLOR, dp2px(DEFAULT_RADIUS_DP));
        } else {
            drawable = new CustomRoundRectDrawableWithShadow(view.getResources(), DEFAULT_BACKGROUND_COLOR, dp2px(DEFAULT_RADIUS_DP), dp2px(DEFAULT_ELEVATION_DP), dp2px(DEFAULT_ELEVATION_DP));
        }

        if (SDK_JB_MR1) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }

        setRipple(DEFAULT_RIPPLE_COLOR);
        setElevation(DEFAULT_ELEVATION_DP);
    }

    private float dp2px(@FloatRange(from = 0.0F) float dp) {
        return view.getResources().getDisplayMetrics().density * dp;
    }

    @NonNull
    private ColorStateList buildColorStateList(@ColorInt int pressed) {
        return new ColorStateList(new int[][]{
                new int[] {android.R.attr.state_pressed},
                new int[] {android.R.attr.state_focused},
                new int[] {}},
                new int[] {pressed, pressed, pressed}
        );
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice setColorRes(@ColorRes int colorRes) {
        return setColor(ContextCompat.getColor(view.getContext(), colorRes));
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice setColor(@ColorInt int color) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).setColor(color);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).setColor(color);
        }

        return this;
    }

    @SuppressWarnings({"NewApi", "unused"})
    @NonNull
    public Slice setElevation(@FloatRange(from = 0.0F) float elevationDp) {
        if (SDK_LOLLIPOP) {
            view.setElevation(dp2px(elevationDp));
        } else {
            Log.i(TAG, "setElevation() only support range from 0dp to 2dp pre API 21.");
            ((CustomRoundRectDrawableWithShadow) drawable).setShadowSize(dp2px(elevationDp));
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice setRadius(@FloatRange(from = 0.0F) float radiusDp) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).setRadius(dp2px(radiusDp));
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).setRadius(dp2px(radiusDp));
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice setRippleRes(@ColorRes int maskRes) {
        return setRipple(ContextCompat.getColor(view.getContext(), maskRes));
    }

    @SuppressWarnings("WeakerAccess")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Slice setRipple(@ColorInt final int mask) {
        if (SDK_LOLLIPOP) {
            if (mask != 0) {
                ShapeDrawable shape = new ShapeDrawable(new Shape() {
                    @Override
                    public void draw(Canvas canvas, Paint paint) {
                        paint.setColor(mask);
                        canvas.drawPath(((CustomRoundRectDrawable) drawable).buildConvexPath(), paint);
                    }
                });

                RippleDrawable ripple = new RippleDrawable(buildColorStateList(mask), drawable, shape);
                view.setBackground(ripple);
            } else {
                view.setBackground(drawable);
            }
        } else {
            Log.i(TAG, "setRipple() only work for API 21+");
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showLeftTopRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showLeftTopRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showLeftTopRect(show);
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showRightTopRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showRightTopRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showRightTopRect(show);
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showRightBottomRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showRightBottomRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showRightBottomRect(show);
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showLeftBottomRect(boolean show) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).showLeftBottomRect(show);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).showLeftBottomRect(show);
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showLeftEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showLeftEdgeShadow(show);
        } else {
            Log.i(TAG, "showLeftEdgeShadow() only work for pre API 21.");
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showTopEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showTopEdgeShadow(show);
        } else {
            Log.i(TAG, "showTopEdgeShadow() only work for pre API 21.");
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showRightEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showRightEdgeShadow(show);
        } else {
            Log.i(TAG, "showRightEdgeShadow() only work for pre API 21.");
        }

        return this;
    }

    @SuppressWarnings("unused")
    @NonNull
    public Slice showBottomEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showBottomEdgeShadow(show);
        } else {
            Log.i(TAG, "showBottomEdgeShadow() only work for pre API 21.");
        }

        return this;
    }
}
