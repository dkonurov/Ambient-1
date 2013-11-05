package com.opensoftdev.ambient;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GavnoConteiner {
	private ArrayList <View> gavnoView = new ArrayList <View> ();
	
	private int select;
	
	private int x, diffX, mLastX = 0;
	
	public GavnoConteiner() {
		select = 0;
	}
	
	public void setView(View view) {
		view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent e) {
				
				int action = e.getAction();
				switch(action) {
					case MotionEvent.ACTION_DOWN:
						x = (int) e.getRawX();
					case MotionEvent.ACTION_MOVE:
						diffX = (int)e.getRawX()- x;
						int mScrollX = diffX - mLastX;
						mLastX = diffX;
						v.scrollBy(mScrollX, 0);
					case MotionEvent.ACTION_UP:
						mLastX = 0;
				}
				
				return false;
			}
		});
	}
	
}
