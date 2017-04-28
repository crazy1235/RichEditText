# RichEditText

RichEditText is a custom EditText to split the input phone number or IdCard number whit " ", "/" or "-".
ClearEditText is a custom EditText to help you clear all the input text quickly.
And the RichEditText also can do it.

## ScreenShot

![screenshot](https://github.com/crazy1235/RichEditText/blob/master/screenshot/screenshot.gif)

    
## How to use?

Add it in your root build.gradle at the end of repositories:    

```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}


```

Add the dependency

```
    compile 'com.jacksen.richedittext:richedittext:3.0'

```

### RichEditText

It's just like the regular EditText..    
    
    
```
    <com.jacksen.richedittext.RichEditText
        android:id="@+id/phone_edit_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:digits="0123456789"
        android:hint="phone number"
        app:separator="space"
        app:splitStyle="335"
        app:type="phone" />
       
```

Remember you should the following code into you xml root layout 
     
```
     xmlns:app="http://schemas.android.com/apk/res-auto"
     
```


you can get the original text use this code:
     
```
     richEditText.getRealText();
        
```

### ClearEditText

```
    <com.jacksen.richedittext.ClearEditText
        android:id="@+id/clear_et"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:clear="true"
        app:clear_icon_down="@drawable/clear_input_down"
        app:clear_icon_up="@drawable/clear_input" />

```

```
    <com.jacksen.richedittext.RichEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:hint="please input your id card number."
        app:clear="true"
        app:separator="hyphen"
        app:splitStyle="684"
        app:type="idCard" />

```

     
## about me


[CSDN Blog](http://blog.csdn.net/crazy1235)

##LICENSE
```
Copyright 2015 Asha

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
