package com.ostrovec.mygarden.ui.base.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap

interface BaseViewModelType {
    fun extractImageFilePath(contentResolver: ContentResolver, bitmap: Bitmap, context: Context): String
}