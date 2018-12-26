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
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CustomRoundRectDrawable extends RoundRectDrawable {
    private boolean leftTopRect = false;
    private boolean rightTopRect = false;
    private boolean leftBottomRect = false;
    private boolean rightBottomRect = false;

    public CustomRoundRectDrawable(int backgroundColor, float radius) {
        super(backgroundColor, radius);
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
    public void getOutline(Outline outline) {
        if (buildConvexPath().isConvex()) {
            outline.setConvexPath(buildConvexPath());
        } else {
            super.getOutline(outline);
        }
    }

    private RectF buildLeftTopRect() {
        RectF rectF = new RectF();
        rectF.left = mBoundsF.left;
        rectF.top = mBoundsF.top;
        rectF.right = mBoundsF.left + mRadius * 2.0f;
        rectF.bottom = mBoundsF.top + mRadius * 2.0f;

        return rectF;
    }

    private RectF buildRightTopRect() {
        RectF rectF = new RectF();
        rectF.left = mBoundsF.right - mRadius * 2.0f;
        rectF.top = mBoundsF.top;
        rectF.right = mBoundsF.right;
        rectF.bottom = mBoundsF.top + mRadius * 2.0f;

        return rectF;
    }

    private RectF buildRightBottomRect() {
        RectF rectF = new RectF();
        rectF.left = mBoundsF.right - mRadius * 2.0f;
        rectF.top = mBoundsF.bottom - mRadius * 2.0f;
        rectF.right = mBoundsF.right;
        rectF.bottom = mBoundsF.bottom;

        return rectF;
    }

    private RectF buildLeftBottomRect() {
        RectF rectF = new RectF();
        rectF.left = mBoundsF.left;
        rectF.top = mBoundsF.bottom - mRadius * 2.0f;
        rectF.right = mBoundsF.left + mRadius * 2.0f;
        rectF.bottom = mBoundsF.bottom;

        return rectF;
    }

    protected Path buildConvexPath() {
        Path path = new Path();

        path.moveTo(mBoundsF.left, (mBoundsF.top + mBoundsF.bottom) / 2.0f);
        path.lineTo(mBoundsF.left, mBoundsF.top + mRadius);
        if (leftTopRect) {
            path.lineTo(mBoundsF.left, mBoundsF.top);
        } else {
            RectF rectF = new RectF(mBoundsF.left, mBoundsF.top, mBoundsF.left + mRadius * 2.0f, mBoundsF.top + mRadius * 2.0f);
            path.arcTo(rectF, 180.0f, 90.0f);
        }

        path.lineTo(mBoundsF.right - mRadius, mBoundsF.top);
        if (rightTopRect) {
            path.lineTo(mBoundsF.right, mBoundsF.top);
        } else {
            RectF rectF = new RectF(mBoundsF.right - mRadius * 2.0f, mBoundsF.top, mBoundsF.right, mBoundsF.top + mRadius * 2.0f);
            path.arcTo(rectF, 270.0f, 90.0f);
        }

        path.lineTo(mBoundsF.right, mBoundsF.bottom - mRadius);
        if (rightBottomRect) {
            path.lineTo(mBoundsF.right, mBoundsF.bottom);
        } else {
            RectF rectF = new RectF(mBoundsF.right - mRadius * 2.0f, mBoundsF.bottom - mRadius * 2.0f, mBoundsF.right, mBoundsF.bottom);
            path.arcTo(rectF, 0.0f, 90.0f);
        }

        path.lineTo(mBoundsF.left + mRadius, mBoundsF.bottom);
        if (leftBottomRect) {
            path.lineTo(mBoundsF.left, mBoundsF.bottom);
        } else {
            RectF rectF = new RectF(mBoundsF.left, mBoundsF.bottom - mRadius * 2.0f, mBoundsF.left + mRadius * 2.0f, mBoundsF.bottom);
            path.arcTo(rectF, 90.0f, 90.0f);
        }

        path.close();
        return path;
    }

    protected int getColor() {
        return mPaint.getColor();
    }

    @Override
    public void setRadius(float radius) {
        super.setRadius(radius);
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
}
