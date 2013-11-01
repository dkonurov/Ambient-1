package com.opensoftdev.ambient;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.agimind.widget.ExpandableAdapter;

public class MainActivity extends Activity implements OnClickListener {

	private ListView mainList;
	
	private MainListAdapter adapter;
	
	private int openedView = -1;
	
	static public ConteinerMediaPlayer conteiner;
	
	private Intent intent;
	
	Animation anim;
	
	/** Buttons **/
	private Button settings, playLists, playAndStop;
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
        playAndStop = (Button) findViewById(R.id.buttonPress);
        
        conteiner = new ConteinerMediaPlayer();
        
        settings.setOnClickListener(this);
        playLists.setOnClickListener(this);
        playAndStop.setOnClickListener(this);
        
        mainList = (ListView) findViewById(R.id.main_list);
        adapter = new MainListAdapter(this, data, conteiner);
        
        ListAdapter listAdapter = new ExpandableAdapter(this, adapter, R.id.expand, R.id.track_volume);
        mainList.setAdapter(listAdapter);
        
    }

	@Override
	public void onClick(View v) {
		Intent activity;
		switch (v.getId()){
		case R.id.settings:
			activity = new Intent(MainActivity.this, SettingsActivity.class);
			startActivity(activity);
			overridePendingTransition(R.anim.down_up, R.anim.none);
			break;
		case R.id.play_lists:
			activity = new Intent(this, PlayListsActivity.class);
			startActivity(activity);
			overridePendingTransition(R.anim.left_right, R.anim.none);
			break;
		case R.id.buttonPress:
			conteiner.playingAndStop(!conteiner.isPlaying());
		}
	}
    
}
