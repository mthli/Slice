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

public class Slice {
    public static final float DEFAULT_RADIUS = 0.0f;
    public static final int DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    public static final float DEFAULT_ELEVATION = 0.0f;
    public static final float DEFAULT_MAX_ELEVATION = 0.0f;

    private Context context;
    private Drawable drawable;

    public Slice(Context context) {
        this.context = context;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.drawable = new CustomRoundRectDrawable(DEFAULT_BACKGROUND_COLOR, DEFAULT_RADIUS);
        } else {
            this.drawable = new CustomRoundRectDrawableWithShadow(context.getResources(), DEFAULT_BACKGROUND_COLOR, DEFAULT_RADIUS, DEFAULT_ELEVATION, DEFAULT_MAX_ELEVATION);
        }
    }

    // TODO
}
