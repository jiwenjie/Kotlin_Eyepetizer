package com.example.baselibrary.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.ComponentName
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.net.toUri
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/11/04
 *  desc:
 *  version:1.0
 */
object GeneralUtils {

    @TargetApi(21)
    fun setTextDrawable(context: Context, view: TextView, drawableRes: Int, direction: String) {
        val drawable = context.resources.getDrawable(drawableRes, null)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        when (direction) {
            "left" -> view.setCompoundDrawables(drawable, null, null, null)
            "top" -> view.setCompoundDrawables(null, drawable, null, null)
            "right" -> view.setCompoundDrawables(null, null, drawable, null)
            "bottom" -> view.setCompoundDrawables(null, null, null, drawable)
            else -> {

            }
        }
    }

    /**
     * #.## 表示会去掉小数点后最后的 0 例如 1.20 就会变成 1.2
     * 0.00 表示不会去掉小数点后的 0 例如 1.20 依然是 1.20
     */
    fun getSavePoint(double: Double, format: String): String {
        return DecimalFormat(format).format(double)
    }

    fun setWindowAlpha(context: Context, alpha: Float) {
        val lp = (context as Activity).window.attributes
        lp.alpha = alpha
        context.window.attributes = lp
    }

    fun showSoftInput(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun compressBitmap(path: String, rqsWidth: Int, rqsHeight: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        val outWidth = options.outWidth
        val outHeight = options.outHeight
        var size = (outWidth * outHeight) / (rqsWidth * rqsHeight)
        if (size % 2 != 0) {
            size ++
        }
        options.inSampleSize = size
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }

    fun compressBitmapForNewGoods(path: String, rqsWidth: Int, rqsHeight: Int, compressSize: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        val outWidth = options.outWidth
        val outHeight = options.outHeight
        options.inSampleSize = calculateInSampleSize(options, rqsWidth, rqsHeight, outWidth, outHeight) *
                compressSize
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, rqsWidth: Int,
                                      rqsHeight: Int, width: Int, height: Int): Int {
        var inSampleSize = 1

        if (rqsWidth == 0 || rqsHeight == 0) {
            return 1
        }

        if (height > rqsHeight || width > rqsWidth) {
            val widthRatio: Int = Math.round(width.toFloat() / rqsWidth.toFloat())
            val heightRadio: Int = Math.round(height.toFloat() / rqsHeight.toFloat())
            inSampleSize = if (heightRadio < widthRatio) heightRadio else widthRatio
        }

        return inSampleSize
    }

    fun bitmapQualityCompress(bitmap: Bitmap): Bitmap? {
        val baos = ByteArrayOutputStream()
        var options = 100
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        while (baos.toByteArray().size / 1024 > 100) {
            baos.reset()
            options -= 10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)
        }

        val bais = ByteArrayInputStream(baos.toByteArray())
        return BitmapFactory.decodeStream(bais, null, null)
    }

    fun getScaledBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat()
        val scaleHeight = newHeight.toFloat()
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true)
    }

    @SuppressLint("MissingPermission")
    fun phoneDial(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.data = "tel:$phoneNumber".toUri()
        context.startActivity(intent)
    }

    fun isEnglish(s: String): Boolean {
        s.filterNot { it.toInt() in 65..90 || it.toInt() in 97..122 }
            .forEach { return false }
        return true
    }

    fun getVideoThumbnail(videoPath: String): Bitmap {
        val media = MediaMetadataRetriever()
        media.setDataSource(videoPath)
        return media.frameAtTime
    }

    fun startAnotherApp(context: Context, packageName: String) {
        var packageInfo: PackageInfo? = null

        try {
            packageInfo = (context as Activity).packageManager.getPackageInfo(packageName, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (packageInfo == null) {
            return
        }

        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.`package` = packageInfo.packageName

        val resolveInfoList = context.packageManager.queryIntentActivities(intent, 0)
        val resolveInfo = resolveInfoList.iterator().next()

        if (resolveInfo != null) {
            val packName = resolveInfo.activityInfo.packageName
            val className = resolveInfo.activityInfo.name
            val startIntent = Intent(Intent.ACTION_MAIN)
            startIntent.addCategory(Intent.CATEGORY_LAUNCHER)

            startIntent.component = ComponentName(packName, className)
            context.startActivity(startIntent)
        }
    }

    /**
     * 获取文件路径 Kitkat 之前版本
     */
    fun getPathByUri(context: Context, data: Uri): String? {
        var filename: String? = null
        if (data.scheme?.compareTo("content") == 0) {
            val cursor = context.contentResolver.query(data, arrayOf("_data"), null, null, null)
            if (cursor!!.moveToFirst()) {
                filename = cursor.getString(0)
            }
            cursor.close()
        } else if (data.scheme?.compareTo("file") == 0) {// file:///开头的uri
            filename = data.toString().replace("file://", "")// 替换file://
        }
        return filename
    }

    /**
     * 获取文件路径 Kitkat 之后版本
     */
    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun getPathByUri4kitkat(context: Context, uri: Uri): String? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id)!!)
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                when (type) {
                    "image" -> contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    "video" -> contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    "audio" -> contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {// MediaStore
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {// File
            return uri.path
        }
        return null
    }

    private fun getDataColumn(context: Context, uri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val colume = "_data"
        val projection = arrayOf(colume)
        try {
            cursor = context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(colume)
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
    }
}