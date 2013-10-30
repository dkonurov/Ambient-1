package com.opensoftdev.ambient;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.widget.SeekBar;

public class CustomMediaPlayer implements OnPreparedListener {
	
	private Context context;
	static final private Integer NULL = 0;
	private SeekBar bar;
	public MediaPlayer mediaPlayer;
	public int number;
	public int volume = -1;
	
	CustomMediaPlayer(Context _сontext, int _id, int _number) {
		context = _сontext;
		number = _number;
		mediaPlayer = new MediaPlayer();
		
		AssetFileDescriptor afd = context.getResources().openRawResourceFd(_id);
		try {
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.prepareAsync();
		
		mediaPlayer.setVolume(0.5f, 0.5f);
		mediaPlayer.setLooping(true);
		
		
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		mediaPlayer.start();
	}
	
	public void stop() {
		mediaPlayer.pause();
		mediaPlayer.seekTo(NULL);
	}
	
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int _number) {
		number = _number;
	}
	
	public void setVolume(float _volume) {
		volume = (int) (_volume*100);
		mediaPlayer.setVolume(_volume, _volume);
	}
	
	public void Destroy() {
		if (mediaPlayer != null) {
			try {
				mediaPlayer.release();
				mediaPlayer = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getVolume() {
		if (volume != -1) {
			return volume;
		} else {
			return 50;
		}
	}

}
