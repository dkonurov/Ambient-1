package com.opensoftdev.ambient;

import android.content.Context;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MainList extends ExpandableListView {

	private Context context;
	
	public MainList(Context context) {
		super(context);
		this.context = context;
	}
	
	public boolean expandGroup(int pos) {
		super.expandGroup(pos);
		
		return false;
	}

	public void setOnItemClickListener(AdapterView.OnItemClickListener l) {
		Toast.makeText(context, "asd", 1000).show();
		
	}

}
