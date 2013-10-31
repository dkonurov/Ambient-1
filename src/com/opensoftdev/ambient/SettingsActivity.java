package com.opensoftdev.ambient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class SettingsActivity extends Activity implements OnClickListener {
	
	private Button closeSettings;
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.settings);
		getWindow().getAttributes().windowAnimations = R.style.SettingsAnimation;
		
		closeSettings = (Button) findViewById(R.id.close_settings);
		closeSettings.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.close_settings:
			finish();
			break;
		}
	}
	
}
