package com.opensoftdev.ambient;

import android.app.Activity;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class SettingsActivity<LocalBinder> extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private Button closeSettings;
	private ToggleButton setMulti;
	private ConteinerMediaPlayer conteiner;
	private Service service;
	ServiceConnection mp3PlayerServiceConntection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder arg1) {
			// TODO Auto-generated method stub
			conteiner = ((ConteinerMediaPlayer.LocalBinder) arg1).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.settings);
		getWindow().getAttributes().windowAnimations = R.style.SettingsAnimation;
		
		closeSettings = (Button) findViewById(R.id.close_settings);
		closeSettings.setOnClickListener(this);
		Intent intent = new Intent(this, ConteinerMediaPlayer.class);
		bindService(intent, mp3PlayerServiceConntection, BIND_AUTO_CREATE);
		Log.v("conteiner", conteiner+"");
		setMulti = (ToggleButton) findViewById(R.id.multi_set);
		setMulti.setOnCheckedChangeListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.close_settings:
			finish();
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
