package com.haiwai.housekeeper.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

/**
 * Created by ihope10 on 2016/9/29.
 */
public class SpanUtil {
    public static Spannable getNewString(String str, int small, int smallStart, int smallEnd, int big, int bigStart, int bigEnd) {
        Spannable spannable = new SpannableString(str);
        spannable.setSpan(new AbsoluteSizeSpan(small, true), smallStart, smallEnd, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannable.setSpan(new AbsoluteSizeSpan(big, true), bigStart, bigEnd, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannable;
    }

    public static Spannable getNew2String(String str, int big, int bigStart, int bigEnd) {
        Spannable spannable = new SpannableString(str);
        spannable.setSpan(new AbsoluteSizeSpan(big, true), bigStart, bigEnd, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return spannable;
    }
}
