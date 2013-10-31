package com.opensoftdev.ambient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private ExpandableListView mainList;
	
	private MainListAdapter mainListAdapter;
	
	private int openedView = -1;
	
	Animation anim;
	
	/** Buttons **/
	private Button settings, playLists;
	
	private TextView currentPlayList;
	
	String data[] = {"8=э", "8==э", "8===э", "8====э"};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        settings = (Button) findViewById(R.id.settings);
        playLists = (Button) findViewById(R.id.play_lists);
        currentPlayList = (TextView) findViewById(R.id.list_name);
        
        settings.setOnClickListener(this);
        playLists.setOnClickListener(this);
        
        mainList = (ExpandableListView) findViewById(R.id.main_list);
        mainListAdapter = new MainListAdapter(this, data);
        mainList.setAdapter(mainListAdapter);
        mainList.setGroupIndicator(null);

        
        anim = AnimationUtils.loadAnimation(this, R.anim.down_up);
        
        mainList.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				
				
				return false;
			}
        	
        });
        mainList.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				
				if (openedView != groupPosition && openedView != -1) {
					mainList.collapseGroup(openedView);
				}
				openedView = groupPosition;
				
			}
        });
    }

	@Override
	public void onClick(View v) {
		Intent activity;
		switch (v.getId()){
		case R.id.settings:
			activity = new Intent(this, SettingsActivity.class);
			startActivity(activity);
			overridePendingTransition(R.anim.down_up, R.anim.none);
			break;
		case R.id.play_lists:
			activity = new Intent(this, PlayListsActivity.class);
			startActivity(activity);
			overridePendingTransition(R.anim.left_right, R.anim.none);
			break;
		}
	}
    
}
