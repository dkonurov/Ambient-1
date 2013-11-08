package com.opensoftdev.ambient;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlayListsAdapter extends BaseAdapter implements View.OnTouchListener {
	
	private Context context;
	
	private ArrayList<String> data;
	
	private boolean editable;
	
	private EditText text;
	
	private int size;

	private boolean setAnimation = true;
	
	PlayListsAdapter(Context c, ArrayList<String> data, boolean editable, Button _editPlayLists) {
		this.context = c;
		this.data = data;
		this.editable = editable;
		this.size = data.size();
		if (_editPlayLists != null) {
			_editPlayLists.setOnTouchListener(this);
		}
	}
	//if editable add button click on done save changed
	PlayListsAdapter(Context c, ArrayList<String> data, boolean editable) {
		this(c,data,editable,null);
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
			playListName.setTag(position);
	        playListName.setText(data.get(position));
	        playListName.setBackgroundDrawable(null);
	        
	        TextView delete = new TextView(context);
	        
	        // LinearLayout Params
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT);
	        params.weight = 2;
	        delete.setLayoutParams(params);
	        delete.setText("delete");
	        delete.setGravity(Gravity.CENTER);
	        delete.setTextColor(Color.RED);
	        
	        // TranslateAnimation
	        TranslateAnimation anim  = new TranslateAnimation(100,0,0,0);
	        LinearLayout layout = (LinearLayout) view.findViewById(R.id.linerLayout_editable_item);
	        anim.setDuration(300);
	        
	        // addView and Start Animation
	        layout.addView(delete);
	        if (setAnimation ) {
		        delete.setAnimation(anim);
		        anim.start();
	        }
	        
	        playListName.setFocusable(false);
	        playListName.setOnTouchListener(this);
		}
		view.setOnTouchListener(this);
		return view;
	}
	
	public void removeItem(int position) {
		data.remove(position);
		size--;
	}
	
	@Override
	public void notifyDataSetChanged() {
		setAnimation = false;
		super.notifyDataSetChanged();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (text != null) {
			
			// hide input soft 
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), imm.HIDE_NOT_ALWAYS);
			
			//setFocusable false because can set clickable listview
			text.setFocusable(false);
			String seterText = text.getText().toString();
			int number = Integer.parseInt(text.getTag()+"");
			data.set(number, seterText);
		}
		
		if (v instanceof EditText) {
			
			v.setFocusable(true);
			v.setFocusableInTouchMode(true);
			text = (EditText) v;
		}
		
		return false;
	}
	
	public ArrayList<String> getData() {
		return data;
	}
}
