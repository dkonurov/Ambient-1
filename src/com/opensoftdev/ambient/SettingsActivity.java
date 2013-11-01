package com.opensoftdev.ambient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ToggleButton;
import android.app.Dialog;
import android.content.DialogInterface;

public class SettingsActivity extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private Button closeSettings;
	private ToggleButton setMulti;
	private ToggleButton autoLock;
	
	public DialogInterface.OnClickListener dialogClickListener;
	
	public ConteinerMediaPlayer conteiner = MainActivity.conteiner;
	
	private PowerManager powerManager;
	private WakeLock wakeLock;
	
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
		
		autoLock = (ToggleButton) findViewById(R.id.auto_lock);
		autoLock.setChecked(false);
		autoLock.setOnCheckedChangeListener(this);
		
		powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(powerManager.FULL_WAKE_LOCK, "AmbientLock");
		
		LinearLayout resetVolume = (LinearLayout) findViewById(R.id.resetVolume);
		resetVolume.setOnClickListener(this);
		
		dialogClickListener = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case Dialog.BUTTON_POSITIVE:
					conteiner.resetVolume();
					dialog.cancel();
					break;
				case Dialog.BUTTON_NEGATIVE:
					dialog.cancel();
				}
			}
		};
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.close_settings:
			finish();
			break;
		case R.id.resetVolume:
			showDialog(0);
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
			case R.id.auto_lock:
				if (autoLock.isChecked()) {
					wakeLock.acquire();
				} else {
					wakeLock.release();				
				}
		}
	}
	
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle("Do you want realy?");
		adb.setMessage("Do you want realy?");
		adb.setPositiveButton("yes", dialogClickListener);
		adb.setNegativeButton("no", dialogClickListener);
		return adb.create();
	}
	
}
