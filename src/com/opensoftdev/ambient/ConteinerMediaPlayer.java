/*
 * ConteinerMediaPlayer
 * 
 * version 1.0
 * 
 * 29.10.2013
 * 
 * copyright opensoftdev
 */
package com.opensoftdev.ambient;

import java.util.ArrayList;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



public class ConteinerMediaPlayer {
	
	private ArrayList <CustomMediaPlayer> conteinerMediaPlayer = new ArrayList <CustomMediaPlayer> ();
	private ArrayList <TextView> conteinerTextView = new ArrayList <TextView> ();
	private ArrayList <SeekBar> conteinerSeekBar = new ArrayList <SeekBar> ();
	private int select;
	private int lastPlayer;
	private boolean mSetMulti = false;
	
	ConteinerMediaPlayer(){
		select = 0;
		lastPlayer = 0;
	}

	
	public void addView(final CustomMediaPlayer player) {
		conteinerMediaPlayer.add(player);		
		select++;
	}
	
	public void setMulti() {
		if (!mSetMulti) {
			mSetMulti = true;
		}
		
	}
	
	public void setTextView(final TextView textview, final int id) {
		if (conteinerTextView.size()-1 < id) {
			conteinerTextView.add(textview);
		} else {
			conteinerTextView.set(id, textview);
		}
		textview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (conteinerMediaPlayer.get(id).isPlaying()) {
					conteinerMediaPlayer.get(id).stop();
					textview.setTextColor(Color.BLACK);
				} else {
					if (!mSetMulti) {
						if (conteinerMediaPlayer.get(lastPlayer).isPlaying()) {
							conteinerMediaPlayer.get(lastPlayer).stop();
							conteinerTextView.get(lastPlayer).setTextColor(Color.BLACK);
						}
					}
					conteinerMediaPlayer.get(id).start();
					lastPlayer = conteinerMediaPlayer.get(id).getNumber();
					textview.setTextColor(Color.GREEN);
				}
			}
			
		});
		if (conteinerMediaPlayer.get(id).isPlaying()) {
			textview.setTextColor(Color.GREEN);
		}
	}
	
	public void offMulti() {
		if (mSetMulti) {
			mSetMulti = false;
		}
		for (int i =0; i<=select;i++) {
			if (conteinerMediaPlayer.get(i).isPlaying()) {
				conteinerMediaPlayer.get(i).stop();
				conteinerTextView.get(i).setTextColor(Color.BLACK);
			}
		}
	}
	
	public void resetVolume() {
		for (int i = 0; i <= select; i++) {
			conteinerMediaPlayer.get(i).setVolume(0.5f);
			conteinerSeekBar.get(i).setProgress(50);
		}
	}
	
	public void setSeekBar(final int id, SeekBar _bar) {
		_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				float volume = (float) arg1/100;
				conteinerMediaPlayer.get(id).setVolume(volume);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		int volume = conteinerMediaPlayer.get(id).getVolume();
		_bar.setProgress(volume);
	}
	
	protected void Destroy(int id) {
		conteinerMediaPlayer.get(id).Destroy();
		conteinerMediaPlayer.remove(id);
		conteinerSeekBar.remove(id);
		conteinerTextView.remove(id);
		select--;
	}
	
	public boolean isSet(int id) {
		if (conteinerMediaPlayer.size()-1 < id) {
			return false;
		}
		else {
			return true;
		}
	}
			
}
