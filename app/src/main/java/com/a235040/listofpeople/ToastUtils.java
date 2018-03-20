package com.a235040.listofpeople;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Szymon on 20.03.2018.
 */

public class ToastUtils {
    public static void showToast(String message, Context context){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
