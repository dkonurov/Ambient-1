package com.opensoftdev.ambient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SettingsActivity extends Activity {
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.settings);
		getWindow().getAttributes().windowAnimations = R.style.SettingsAnimation;
	}
}
