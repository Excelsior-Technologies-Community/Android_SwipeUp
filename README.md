## SwipeUpLayout
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

A lightweight, customizable Swipe-Up / Bottom Sheet style layout for Android, written in Kotlin.
It allows you to add smooth swipe-up interactions to any view with minimal setup.

### Features

- Smooth swipe-up & swipe-down animation
- Bottom-sheet–like behavior
- Works with any custom layout
- Gesture-based interaction
- Expand / Collapse programmatically
- State management (EXPANDED, COLLAPSED)
- XML configuration support

---

### Preview

<p align="center">
<table>
  <tr>
    <td align="center">
      <img src="assets/demo1.gif" width="360" />
    </td>
    <td align="center">
      <img src="assets/demo2.gif" width="360" />
    </td>
  </tr>
</table>
</p>

---

## Installation (JitPack)

### 1️⃣ Add JitPack to your **root `settings.gradle` or `build.gradle`**

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Add Dependency
```
dependencies {
	        implementation 'com.github.Excelsior-Technologies-Community:Android_SwipeUp:1.0.0'
	}
```

---

### Basic Usage

**XML Layout**
```xml
<com.ext.swipeup.SwipeUpLayout
    android:id="@+id/swipeUpLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:startExpanded="false">

    <!-- Swipeable panel -->
    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="300dp"
        android:layout_gravity="bottom">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Swipe Me Up 👆"
            android:textSize="20sp"
            android:layout_gravity="center"/>

    </FrameLayout>

</com.ext.swipeup.SwipeUpLayout>
```

**Important**

- The swipeable child must NOT be match_parent height
- This child is treated as the bottom panel

**Kotlin Usage**
```xml
 val swipeLayout = findViewById<SwipeUpLayout>(R.id.swipeUpLayout)

        swipeLayout.setSwipeUpCallback(object : SwipeUpCallback {
            override fun onSwipeUp() {
                Toast.makeText(this@MainActivity, "Swipe Up Detected 🚀", Toast.LENGTH_SHORT).show()
            }
        })
```

**Programmatic Control**
```
swipeLayout.expand()
swipeLayout.collapse()
```

### XML Attributes

| Attribute        | Description                         | Default |
|------------------|-------------------------------------|---------|
| `startExpanded`  | Start panel in expanded state       | `false` |

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
