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

package com.sunjiajia.newdemo.hardware;

import android.content.Context;

import com.ieimobile.ikar07.IKar07;
import com.ieimobile.ikar07.IKar07.LEDTYPE;

/**
 * @brief A class to handle DIO, switch RS232/422/485 mode and etc.  
 */

public class Gpio {
	private static final String TAG = "IEIMobile_HW";
	
	public static final int MODE_RS232 = IKar07.MODE_RS232;
	public static final int MODE_RS422 = IKar07.MODE_RS422;
	public static final int MODE_RS485 = IKar07.MODE_RS485;
	
	public static final String SERIALPORT_DEVICE1 = IKar07.SERIALPORT_DEVICE1;
	//public static final String SERIALPORT_DEVICE2 = IKar07.SERIALPORT_DEVICE2;
	
	public static final int NUM_OF_DIGITAL_INPUT = IKar07.NUM_OF_DIGITAL_INPUT;
	public static final int NUM_OF_DIGITAL_OUTPUT = IKar07.NUM_OF_DIGITAL_OUTPUT;
			
	private final IKar07 mIKar07Lib;
		
	public Gpio(Context ctx) {
		mIKar07Lib = new IKar07(ctx);
	}	
	
	public boolean setLEDOnOff(LEDTYPE led, boolean on) {
		return mIKar07Lib.setLEDOnOff(led, on);
	}
	
	public int getAudioOutput() {
		return mIKar07Lib.getAudioOutput();
	}
	
	public boolean setAudioOutput(int target) {
		return mIKar07Lib.setAudioOutput(target);
	}
	
	/**
	 * Set the value of digital output
	 * @param index digital output index, based from 0 
	 * @param value 1=HIGH or 0=LOW
	 */
	public void setDO(int index, int value) {
		mIKar07Lib.setDigitalOutput(index, value);
	}
	
	/**
	 * Get the value of digital input
	 * @param index digital input index, based from 0
	 * @return 1 if digital input= HIGH; otherwise return 0;
	 */
	public int getDI(int index) {		
		return mIKar07Lib.getDigitalInput(index);
	}
//	
//	/**
//	 * Set serial communication mode
//	 * @param mode MODE_RS232, MODE_RS422 or MODE_RS485
//	 */
//	public boolean setSerialMode(int mode) {
//		return mIKar07Lib.setSerialMode(mode);
//	}
	
	public int getSerialMode() {
		return mIKar07Lib.getSerialMode();
	}

}
