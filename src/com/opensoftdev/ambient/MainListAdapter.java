package com.opensoftdev.ambient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainListAdapter extends BaseAdapter {
	
	private Context context;
	private String[] data;
	private int lastPosition;
	private int select = 0;
	private static ConteinerMediaPlayer conteiner;
	
	MainListAdapter(Context c, String[] data, ConteinerMediaPlayer _conteiner){
		this.context = c;
		this.data = data;
		conteiner = _conteiner;
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
        if (!conteiner.isSet(position)) {
            CustomMediaPlayer player;
            if(position == 0) {
                    player = new CustomMediaPlayer(context, R.raw.comin_in_hot, position);
                    conteiner.addView(player);
            } else if (position == 1) {
                    player = new CustomMediaPlayer(context, R.raw.coming_back_down, position);
                    conteiner.addView(player);
            } else {
                    player = new CustomMediaPlayer(context, R.raw.do_neba, position);
                    conteiner.addView(player);
            }
        }
        conteiner.setTextView(tv, position);
        SeekBar trackVolume = (SeekBar) view.findViewById(R.id.track_volume);
        conteiner.setSeekBar(position, trackVolume);
		return view;
	}
	
}
