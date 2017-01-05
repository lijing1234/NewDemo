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

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.sunjiajia.newdemo.hardware.Gpio;

public class GameFragment extends Fragment {
	private Chronometer timer;
	private static final String TAG = "MainActivity";

	public static final int TEST_DIO_PINS = Gpio.NUM_OF_DIGITAL_INPUT;

	private Gpio mGPIOMgr;
	private TextView[] mValueDi = new TextView[TEST_DIO_PINS];
	private Button[]mBtnDo = new Button[2*TEST_DIO_PINS];

	private ReadingThread mReadingThread = null;
	boolean mContinue;
	View view;

	private class ReadingThread extends Thread {
		@Override
		public void run() {

			while (!isInterrupted()) {
				if(!mContinue)
					break;

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						for(int i=0; i < TEST_DIO_PINS; i++)
							getDigitalInput(i);
					}
				});

				try {
					Thread.sleep(500);

				} catch (InterruptedException e) {
					mContinue = false;
					break;
				}
			}
			mReadingThread = null;
		}
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
		 view = (View)inflater.inflate(R.layout.game_frag, null);



    	return view;
    }

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		try {
			mGPIOMgr = new Gpio(getActivity());
		} catch (Exception e) {
			Logs.e(TAG, "Exception : " + e.toString(), e);
		}
		timer = (Chronometer) view.findViewById(R.id.chronometer);
		this.registerForContextMenu(timer);
		mBtnDo[0] = (Button) view.findViewById(R.id.btn00);
		mBtnDo[1] = (Button) view.findViewById(R.id.btn01);
		mBtnDo[2] = (Button) view.findViewById(R.id.btn10);
		mBtnDo[3] = (Button) view.findViewById(R.id.btn11);
//        mBtnDo[4] = (Button) findViewById(R.id.btn20);
//        mBtnDo[5] = (Button) findViewById(R.id.btn21);
//        mBtnDo[6] = (Button) findViewById(R.id.btn30);
//        mBtnDo[7] = (Button) findViewById(R.id.btn31);

		for(int i=0; i < TEST_DIO_PINS * 2; i++) {
			mBtnDo[i].setOnClickListener(mClickListener);
			mBtnDo[i].setBackgroundResource(android.R.drawable.btn_default);
		}

		setDigitalOutput(0, 0);
		mBtnDo[0].setBackgroundResource(android.R.color.holo_blue_bright);
		setDigitalOutput(1, 0);
		mBtnDo[2].setBackgroundResource(android.R.color.holo_blue_bright);

		mValueDi[0] = (TextView) view.findViewById(R.id.di0Value);
		mValueDi[1] = (TextView) view.findViewById(R.id.di1Value);
//        mValueDi[2] = (TextView) findViewById(R.id.di2Value);
//        mValueDi[3] = (TextView) findViewById(R.id.di3Value);

		mContinue = true;

		mReadingThread = new ReadingThread();
		mReadingThread.start();

	}
	private void setDigitalOutput(int pin, int value) {
		if (value != 1 && value != 0) {
			return;
		}

		if (pin >= 0 && pin < TEST_DIO_PINS) {

			//TODO: DO API
			mGPIOMgr.setDO(pin, value);

			Logs.d(TAG, "Set DO["+pin+"]= " + value);
		}
	}
	private int getDigitalInput(int pin) {
		int value = 1;

		if (pin >= 0 && pin < TEST_DIO_PINS) {

			//TODO: DI API
			value = mGPIOMgr.getDI(pin);

//            Logs.d(TAG, "Get DI["+pin+"]= " + value);
			mValueDi[pin].setText(String.valueOf(value));
		}

		return value;
	}

	private View.OnClickListener mClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {

				case R.id.btn00:
					setDigitalOutput(0, 0);
					v.setBackgroundResource(android.R.color.holo_blue_bright);

					mBtnDo[1].setBackgroundResource(android.R.drawable.btn_default);
//					Toast.makeText(getApplicationContext(), ""+getDigitalInput(0), Toast.LENGTH_LONG).show();
					//停止计时
					timer.stop();
					String time = timer.getText().toString();
					break;

				case R.id.btn01:
					setDigitalOutput(0, 1);
					v.setBackgroundResource(android.R.color.holo_blue_bright);

					mBtnDo[0].setBackgroundResource(android.R.drawable.btn_default);
					// 将计时器清零
					timer.setBase(SystemClock.elapsedRealtime());
					//开始计时
					timer.start();
					break;

				case R.id.btn10:
					setDigitalOutput(1, 0);
					v.setBackgroundResource(android.R.color.holo_blue_bright);

					mBtnDo[3].setBackgroundResource(android.R.drawable.btn_default);
					break;

				case R.id.btn11:
					setDigitalOutput(1, 1);
					v.setBackgroundResource(android.R.color.holo_blue_bright);

					mBtnDo[2].setBackgroundResource(android.R.drawable.btn_default);
					break;
			}
		}
	};
	@Override
	public void onResume () {
		super.onResume();
	}
	@Override
	public void onPause() {
		super.onPause();
		Logs.d(TAG, "onPause()");

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Logs.d(TAG, "onDestroy()");

		if(mContinue) {
			mContinue = false;
		}

		if (mReadingThread != null) {
			mReadingThread.interrupt();
			mReadingThread = null;
		}
	}

}
