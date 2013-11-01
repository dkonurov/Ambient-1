package com.opensoftdev.ambient;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayListsActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	private Button closePlayLists, editPlayLists;
	
	private String[] data = {"asd", "zxc", "qwe", "iop"};
	
	private boolean isFirstClick = true;
	
	PlayListsAdapter adapterNotEditable;
	ListView playLists;
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.play_lists);
		getWindow().getAttributes().windowAnimations = R.style.PlayListsAnimation;
		
		closePlayLists = (Button) findViewById(R.id.close_play_lists);
		closePlayLists.setOnClickListener(this);
		
		editPlayLists = (Button) findViewById(R.id.edit_play_lists);
		editPlayLists.setOnClickListener(this);

		playLists = (ListView) findViewById(R.id.play_lists_list);
		
		adapterNotEditable = new PlayListsAdapter(this, data, false);

		playLists.setAdapter(adapterNotEditable);
		playLists.setOnItemClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.close_play_lists:
			finish();
			break;
		case R.id.edit_play_lists:
			if (isFirstClick) {
				editPlayLists.setText("done");			
				PlayListsAdapter adapter = new PlayListsAdapter(this, data, true);
				playLists.setAdapter(adapter);
				playLists.setOnItemClickListener(this);
				isFirstClick = false;
			} else {
				editPlayLists.setText("edit");			
				playLists.setAdapter(adapterNotEditable);
				playLists.setOnItemClickListener(this);
				isFirstClick = true;
			}
			
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3) {
		TextView asd = (TextView) v.findViewById(R.id.play_list_name);
		
		v.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent e) {
				
				LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(0, 0, (int) e.getX(), 0);
                v.setLayoutParams(lp2);
				
				return false;
			}
			
		});
		
		Toast.makeText(getApplicationContext(), "111", 20).show();

	}
	
}
