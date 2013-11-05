package com.opensoftdev.ambient;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PlayListsActivity extends Activity implements OnClickListener, OnItemClickListener {
	
	private Button closePlayLists, editPlayLists;
	
	private ArrayList<String> data = new ArrayList<String>();
	
	private boolean isFirstClick = true;
	
	
	
	PlayListsAdapter adapterNotEditable, adapterEditable;
	ListView playLists;
	
	protected void onCreate(Bundle savedState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedState);
		setContentView(R.layout.play_lists);
		getWindow().getAttributes().windowAnimations = R.style.PlayListsAnimation;
		
		data.add("asd1");
		data.add("asd2");
		data.add("asd3");
		data.add("asd4");
		data.add("asd5");
		
		closePlayLists = (Button) findViewById(R.id.close_play_lists);
		closePlayLists.setOnClickListener(this);
		
		editPlayLists = (Button) findViewById(R.id.edit_play_lists);
		editPlayLists.setOnClickListener(this);

		playLists = (ListView) findViewById(R.id.play_lists_list);
		
		adapterNotEditable = new PlayListsAdapter(this, data, false);

		playLists.setAdapter(adapterNotEditable);
		playLists.setOnItemClickListener(null);

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
				adapterEditable = new PlayListsAdapter(this, data, true);
				SwipeDismissListViewTouchListener listener = new SwipeDismissListViewTouchListener(playLists,
						new SwipeDismissListViewTouchListener.OnDismissCallback() {
							
							@Override
							public void onDismiss(ListView listView, int[] reverseSortedPositions) {
								// TODO Auto-generated method stub
								 for (int position : reverseSortedPositions) {
	                                    adapterEditable.removeItem(position);
	                                }
								adapterEditable.notifyDataSetChanged();
							}
						});
				playLists.setAdapter(adapterEditable);
				playLists.setOnTouchListener(listener);
				playLists.setOnItemClickListener(this);
				isFirstClick = false;
			} else {
				editPlayLists.setText("edit");
				adapterNotEditable = new PlayListsAdapter(this, data, false);
				playLists.setAdapter(adapterNotEditable);
				playLists.setOnItemClickListener(null);
				playLists.setOnTouchListener(null);
				isFirstClick = true;
			}
			
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		TextView asd = (TextView) v.findViewById(R.id.play_list_name);
		
		TranslateAnimation anim = new TranslateAnimation(0,-v.getWidth(),0,0);
		anim.setDuration(300);
		v.startAnimation(anim);
		
		final int pos = position;
		
		anim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation animation) {
				//data.remove(pos);
				adapterEditable.removeItem(pos);
				adapterEditable.notifyDataSetChanged();
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationStart(Animation animation) {}
			
		});
		
		//Toast.makeText(getApplicationContext(), "111", 20).show();

	}
	
}
