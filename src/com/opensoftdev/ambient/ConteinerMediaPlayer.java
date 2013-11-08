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

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;



public class ConteinerMediaPlayer {

	/** ArrayList for conteiner, because don't know previously how many be CustomMediaPlayer, TextView and SeekBar */
	public ArrayList <CustomMediaPlayer> conteinerMediaPlayer = new ArrayList <CustomMediaPlayer> ();
	public ArrayList <TextView> conteinerTextView = new ArrayList <TextView> ();
	public ArrayList <SeekBar> conteinerSeekBar = new ArrayList <SeekBar> ();
	
	/** select is selector for conteiner, lastPlayer save number, isPlaying save number's playing player  */
	public int select;
	public int lastPlayer;
	public ArrayList <Integer> isPlaying = new ArrayList <Integer> ();

	/** check multi player  */
	public boolean mSetMulti = true;
	
	/**
	 * constructor class start selector
	 */
	public ConteinerMediaPlayer() {
		
		select = 0;
		lastPlayer = 0;
	}
	
	/**
	 * add player to conteiner
	 * @param CustomMediaPlayer player
	 */
	public void addView(final CustomMediaPlayer player) {
		
		conteinerMediaPlayer.add(player);		
		select++;
	}
	
	/**
	 * set MultiPlayer is true
	 */
	public void setMulti() {
		
		if (!mSetMulti) {
			mSetMulti = true;
		}
		
	}
	
	/**
	 * set TextView for current player
	 * @param TextView textview
	 * @param int id
	 */
	public void setTextView(final TextView textview, final int id) {
		
		if (conteinerTextView.size()-1 < id) {
			conteinerTextView.add(textview);
		} else {
			conteinerTextView.set(id, textview);
		}
		
		textview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				if (conteinerMediaPlayer.get(id).isPlaying()) {
					conteinerMediaPlayer.get(id).stop();
					
					for (int i = 0, length = isPlaying.size(); i < length; i++) {
						if (isPlaying.get(i) == id) {
							isPlaying.remove(i);
							break;
						}
					}
					
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
					isPlaying.add(lastPlayer);
					textview.setTextColor(Color.GREEN);
				}
			}
			
		});
		
		if (conteinerMediaPlayer.get(id).isPlaying()) {
			textview.setTextColor(Color.GREEN);
		}
		
	}
	
	/**
	 * off Multi player and stop all players into continer
	 */
	public void offMulti() {
		
		if (mSetMulti) {
			mSetMulti = false;
		}
		
		for (int i =0; i < select;i++) {
			
			if (conteinerMediaPlayer.get(i).isPlaying()) {
				conteinerMediaPlayer.get(i).stop();
				conteinerTextView.get(i).setTextColor(Color.BLACK);
			}
			
		}
		
	}
	
	/**
	 * all volume set 50%
	 */
	public void resetVolume() {
		
		for (int i = 0; i < select; i++) {
			conteinerMediaPlayer.get(i).setVolume(0.5f);
			conteinerSeekBar.get(i).setProgress(50);
		}
		
	}
	
	/**
	 * set for current player seekBar for control volume
	 * @param int id
	 * @param SeekBar _bar
	 */
	public void setSeekBar(final int id, SeekBar _bar) {
		
		conteinerSeekBar.add(_bar);
		
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
	
	/**
	 * Destroy player and delete from Conteiner seekBar, textView
	 * @param int id
	 */
	protected void Destroy(int id) {
		
		conteinerMediaPlayer.get(id).Destroy();
		conteinerMediaPlayer.remove(id);
		conteinerSeekBar.remove(id);
		conteinerTextView.remove(id);
		select--;
		
	}
	
	/**
	 * if already  set player return false else return true
	 * @param int id
	 * @return boolean
	 */
	public boolean isSet(int id) {
		
		if (conteinerMediaPlayer.size()-1 < id) {
			return false;
		} else {
			return true;
		}
		
	}
	
	/**
	 * if check true start music else stop music
	 * @param boolean check
	 */
	
	public void playingAndStop(boolean check) {
		
		if (!mSetMulti) {
			
			if (check) {
				conteinerMediaPlayer.get(lastPlayer).start();
				conteinerTextView.get(lastPlayer).setTextColor(Color.GREEN);
			} else {
				conteinerMediaPlayer.get(lastPlayer).stop();
				conteinerTextView.get(lastPlayer).setTextColor(Color.BLACK);
			}
		} else {
			if (check) {
				for (int i = 0, length = isPlaying.size(); i < length; i++) {
					conteinerMediaPlayer.get(isPlaying.get(i)).start();
					conteinerTextView.get(isPlaying.get(i)).setTextColor(Color.GREEN);
				}
			} else {
				for (int i = 0, length = isPlaying.size(); i < length; i++) {
					conteinerMediaPlayer.get(isPlaying.get(i)).stop();
					conteinerTextView.get(isPlaying.get(i)).setTextColor(Color.BLACK);
				}
			}
		}
		
	}
	
	/**
	 * if playing reutrn true else return false
	 * @return boolean
	 */
	public boolean isPlaying() {
		
		if (!mSetMulti) {
			return conteinerMediaPlayer.get(lastPlayer).isPlaying();
		} else {
			for (int i = 0; i < select; i++) {
				
				if (conteinerMediaPlayer.get(i).isPlaying()) {
					return true;
				}
				
			}
			return false;
		}
		
	}
	
	/**
	 * get mSetMulti
	 * @return boolean mSetMulti
	 */
	public boolean getMulti () {
		
		return mSetMulti;
		
	}		
}
