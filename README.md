Slice
===

**YOU DON'T NEED CardView**.

Android drawable that allows you custom round rect position. with more friendly and flexible API. 

Support Android 2.1+

## Example

For example, we can build a card style background for RecyclerView:

![example.png](./example.png "example.png")

And here has a [demo apk]( "SliceDemo.1.0.apk"), and it's [source code]( "SliceDemo.1.0.apk's source code"), very easy to understand~

## API

 - `setRadius(float radiusDp)`
 
 - `setElevation(float elevationDp)`
 
 - `setColor(int color)`
 
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

    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
    
And then at your project `build.gradle` file:

    dependencies {
        compile 'com.github.mthli:Slice:v1.0'
    }

Done!

## License

    Copyright 2016 Matthew Lee

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
