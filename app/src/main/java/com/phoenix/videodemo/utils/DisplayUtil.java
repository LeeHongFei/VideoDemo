package com.phoenix.videodemo.utils;

import android.content.Context;

/**
 * dp��spת��Ϊpx�Ĺ�����
 */
public class DisplayUtil {
	/**
	 * ��pxֵת��Ϊdip��dpֵ����֤�ߴ��С����
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/**
	 * ��dip��dpֵת��Ϊpxֵ����֤�ߴ��С����
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
	
	/**
	 * ��pxֵת��Ϊspֵ����֤���ִ�С����
	 * @param pxValue
	 * @param fontScale
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (pxValue / fontScale + 0.5f); 
    }
	
	/**
     * ��spֵת��Ϊpxֵ����֤���ִ�С����
     * 
     * @param spValue
     * @param fontScale
     *            ��DisplayMetrics��������scaledDensity��
     * @return
     */ 
    public static int sp2px(Context context, float spValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (spValue * fontScale + 0.5f); 
    }
}
