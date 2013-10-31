package com.opensoftdev.ambient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {
	
	private Context context;
	private String[] data;
	private int lastPosition;
	private Button buttons[];
	private int select = 0;
	private ConteinerMediaPlayer conteiner = new ConteinerMediaPlayer();
	
	MainListAdapter(Context c, String[] data){
		this.context = c;
		this.data = data;
		buttons = new Button[data.length];
	}

	@Override
	public int getCount() {
		return data.length;
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
        view = inflater.inflate(R.layout.main_listview_fragment, null);
        
        final TextView tv = (TextView) view.findViewById(R.id.track_name);
        tv.setText(data[position]);
        TextView expand = (TextView) view.findViewById(R.id.expand);
        Drawable shape = context.getResources().getDrawable(R.drawable.down);
        expand.setBackgroundDrawable(shape);
		return view;
	}

}
