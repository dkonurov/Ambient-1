package com.opensoftdev.ambient;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class CustomMediaPlayer implements OnPreparedListener {
	
	private View button;
	private Context context;
	static final private Integer NULL = 0;
	private SeekBar bar;
	//final private Integer id;
	public MediaPlayer mediaPlayer;
	
	CustomMediaPlayer(Context _context, int Id) {
		context = _context;
		mediaPlayer = new MediaPlayer();
		AssetFileDescriptor afd = context.getResources().openRawResourceFd(Id);
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
		//resetVolume();
		/*Bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				float volume = (float) arg1/100;
				mediaPlayer.setVolume(volume, volume);
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
		*/
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.prepareAsync();
		
		mediaPlayer.setVolume(0.5f, 0.5f);
		mediaPlayer.setLooping(true);
		
		
	}
	
	public void setView(final TextView v){
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mediaPlayer.isPlaying()) {
					mediaPlayer.pause();
					mediaPlayer.seekTo(NULL);
					v.setTextColor(Color.BLACK);
				} else {
					mediaPlayer.start();
					v.setTextColor(Color.GREEN);
				}
			}
			
		});
	}
	
	public void setSeekBar(SeekBar bar) {
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				float volume = (float) arg1/100;
				mediaPlayer.setVolume(volume, volume);
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
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}
	
	public void resetVolume() {
		mediaPlayer.setVolume(0.5f, 0.5f);
		bar.setProgress(50);
	}

}
