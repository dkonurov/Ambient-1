package com.opensoftdev.ambient;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {
	
	private Context context;
	private String[] data;
	private LayoutInflater inflater;
	
	MainListAdapter(Context c, String[] data){
		this.context = c;
		this.data = data;
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
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		Log.v("position", position+"");
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.main_listview_fragment, null);
		
		TextView tv = (TextView) view.findViewById(R.id.track_name);
		CustomMediaPlayer player;
		if (position == 0) {
			player = new CustomMediaPlayer(context,R.raw.do_neba);
		} else if (position == 1) {
			player = new CustomMediaPlayer(context,R.raw.comin_in_hot);
		} else {
			player = new CustomMediaPlayer(context,R.raw.coming_back_down);
		}
		player.setView(tv);
		tv.setText(data[position]);
		return view;
	}

}
