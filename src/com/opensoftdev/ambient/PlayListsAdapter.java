package com.opensoftdev.ambient;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PlayListsAdapter extends BaseAdapter implements View.OnTouchListener {
	
	private Context context;
	
	private ArrayList<String> data;
	
	private boolean editable;
	
	private EditText text;
	
	private int size;
	
	PlayListsAdapter(Context c, ArrayList<String> data, boolean editable) {
		this.context = c;
		this.data = data;
		this.editable = editable;
		this.size = data.size();
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
	        playListName.setText(data.get(position));
		} else {
			view = inflater.inflate(R.layout.play_lists_editable_item, null);
	        
			EditText playListName = (EditText) view.findViewById(R.id.play_list_name_editable);
	        playListName.setText(data.get(position));
	        playListName.setFocusable(false);
	        playListName.setOnTouchListener(this);
	        
	        TextView asd = (TextView) view.findViewById(R.id.delc);
	        asd.setOnTouchListener(this);
		}
		
		
		return view;
	}
	
	public void removeItem(int position) {
		data.remove(position);
		size--;
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (v instanceof EditText) {
			v.setFocusable(true);
			v.setFocusableInTouchMode(true);
			text = (EditText) v;
		} else {
			v.clearFocus();
			if (text != null) {
				text.setFocusable(false);
			}
			Toast.makeText(context, "asd", 10).show();
		}
		return false;
	}

}
