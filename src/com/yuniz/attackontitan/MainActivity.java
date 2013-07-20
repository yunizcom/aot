package com.yuniz.attackontitan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.yuniz.attackontitan.R;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	
	public int screenWidth = 0;
	public int screenHeight = 0;
	
	private int previousX = 0;
	
	private RelativeLayout gameMenu;
	private RelativeLayout gameStage1;
	private RelativeLayout gameStage_human;
	
	private ImageView logo;
	private ImageView playBtn;
	private ImageView quitBtn;
	
	private ImageView human;
	
	private ImageView titan1;
	private ImageView titan2;
	private ImageView titan3;
	
	MediaPlayer bgMusic;
	MediaPlayer clickEffect;
	
	private OrientationEventListener myOrientationEventListener;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		int sdk = android.os.Build.VERSION.SDK_INT;
		
		//----------detect device setting and adapt environment
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		
		boolean smallScreen = false;
		try
		{ 
			display.getSize(size); 
			screenWidth = size.x; 
			screenHeight = size.y; 
			smallScreen = false;
		} 
		catch (NoSuchMethodError e) 
		{ 
			screenWidth = display.getWidth(); 
			screenHeight = display.getHeight(); 
			smallScreen = true;
		} 
	
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		//----------detect device setting and adapt environment

	    double setNewHeight = screenHeight;
		double setNewWidth = screenWidth;
		
		gameMenu = (RelativeLayout) findViewById(R.id.gameMenu);
		gameStage1 = (RelativeLayout) findViewById(R.id.gameStage1);
		gameStage_human = (RelativeLayout) findViewById(R.id.gameStage_human);
		
		logo = (ImageView) findViewById(R.id.logo);
		playBtn = (ImageView) findViewById(R.id.playBtn);
		quitBtn = (ImageView) findViewById(R.id.quitBtn);
		
		human = (ImageView) findViewById(R.id.human);
		
		titan1 = (ImageView) findViewById(R.id.titan1);
		titan2 = (ImageView) findViewById(R.id.titan2);
		titan3 = (ImageView) findViewById(R.id.titan3);
		
		bgMusic  = new MediaPlayer();
		clickEffect  = new MediaPlayer();
		
		try 
		{
		    InputStream ims = getAssets().open("menu_bg.jpg");
		    Drawable d = Drawable.createFromStream(ims, null);
		    
		    InputStream ims2 = getAssets().open("stage_1.jpg");
		    Drawable d2 = Drawable.createFromStream(ims2, null);

		    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
		    	gameMenu.setBackgroundDrawable(d);
		    	gameStage1.setBackgroundDrawable(d2);
		    } else {
		    	gameMenu.setBackground(d);
		    	gameStage1.setBackground(d2);
		    }
		    
		    InputStream ims1 = getAssets().open("logo.png");
		    Drawable d1 = Drawable.createFromStream(ims1, null);
		    logo.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("playBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    playBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("quitBtn.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    quitBtn.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("g" + generateNumber(1,4) + "_" + generateNumber(1,2) + ".png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan1.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("g" + generateNumber(1,4) + "_" + generateNumber(1,2) + ".png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan2.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("g" + generateNumber(1,4) + "_" + generateNumber(1,2) + ".png");
		    d1 = Drawable.createFromStream(ims1, null);
		    titan3.setImageDrawable(d1);
		    
		    ims1 = getAssets().open("human_1.png");
		    d1 = Drawable.createFromStream(ims1, null);
		    human.setImageDrawable(d1);
		}
		catch(IOException ex) 
		{
		    return;
		}
		
		//----------auto Adjust UI Elements size----------
		if(smallScreen == true){
			
		}
		
		setNewWidth = screenWidth * 0.7;
		setNewHeight = screenHeight * 0.4;
		logo.setMinimumHeight((int)setNewHeight);
		logo.setMaxHeight((int)setNewHeight);
		logo.setMinimumWidth((int)setNewWidth);
		logo.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.35;
		setNewHeight = screenHeight * 0.2;
		playBtn.setMinimumHeight((int)setNewHeight);
		playBtn.setMaxHeight((int)setNewHeight);
		playBtn.setMinimumWidth((int)setNewWidth);
		playBtn.setMaxWidth((int)setNewWidth);
		
		quitBtn.setMinimumHeight((int)setNewHeight);
		quitBtn.setMaxHeight((int)setNewHeight);
		quitBtn.setMinimumWidth((int)setNewWidth);
		quitBtn.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.5;
		setNewHeight = screenHeight * 0.4;
		human.setMinimumHeight((int)setNewHeight);
		human.setMaxHeight((int)setNewHeight);
		human.setMinimumWidth((int)setNewWidth);
		human.setMaxWidth((int)setNewWidth);
		
		setNewWidth = screenWidth * 0.1;
		setNewHeight = screenHeight * 0.2;
		titan1.setMinimumHeight((int)setNewHeight);
		titan1.setMaxHeight((int)setNewHeight);
		titan1.setMinimumWidth((int)setNewWidth);
		titan1.setMaxWidth((int)setNewWidth);
		
		titan2.setMinimumHeight((int)setNewHeight);
		titan2.setMaxHeight((int)setNewHeight);
		titan2.setMinimumWidth((int)setNewWidth);
		titan2.setMaxWidth((int)setNewWidth);
		
		titan3.setMinimumHeight((int)setNewHeight);
		titan3.setMaxHeight((int)setNewHeight);
		titan3.setMinimumWidth((int)setNewWidth);
		titan3.setMaxWidth((int)setNewWidth);
		
		//logo.setAdjustViewBounds(true);
		playBtn.setAdjustViewBounds(true);
		quitBtn.setAdjustViewBounds(true);
		titan1.setAdjustViewBounds(true);
		titan2.setAdjustViewBounds(true);
		titan3.setAdjustViewBounds(true);
		
		//logo.setScaleType( ImageView.ScaleType.FIT_CENTER);
		playBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		quitBtn.setScaleType( ImageView.ScaleType.FIT_CENTER);
		
		human.setScaleType( ImageView.ScaleType.FIT_CENTER);
		
		titan1.setScaleType( ImageView.ScaleType.FIT_CENTER);
		titan2.setScaleType( ImageView.ScaleType.FIT_CENTER);
		titan3.setScaleType( ImageView.ScaleType.FIT_CENTER);
		//----------auto Adjust UI Elements size----------
		
		myOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL){

		    @Override
		    public void onOrientationChanged(int arg0) {
		     // TODO Auto-generated method stub
		    	moveHuman(arg0);
		    }
		};
		    
	    if (myOrientationEventListener.canDetectOrientation()){
	        myOrientationEventListener.enable();
	    }else{
	    	//finish();
	    }
	    
	    playBGMusic("music_1.mp3");
	}
	
	public int generateNumber(int startFrom, int stopAt){
		Random r = new Random();
		int i1=r.nextInt(stopAt-startFrom) + startFrom;
		return i1;
	}
	
	public void initTitans(ImageView titanSelect, int locations){
		int arg0 = (screenWidth / 100) * locations;
		
		arg0 = arg0 - ( titanSelect.getWidth() / 2 );
		
		Animation animation = new TranslateAnimation(arg0, arg0,0, 0);
		animation.setDuration(0);
		animation.setFillAfter(true);
		titanSelect.startAnimation(animation);
	}
	
	public void moveHuman(int arg0){
		if(arg0 < 240){arg0 = 240;}
		if(arg0 > 300){arg0 = 300;}
		
		arg0-=240;
		arg0 = arg0 * 100 / 60;
		
		String humanGIF = "human_1.png";
		if(arg0 >=50){
			humanGIF = "human_2.png";
		}
		
		try 
		{
		    InputStream ims1 = getAssets().open(humanGIF);
		    Drawable d1 = Drawable.createFromStream(ims1, null);

		    human.setImageDrawable(d1);
		}
		catch(IOException ex) 
		{
		    return;
		}
		
		arg0 = (screenWidth / 100) * arg0;
		
		arg0 = arg0 - ( human.getWidth() / 2 );
		
		previousX = arg0;
		
		Animation animation = new TranslateAnimation(previousX, arg0,0, 0);
		animation.setDuration(500);
		animation.setFillAfter(true);
		human.startAnimation(animation);
	}
	
	public void playBtn(View v) {
		buttonClicks();
		
		gameMenu.setVisibility(View.INVISIBLE);
		gameStage1.setVisibility(View.VISIBLE);
		gameStage_human.setVisibility(View.VISIBLE);
		
		initTitans(titan1,30);
		initTitans(titan2,60);
		initTitans(titan3,90);
		
		playBGMusic("music_1.mp3");
	}
	
	public void quitBtn(View v) {
		finish();
	}
	
	public void buttonClicks(){
		clickEffect.reset();
		clickEffect.release();
		clickEffect = null;
    	
		AssetFileDescriptor descriptor = null;
		try {
			descriptor = getAssets().openFd("button_sound.mp3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long start = descriptor.getStartOffset();
		long end = descriptor.getLength();
		
		clickEffect = new MediaPlayer();
		try {
			clickEffect.setDataSource(descriptor.getFileDescriptor(), start, end);
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
		
		try {
			descriptor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			clickEffect.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickEffect.start();
	}
	
	public void playBGMusic(String filename){
		bgMusic.reset();
		bgMusic.release();
		bgMusic = null;
    	
		AssetFileDescriptor descriptor = null;
		try {
			descriptor = getAssets().openFd(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long start = descriptor.getStartOffset();
		long end = descriptor.getLength();
		
		bgMusic = new MediaPlayer();
		try {
			bgMusic.setDataSource(descriptor.getFileDescriptor(), start, end);
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
		
		try {
			descriptor.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			bgMusic.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bgMusic.setVolume(0.4f,0.4f);
		
		bgMusic.setLooping(true);
		bgMusic.start();
	}
	
	public void openURL(View v) {
		Uri uri = Uri.parse("http://www.yuniz.com");
		 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		 startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
	 // TODO Auto-generated method stub
	 super.onDestroy();
	 
	 bgMusic.stop();
	 bgMusic.release();
	 
	 clickEffect.stop();
	 clickEffect.release();
	 
	 myOrientationEventListener.disable();
	}
	
	@Override
	protected void onPause() {
		super.onPause();

		if(bgMusic.isPlaying()){
			bgMusic.pause();
		}
		
	}
	
	protected void onResume() {
		try {
			bgMusic.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!bgMusic.isPlaying()){
			bgMusic.start();
		}
		
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
