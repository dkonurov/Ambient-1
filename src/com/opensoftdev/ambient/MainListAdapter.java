package com.opensoftdev.ambient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainListAdapter extends BaseExpandableListAdapter {
	
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
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;
		
		LayoutInflater inflater;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.main_list_seek_bar, null);
        
        SeekBar trackVolume = (SeekBar) view.findViewById(R.id.track_volume);
        
        conteiner.setSeekBar(groupPosition, trackVolume);
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return data.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = convertView;
		
		LayoutInflater inflater;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.main_listview_fragment, null);
        
        final TextView tv = (TextView) view.findViewById(R.id.track_name);
        tv.setText(data[groupPosition]);
        TextView expand = (TextView) view.findViewById(R.id.expand);
        Drawable shape = context.getResources().getDrawable(R.drawable.down);
        expand.setBackgroundDrawable(shape);
        
        if (!conteiner.isSet(groupPosition)) {
        	CustomMediaPlayer player;
        	if(groupPosition == 0) {
        		player = new CustomMediaPlayer(context, R.raw.comin_in_hot, groupPosition);
        		conteiner.addView(player);
        	} else if (groupPosition == 1) {
        		player = new CustomMediaPlayer(context, R.raw.coming_back_down, groupPosition);
        		conteiner.addView(player);
        	} else {
        		player = new CustomMediaPlayer(context, R.raw.do_neba, groupPosition);
        		conteiner.addView(player);
        	}
        }
        
        conteiner.setTextView(tv, groupPosition);

		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
