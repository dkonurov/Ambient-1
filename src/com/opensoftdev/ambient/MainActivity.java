package com.opensoftdev.ambient;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ExpandableListView mainList;
	
	private MainListAdapter mainListAdapter;
	
	private int openedView = -1;
	
	String data[] = {"8=э", "8==э", "8===э", "8====э"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainList = (ExpandableListView) findViewById(R.id.main_list);
        mainListAdapter = new MainListAdapter(this, data);
        mainList.setAdapter(mainListAdapter);
        mainList.setGroupIndicator(null);
        
        mainList.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				TextView text = (TextView) v.findViewById(R.id.expand);
				Drawable shape = getApplicationContext().getResources().getDrawable(R.drawable.up);
				text.setBackgroundDrawable(shape);
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
    
}
