package com.haiwai.housekeeper.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ihope10 on 2016/11/11.
 */

public class TextFontView extends TextView {
    public TextFontView(Context context) {
        this(context, null);
    }

    public TextFontView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextFontView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/fzltcxhjt.TTF");
        setTypeface(typeface);
//        Typeface.ITALIC
    }
}
