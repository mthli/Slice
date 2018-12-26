Slice
===

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Slice-brightgreen.svg?style=flat-square)](http://android-arsenal.com/details/1/2979)

**YOU DON'T NEED CardView**.

Android drawable that allows you custom round rect position, with more friendly and flexible API. 

Support Android 2.1+

## Example

For example, we can build a card style background for RecyclerView:

![example.png](./example.png "example.png")

[*DEMO*](https://github.com/mthli/Slice/tree/master/app "DEMO")

## API

 - `setColorRes(@ColorRes int colorRes)`

 - `setColor(@ColorInt int color)`
 
 - `setElevation(@FloatRange(from = 0.0F) float elevationDp)`
 
 - `setRadius(@FloatRange(from = 0.0F) float radiusDp)`
 
 - `setRippleRes(@ColorRes int maskRes)` only work for API 21+.
 
 - `setRipple(@ColorInt int mask)` only work for API 21+.
 
 - `showLeftTopRect(boolean show)`
 
 - `showRightTopRect(boolean show)`
 
 - `showRightBottomRect(boolean show)`
 
 - `showLeftBottomRect(boolean show)`
 
 - `showLeftEdgeShadow(boolean show)` only work for pre API 21.
 
 - `showTopEdgeShadow(boolean show)` only work for pre API 21.
 
 - `showRightEdgeShadow(boolean show)` only work for pre API 21.
 
 - `showBottomEdgeShadow(boolean show)` only work for pre API 21.
 
## Gradle

At your top-level `build.gradle` file:

    allprojects {
        repositories {
            // ...
            maven { url 'https://jitpack.io' }
        }
    }
    
And then at your project `build.gradle` file:

    dependencies {
        implementation 'com.github.mthli:Slice:v1.3'
    }

Done!

## Thanks

 - [CardView](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwicgMb484rKAhUhJaYKHQG9AS0QFggfMAA&url=http%3A%2F%2Fdeveloper.android.com%2Freference%2Fandroid%2Fsupport%2Fv7%2Fwidget%2FCardView.html&usg=AFQjCNGAnZUJaZn0kcQuGn6tnrTfsYfTWw&sig2=3NybbX-R_8BWSq_XFxdzjg "CardView | Android Developers")

## License

    Copyright (C) 2018 Matthew Lee
    Copyright (C) 2014 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
