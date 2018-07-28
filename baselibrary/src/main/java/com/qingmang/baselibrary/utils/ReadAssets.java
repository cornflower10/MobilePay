package com.qingmang.baselibrary.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xiejingbao on 2018/7/19.
 */

public class ReadAssets {

    public static String readAssetsTxt(Context context, String fileName) throws IOException {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
    }
}
