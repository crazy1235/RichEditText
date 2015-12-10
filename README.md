# RichEditText

    RichEditText is a custom EditText to split the input phone number or IdCard number whit " ", "/" or "-".

## ScreenShot

    ![这里写图片描述](http://img.blog.csdn.net/20151210113227422)
    
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

