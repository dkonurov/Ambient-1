package com.opensoftdev.ambient;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
	        playListName.setBackgroundDrawable(null);
	        
	        TextView asd = new TextView(context);
	        asd.setOnTouchListener(this);
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT);
	        params.weight = 2;
	        asd.setLayoutParams(params);
	        asd.setText("delete");
	        asd.setGravity(Gravity.CENTER);
	        TranslateAnimation anim  = new TranslateAnimation(100,0,0,0);
	        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linerLayout_editable_item);
	        anim.setDuration(300);
	        asd.setTextColor(Color.RED);
	        asd.setAnimation(anim);
	        layout.addView(asd);
	        anim.start();
	        playListName.setFocusable(false);
	        playListName.setOnTouchListener(this);
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
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), imm.HIDE_NOT_ALWAYS);
			if (text != null) {
				text.setFocusable(false);
			}
		}
		int action = event.getAction();
		return false;
	}
}
