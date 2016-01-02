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

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.Log;
import android.view.View;

public class Slice {
    private static final String TAG = Slice.class.getName();
    private static final boolean SDK_LOLLIPOP = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    private static final boolean SDK_JB_MR1 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;

    public static final float DEFAULT_RADIUS_DP = 2.0f;
    public static final float DEFAULT_ELEVATION_DP = 2.0f;
    public static final int DEFAULT_RIPPLE_COLOR = 0x40000000;
    public static final int DEFAULT_BACKGROUND_COLOR = 0xFFFAFAFA;

    private View view;
    private Drawable drawable;

    public Slice(View view) {
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

    private float dp2px(float dp) {
        return view.getResources().getDisplayMetrics().density * dp;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private ColorStateList buildColorStateList(int normal, int pressed) {
        return new ColorStateList(new int[][]{
                new int[] {android.R.attr.state_pressed},
                new int[] {android.R.attr.state_focused},
                new int[] {android.R.attr.state_activated},
                new int[] {}},
                new int[] {pressed, pressed, pressed, normal}
        );
    }

    public void setColor(int color) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).setColor(color);
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).setColor(color);
        }
    }

    @SuppressWarnings("NewApi")
    public void setElevation(float elevationDp) {
        if (SDK_LOLLIPOP) {
            view.setElevation(dp2px(elevationDp));
        } else {
            Log.i(TAG, "setElevation() only support range from 0dp to 2dp pre API 21.");
            ((CustomRoundRectDrawableWithShadow) drawable).setShadowSize(dp2px(elevationDp));
        }
    }

    public void setRadius(float radiusDp) {
        if (SDK_LOLLIPOP) {
            ((CustomRoundRectDrawable) drawable).setRadius(dp2px(radiusDp));
        } else {
            ((CustomRoundRectDrawableWithShadow) drawable).setRadius(dp2px(radiusDp));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setRipple(final int mask) {
        if (SDK_LOLLIPOP) {
            if (mask != 0) {
                ShapeDrawable shape = new ShapeDrawable(new Shape() {
                    @Override
                    public void draw(Canvas canvas, Paint paint) {
                        paint.setColor(mask);
                        canvas.drawPath(((CustomRoundRectDrawable) drawable).buildConvexPath(), paint);
                    }
                });

                int color = ((CustomRoundRectDrawable) drawable).getColor();
                RippleDrawable ripple = new RippleDrawable(buildColorStateList(color, mask), drawable, shape);

                view.setBackground(ripple);
            } else {
                view.setBackground(drawable);
            }
        } else {
            Log.i(TAG, "setRipple() only work for API 21+");
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
            ((CustomRoundRectDrawableWithShadow) drawable).showRightBottomRect(show);
        }
    }

    public void showLeftBottomRect(boolean show) {
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
            Log.i(TAG, "showLeftEdgeShadow() only work for pre API 21.");
        }
    }

    public void showTopEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showTopEdgeShadow(show);
        } else {
            Log.i(TAG, "showTopEdgeShadow() only work for pre API 21.");
        }
    }

    public void showRightEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showRightEdgeShadow(show);
        } else {
            Log.i(TAG, "showRightEdgeShadow() only work for pre API 21.");
        }
    }

    public void showBottomEdgeShadow(boolean show) {
        if (!SDK_LOLLIPOP) {
            ((CustomRoundRectDrawableWithShadow) drawable).showBottomEdgeShadow(show);
        } else {
            Log.i(TAG, "showBottomEdgeShadow() only work for pre API 21.");
        }
    }
}
