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
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;

public class CustomRoundRectDrawableWithShadow extends RoundRectDrawableWithShadow {
    private boolean leftTopRect = false;
    private boolean rightTopRect = false;
    private boolean leftBottomRect = false;
    private boolean rightBottomRect = false;

    private boolean leftEdgeShadow = true;
    private boolean topEdgeShadow = true;
    private boolean rightEdgeShadow = true;
    private boolean bottomEdgeShadow = true;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public CustomRoundRectDrawableWithShadow(Resources resources, int backgroundColor, float radius, float shadowSize, float maxShadowSize) {
        super(resources, backgroundColor, radius, shadowSize, maxShadowSize);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            initJellyBeanMr1();
        } else {
            initEclairMr1();
        }
    }

    private void initJellyBeanMr1() {
        sRoundRectHelper = new RoundRectHelper() {
            @Override
            public void drawRoundRect(Canvas canvas, RectF bounds, float radius, Paint paint) {
                canvas.drawRoundRect(bounds, radius, radius, paint);
            }
        };
    }

    private void initEclairMr1() {
        final RectF rectF = new RectF();

        sRoundRectHelper = new RoundRectHelper() {
            @Override
            public void drawRoundRect(Canvas canvas, RectF bounds, float radius, Paint paint) {
                float innerWidth = bounds.width() - radius * 2.0f - 1.0f;
                float innerHeight = bounds.height() - radius * 2.0f - 1.0f;

                if (radius >= 1.0f) {
                    radius += 0.5f;
                    rectF.set(-radius, -radius, radius, radius);

                    int saved = canvas.save();
                    canvas.translate(bounds.left + radius, bounds.top + radius);
                    canvas.drawArc(rectF, 180.0f, 90.0f, true, paint);
                    canvas.translate(innerWidth, 0);
                    canvas.rotate(90.0f);
                    canvas.drawArc(rectF, 180.0f, 90.0f, true, paint);
                    canvas.translate(innerHeight, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(rectF, 180.0f, 90.0f, true, paint);
                    canvas.translate(innerWidth, 0.0f);
                    canvas.rotate(90.0f);
                    canvas.drawArc(rectF, 180.0f, 90.0f, true, paint);
                    canvas.restoreToCount(saved);

                    canvas.drawRect(
                            bounds.left + radius - 1.0f,
                            bounds.top,
                            bounds.right - radius + 1.0f,
                            bounds.top + radius,
                            paint
                    );

                    canvas.drawRect(
                            bounds.left + radius - 1.0f,
                            bounds.bottom - radius + 1.0f,
                            bounds.right - radius + 1.0f,
                            bounds.bottom,
                            paint
                    );
                }

                canvas.drawRect(
                        bounds.left,
                        bounds.top + Math.max(0.0f, radius - 1.0f),
                        bounds.right,
                        bounds.bottom - radius + 1.0f,
                        paint
                );
            }
        };
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (leftTopRect) {
            canvas.drawRect(buildLeftTopRect(), mPaint);
        }

        if (rightTopRect) {
            canvas.drawRect(buildRightTopRect(), mPaint);
        }

        if (rightBottomRect) {
            canvas.drawRect(buildRightBottomRect(), mPaint);
        }

        if (leftBottomRect) {
            canvas.drawRect(buildLeftBottomRect(), mPaint);
        }
    }

    @Override
    protected void drawShadow(Canvas canvas) {
        float top = -mCornerRadius - mShadowSize;
        float inset = mCornerRadius + mInsetShadow + mRawShadowSize / 2.0f;
        boolean suggestHorizontal = mCardBounds.width() - inset * 2.0f > 0.0f;
        boolean suggestVertical = mCardBounds.height() - inset * 2.0f > 0.0f;

        drawLeftTopShadow(canvas, inset);
        drawRightTopShadow(canvas, inset);
        drawRightBottomShadow(canvas, inset);
        drawLeftBottomShadow(canvas, inset);

        drawLeftEdgeShadow(canvas, top, inset, suggestVertical);
        drawTopEdgeShadow(canvas, top, inset, suggestHorizontal);
        drawRightEdgeShadow(canvas, top, inset, suggestVertical);
        drawBottomEdgeShadow(canvas, top, inset, suggestHorizontal);
    }

    @Override
    protected void buildComponents(Rect bounds) {
        float vOffset = mRawMaxShadowSize * SHADOW_MULTIPLIER;
        float left = leftEdgeShadow ? bounds.left + mRawMaxShadowSize : bounds.left;
        float top = topEdgeShadow ? bounds.top + vOffset : bounds.top;
        float right = rightEdgeShadow ? bounds.right - mRawMaxShadowSize : bounds.right;
        float bottom = bottomEdgeShadow ? bounds.bottom - vOffset : bounds.bottom;

        mCardBounds.set(left, top, right, bottom);
        buildShadowCorners();
    }

    private RectF buildLeftTopRect() {
        RectF rectF = new RectF();
        rectF.left = mCardBounds.left;
        rectF.top = mCardBounds.top;
        rectF.right = mCardBounds.left + mCornerRadius * 2.0f;
        rectF.bottom = mCardBounds.top + mCornerRadius * 2.0f;

        return rectF;
    }

    private RectF buildRightTopRect() {
        RectF rectF = new RectF();
        rectF.left = mCardBounds.right - mCornerRadius * 2.0f;
        rectF.top = mCardBounds.top;
        rectF.right = mCardBounds.right;
        rectF.bottom = mCardBounds.top + mCornerRadius * 2.0f;

        return rectF;
    }

    private RectF buildRightBottomRect() {
        RectF rectF = new RectF();
        rectF.left = mCardBounds.right - mCornerRadius * 2.0f;
        rectF.top = mCardBounds.bottom - mCornerRadius * 2.0f;
        rectF.right = mCardBounds.right;
        rectF.bottom = mCardBounds.bottom;

        return rectF;
    }

    private RectF buildLeftBottomRect() {
        RectF rectF = new RectF();
        rectF.left = mCardBounds.left;
        rectF.top = mCardBounds.bottom - mCornerRadius * 2.0f;
        rectF.right = mCardBounds.left + mCornerRadius * 2.0f;
        rectF.bottom = mCardBounds.bottom;

        return rectF;
    }

    private void drawLeftTopShadow(Canvas canvas, float inset) {
        if (!leftTopRect) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.left + inset, mCardBounds.top + inset);
            canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
            canvas.restoreToCount(saved);
        }
    }

    private void drawRightTopShadow(Canvas canvas, float inset) {
        if (!rightTopRect) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.right - inset, mCardBounds.top + inset);
            canvas.rotate(90.0f);
            canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
            canvas.restoreToCount(saved);
        }
    }

    private void drawRightBottomShadow(Canvas canvas, float inset) {
        if (!rightBottomRect) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.right - inset, mCardBounds.bottom - inset);
            canvas.rotate(180.0f);
            canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
            canvas.restoreToCount(saved);
        }
    }

    private void drawLeftBottomShadow(Canvas canvas, float inset) {
        if (!leftBottomRect) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.left + inset, mCardBounds.bottom - inset);
            canvas.rotate(270.0f);
            canvas.drawPath(mCornerShadowPath, mCornerShadowPaint);
            canvas.restoreToCount(saved);
        }
    }

    private void drawLeftEdgeShadow(Canvas canvas, float top, float inset, boolean suggest) {
        if (suggest && leftEdgeShadow) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.left + inset, mCardBounds.bottom - inset);
            canvas.rotate(270.0f);
            canvas.drawRect(0.0f, top, mCardBounds.height() - inset * 2.0f, -mCornerRadius, mEdgeShadowPaint);
            canvas.restoreToCount(saved);

            if (leftTopRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.left + inset, mCardBounds.top + inset);
                canvas.rotate(270.0f);
                canvas.drawRect(0.0f, top, inset, -mCornerRadius, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }

            if (leftBottomRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.left + inset, mCardBounds.bottom);
                canvas.rotate(270.0f);
                canvas.drawRect(0.0f, top, inset, -mCornerRadius, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }
        }
    }

    private void drawTopEdgeShadow(Canvas canvas, float top, float inset, boolean suggest) {
        if (suggest && topEdgeShadow) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.left + inset, mCardBounds.top + inset);
            canvas.drawRect(0, top, mCardBounds.width() - inset * 2.0f, -mCornerRadius, mEdgeShadowPaint);
            canvas.restoreToCount(saved);

            if (leftTopRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.left, mCardBounds.top + inset);
                canvas.drawRect(0, top, inset, -mCornerRadius, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }

            if (rightTopRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.right - inset, mCardBounds.top + inset);
                canvas.drawRect(0, top, inset, -mCornerRadius, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }
        }
    }

    private void drawRightEdgeShadow(Canvas canvas, float top, float inset, boolean suggest) {
        if (suggest && rightEdgeShadow) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.right - inset, mCardBounds.top + inset);
            canvas.rotate(90.0f);
            canvas.drawRect(0.0f, top, mCardBounds.height() - inset * 2.0f, -mCornerRadius, mEdgeShadowPaint);
            canvas.restoreToCount(saved);

            if (rightTopRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.right - inset, mCardBounds.top);
                canvas.rotate(90.0f);
                canvas.drawRect(0.0f, top, inset, -mCornerRadius, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }

            if (rightBottomRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.right - inset, mCardBounds.bottom - inset);
                canvas.rotate(90.0f);
                canvas.drawRect(0.0f, top, inset, -mCornerRadius, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }
        }
    }

    private void drawBottomEdgeShadow(Canvas canvas, float top, float inset, boolean suggest) {
        if (suggest && bottomEdgeShadow) {
            int saved = canvas.save();
            canvas.translate(mCardBounds.right - inset, mCardBounds.bottom - inset);
            canvas.rotate(180.0f);
            canvas.drawRect(0.0f, top, mCardBounds.width() - inset * 2.0f, -mCornerRadius + mShadowSize, mEdgeShadowPaint);
            canvas.restoreToCount(saved);

            if (leftBottomRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.left + inset, mCardBounds.bottom - inset);
                canvas.rotate(180.0f);
                canvas.drawRect(0.0f, top, inset, -mCornerRadius + mShadowSize, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }

            if (rightBottomRect) {
                saved = canvas.save();
                canvas.translate(mCardBounds.right, mCardBounds.bottom - inset);
                canvas.rotate(180.0f);
                canvas.drawRect(0.0f, top, inset, -mCornerRadius + mShadowSize, mEdgeShadowPaint);
                canvas.restoreToCount(saved);
            }
        }
    }

    public void setRadius(float radius) {
        super.setCornerRadius(radius);
    }

    @Override
    public void setShadowSize(float size) {
        super.setShadowSize(size, mRawMaxShadowSize);
    }

    @Override
    public void setColor(int color) {
        super.setColor(color);
    }

    public void showLeftTopRect(boolean show) {
        this.leftTopRect = show;
        invalidateSelf();
    }

    public void showRightTopRect(boolean show) {
        this.rightTopRect = show;
        invalidateSelf();
    }

    public void showRightBottomRect(boolean show) {
        this.rightBottomRect = show;
        invalidateSelf();
    }

    public void showLeftBottomRect(boolean show) {
        this.leftBottomRect = show;
        invalidateSelf();
    }

    public void showLeftEdgeShadow(boolean show) {
        this.leftEdgeShadow = show;
        invalidateSelf();
    }

    public void showTopEdgeShadow(boolean show) {
        this.topEdgeShadow = show;
        invalidateSelf();
    }

    public void showRightEdgeShadow(boolean show) {
        this.rightEdgeShadow = show;
        invalidateSelf();
    }

    public void showBottomEdgeShadow(boolean show) {
        this.bottomEdgeShadow = show;
        invalidateSelf();
    }
}
