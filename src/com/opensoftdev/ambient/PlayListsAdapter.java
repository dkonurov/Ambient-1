package com.opensoftdev.ambient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayListsAdapter extends BaseAdapter {
	
	private Context context;
	
	private String[] data;
	
	private boolean editable;
	
	private int size;
	
	PlayListsAdapter(Context c, String[] data, boolean editable) {
		this.context = c;
		this.data = data;
		this.editable = editable;
		this.size = data.length;
	}

	@Override
	public int getCount() {
		return size;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		LayoutInflater inflater;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (!editable) {
			view = inflater.inflate(R.layout.play_lists_item, null);
	        
	        TextView playListName = (TextView) view.findViewById(R.id.play_list_name);
	        playListName.setText(data[position]);
		} else {
			view = inflater.inflate(R.layout.play_lists_editable_item, null);
	        
			EditText playListName = (EditText) view.findViewById(R.id.play_list_name_editable);
	        playListName.setText(data[position]);
	        playListName.setFocusable(false);
	        
	        //TextView delsw = (TextView) view.findViewById(R.id.delete_swipe);
		}		
		return view;
	}
	
	public void setNewData(String[] asd) {
		this.data = asd;
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

}
