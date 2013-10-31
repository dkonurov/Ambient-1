package com.opensoftdev.ambient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class PlayListsActivity extends Activity implements OnClickListener {
	
	private Button closePlayLists;
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.play_lists);
		getWindow().getAttributes().windowAnimations = R.style.PlayListsAnimation;
		
		closePlayLists = (Button) findViewById(R.id.close_play_lists);
		closePlayLists.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.close_play_lists:
			finish();
			break;
		}
	}
	
}
