package com.example.baselibrary.baseviews

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.*

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2018/12/09
 *  desc: DialogFragment 基类，dialog，popupWindow 推荐使用这个基类，方便生命周期管理
 *  tips：popupWindow 的基类，涉及 popupWindow 界面存在 fragment 的时候禁止使用
 *  通过 AlertDialog.setView 添加自定义的 dialog view
 *  version:1.0
 */
abstract class BaseDialogFragment : DialogFragment() {

    private var mWidth: Int = WindowManager.LayoutParams.WRAP_CONTENT
    private var mHeight: Int = WindowManager.LayoutParams.WRAP_CONTENT
    private var mGravity: Int = Gravity.CENTER
    private var mAnimation: Int = 0
    private var mOnDismissListener: (() -> Unit)? = null

    fun setOnDismissListener(l: (() -> Unit)?) {
        this.mOnDismissListener = l
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)  // 去除 AlertDialog 可能会出现的标题
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Material_Dialog_Alert)

        if (arguments != null) {
            mWidth = arguments!!.getInt(DIALOG_WIDTH_TYPE)
            mHeight = arguments!!.getInt(DIALOG_HEIGHT_TYPE)
            mGravity = arguments!!.getInt(DIALOG_GRAVITY)
            mAnimation = arguments!!.getInt(DIALOG_ANIMATION)
        }

        if (mAnimation != 0) dialog.window?.setWindowAnimations(mAnimation)

        return inflater.inflate(getLayoutId(), container, false)
    }

    /* put your height, width, gravity, animation param here */
    fun putExtraParam(
        widthType: Int = WindowManager.LayoutParams.WRAP_CONTENT,
        heightType: Int = WindowManager.LayoutParams.WRAP_CONTENT,
        gravity: Int = Gravity.CENTER, anim: Int = 0
    ) {
        val bundle = Bundle()
        bundle.putInt(DIALOG_WIDTH_TYPE, widthType)
        bundle.putInt(DIALOG_HEIGHT_TYPE, heightType)
        bundle.putInt(DIALOG_GRAVITY, gravity)
        bundle.putInt(DIALOG_ANIMATION, anim)
        this.arguments = bundle
    }

    override fun onDestroy() {
        super.onDestroy()
        mOnDismissListener?.invoke()    /** 查一下这行代码什么意思和作用 **/
    }

    override fun onStart() {
        super.onStart()

        /* set dialog param */
        dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        val attrs = dialog.window!!.attributes
        attrs.width = mWidth
        attrs.height = mHeight
        attrs.gravity = mGravity
        dialog.window!!.attributes = attrs
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialogView()
    }

    abstract fun getLayoutId(): Int

    protected open fun initDialogView() { }

    companion object {
        const val DIALOG_WIDTH_TYPE = "com.base.dialog.width"
        const val DIALOG_HEIGHT_TYPE = "com.base.dialog.height"
        const val DIALOG_GRAVITY = "com.base.dialog.gravity"
        const val DIALOG_ANIMATION = "com.base.dialog.animation"
    }
}






















