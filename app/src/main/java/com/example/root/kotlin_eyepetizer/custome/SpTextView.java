package com.example.root.kotlin_eyepetizer.custome;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2018/11/07
 * desc:使用 Java 重写 TextView 加载字体文件
 * version:1.0
 */
public class SpTextView extends android.support.v7.widget.AppCompatTextView {

    private Context mContext;

    public SpTextView(Context context) {
        super(context);
        this.mContext = context;
        initText();
    }

    public SpTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initText();
    }

    private void initText() {
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Lobster-1.4.otf");
        setTypeface(typeface);
    }

}
