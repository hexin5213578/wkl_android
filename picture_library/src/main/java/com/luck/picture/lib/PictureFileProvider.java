package com.luck.picture.lib;

import android.net.Uri;

import androidx.core.content.FileProvider;

/**
 * Created by Mankin on 2017/8/29.
 */

public class PictureFileProvider extends FileProvider {

    private static final String authority = "picture_authority";
    private static final String scheme = "content";

    @Override
    public boolean onCreate() {
        return true;
    }

    public static Uri getUri(String path){
        Uri.Builder builder = new Uri.Builder();
        return builder.authority(authority).path(path).scheme(scheme).build();
    }

}
