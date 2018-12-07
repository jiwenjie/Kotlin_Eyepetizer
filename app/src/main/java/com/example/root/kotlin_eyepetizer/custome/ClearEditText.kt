package com.example.root.kotlin_eyepetizer.custome

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.example.root.kotlin_eyepetizer.R

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/02
 *  desc:帶刪除按鈕的 EditText
 *  version:1.0
 */
class ClearEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                              defStyle: Int = android.R.attr.editTextStyle)
   : EditText(context, attrs, defStyle), View.OnFocusChangeListener, TextWatcher {

   //EditText 右側的刪除按鈕
   private var mClearDrawable: Drawable? = null
   private var hasFocus: Boolean = false

   init {
      init()
   }

   @Suppress("DEPRECATION")
   private fun init() {
      // 獲取 EditText 的 DrawableRight, 加入沒有設置我們就使用默認的圖片，獲取圖片的順序是左上右下（0，1，2，3）
      mClearDrawable = compoundDrawables[2]
      if (mClearDrawable == null) {
         mClearDrawable = resources.getDrawable(R.mipmap.ic_action_clear)
      }

      mClearDrawable!!.setBounds(0, 0, mClearDrawable!!.intrinsicWidth, mClearDrawable!!.intrinsicHeight)
      // 設置默認隱藏圖標
      setClearIconVisible(false)
      // 設置焦點改變的監聽
      onFocusChangeListener = this
      // 設置輸入框裏面内容改變的監聽
      addTextChangedListener(this)
   }

   /* @说明：isInnerWidth, isInnerHeight为true，触摸点在删除图标之内，则视为点击了删除图标
     * event.getX() 获取相对应自身左上角的X坐标
     * event.getY() 获取相对应自身左上角的Y坐标
     * getWidth() 获取控件的宽度
     * getHeight() 获取控件的高度
     * getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离
     * getPaddingRight() 获取删除图标右边缘到控件右边缘的距离
     * isInnerWidth:
     *  getWidth() - getTotalPaddingRight() 计算删除图标左边缘到控件左边缘的距离
     *  getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
     * isInnerHeight:
     *  distance 删除图标顶部边缘到控件顶部边缘的距离
     *  distance + height 删除图标底部边缘到控件顶部边缘的距离
     */
   @SuppressLint("ClickableViewAccessibility")
   override fun onTouchEvent(event: MotionEvent?): Boolean {
      if (event!!.action == MotionEvent.ACTION_UP) {
         if (compoundDrawables[2] != null) {
            val x = event.x.toInt()
            val y = event.y.toInt()
            val rect = compoundDrawables[2].bounds
            val height = rect.height()
            val distance = (getHeight() - height) / 2
            val isInnerWidth = x > width - totalPaddingRight && x < width - paddingRight
            val isInnerHeight = y > distance && y < distance + height
            if (isInnerWidth && isInnerHeight) {
               this.setText("")
            }
         }
      }
      return super.onTouchEvent(event)
   }


   /**
    * 儅 ClearEditText 焦點發生變化的時候
    * 儅輸入長度為 0，隱藏刪除圖標沒否則，顯示刪除圖標
    */
   override fun onFocusChange(v: View?, hasFocus: Boolean) {
      this.hasFocus = hasFocus
      if (hasFocus) {
         setClearIconVisible(text.isNotEmpty())
      } else {
         setClearIconVisible(false)
      }
   }

   private fun setClearIconVisible(visible: Boolean) {
      val right = if (visible) mClearDrawable else null
      setCompoundDrawables(compoundDrawables[0],
              compoundDrawables[1], right, compoundDrawables[3])
   }

   override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
      if (hasFocus) {
         setClearIconVisible(text.isNullOrEmpty())
      }
   }

   override fun afterTextChanged(s: Editable?) {

   }

   override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

   }
}










