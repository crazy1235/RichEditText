# RichEditText

RichEditText is a custom EditText to split the input phone number or IdCard number whit " ", "/" or "-".

## ScreenShot

![screenshot](https://github.com/crazy1235/RichEditText/tree/master/screenshot/screenshot.gif)

    
## How to use?


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
