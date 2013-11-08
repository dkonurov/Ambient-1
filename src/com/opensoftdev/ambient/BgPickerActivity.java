package com.opensoftdev.ambient;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;

public class BgPickerActivity extends Activity implements OnPageChangeListener {

	private ViewPager bg_picker;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bg_picker);
        
        bg_picker = (ViewPager) findViewById(R.id.bg_picker); 
        
        bg_picker.setOnPageChangeListener(this);
        
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
