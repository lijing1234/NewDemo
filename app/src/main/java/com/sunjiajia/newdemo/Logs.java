/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.newdemo;

import android.util.Log;

public class Logs {
	private static final String TAG = "Wei-";
	
	public static void v(String name, String msg) {
		Log.d(TAG + name,  msg);
	}
	
	public static void d(String name, String msg) {
		Log.d(TAG + name,  msg);
	}
	
	public static void w(String name, String msg) {
		Log.d(TAG + name,  msg);
	}
	
	public static void w(String name, String msg, Exception e) {
		Log.e(TAG + name, msg, e);
	}
	
	public static void w(String name, String msg, Throwable t) {
		Log.e(TAG + name, msg, t);
	}
	
	public static void e(String name, String msg) {
		Log.e(TAG + name, msg);
	}
	
	public static void e(String name, String msg, Exception e) {
		Log.e(TAG + name, msg, e);
	}
}
