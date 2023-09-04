package com.fongmi.android.tv;

import android.content.Context;

import com.fongmi.android.tv.bean.Style;
import com.fongmi.android.tv.utils.ResUtil;

public class Product {

    public static int getDeviceType() {
        return 1;
    }

    public static int getColumn() {
        int count = ResUtil.isPad() ? 6 : 5;
        count = count + ResUtil.getOrient() - 1;
        return Math.abs(Setting.getSize() - count);
    }

    public static int getColumn(Style style) {
        return style.isLand() ? getColumn() - 1 : getColumn();
    }

    public static int[] getSpec(Context context) {
        return getSpec(context, Style.rect());
    }

    public static int[] getSpec(Context context, Style style) {
        int column = getColumn(style);
        int space = ResUtil.dp2px(32) + ResUtil.dp2px(16 * (column - 1));
        if (style.isOval()) space += ResUtil.dp2px(column * 16);
        return getSpec(context, space, column, style);
    }

    public static int[] getSpec(Context context, int space, int column) {
        return getSpec(context, space, column, Style.rect());
    }

    private static int[] getSpec(Context context, int space, int column, Style style) {
        int base = ResUtil.getScreenWidth(context) - space;
        int width = base / column;
        int height = (int) (width / style.getRatio());
        return new int[]{width, height};
    }

    public static int getEms() {
        return Math.min(ResUtil.getScreenWidth() / ResUtil.sp2px(20), 25);
    }
}
