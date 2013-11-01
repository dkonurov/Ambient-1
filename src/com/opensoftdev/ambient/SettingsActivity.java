package com.opensoftdev.ambient;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class SettingsActivity extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private Button closeSettings;
	private ToggleButton setMulti;
	public ConteinerMediaPlayer conteiner = MainActivity.conteiner;
	private Service service;
	private Intent intent;
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.settings);
		getWindow().getAttributes().windowAnimations = R.style.SettingsAnimation;
		
		closeSettings = (Button) findViewById(R.id.close_settings);
		closeSettings.setOnClickListener(this); 
		
		setMulti = (ToggleButton) findViewById(R.id.multi_set);
		setMulti.setChecked(conteiner.getMulti());
		setMulti.setOnCheckedChangeListener(this);
		
		LinearLayout resetVolume = (LinearLayout) findViewById(R.id.resetVolume);
		resetVolume.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.close_settings:
			finish();
			break;
		case R.id.resetVolume:
			conteiner.resetVolume();
			break;
		}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
			case R.id.multi_set:
				if (arg1) {
					conteiner.setMulti();
				} else {
					conteiner.offMulti();
				}
		}
	}
	
}
