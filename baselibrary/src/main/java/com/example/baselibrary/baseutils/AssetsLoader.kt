package com.example.baselibrary.baseutils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/09
 *  desc: asserts 文件加载工具类
 *  version:1.0
 */
object AssetsLoader {

    @JvmStatic  // 增加该注解后 Java 调用就不用在增加 INSTANCE 了
    fun getTextFromAssets(context: Context, file: String): String {
        var inputString: InputStream? = null
        try {
            inputString = context.resources.assets.open(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return getText(inputString)
    }

    private fun getText(inputStream: InputStream?): String {
        var inputReader: InputStreamReader? = null
        var bufferedReader: BufferedReader? = null
        val result = StringBuilder()
        try {
            inputReader = InputStreamReader(inputStream!!)
            bufferedReader = BufferedReader(inputReader)

            var line: String?
            do {
                line = bufferedReader.readLine()
                if (line != null) result.append(line) else break
            } while (true)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            bufferedReader?.close()
            inputReader?.close()
        }
        return result.toString()
    }

    @JvmStatic
    fun getImageFromAssets(context: Context, file: String): Bitmap? {
        var image: Bitmap? = null
        val am = context.resources.assets
        try {
            val inputStream = am.open(file)
            image = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return image
    }
}















