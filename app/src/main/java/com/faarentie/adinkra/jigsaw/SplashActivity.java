package com.faarentie.adinkra.jigsaw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;









import com.faarentie.adinkra.jigsaw.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {
	
	
	ImageView adinkra_logo;
	Animation anim,anim2;
	LoadObjects lb;
	ArrayList<LoadObjects> loadManager;
	
	ProgressBar webBar;
	ProgressBarThread thd;
	ProgressHandlerBar hanbar;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		//this will display the splash activity in fullscreen without the title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash_screen);
		
		loadManager = new ArrayList<LoadObjects>();
		loadManager.add(new LoadObjects("#FFCC33",R.drawable.loada));
		loadManager.add(new LoadObjects("#33FF00", R.drawable.loadb));
		loadManager.add(new LoadObjects("#FF0000",R.drawable.loadc));
		loadManager.add(new LoadObjects("#9900CC", R.drawable.loadd));
		loadManager.add(new LoadObjects("#000000",R.drawable.loade));
		loadManager.add(new LoadObjects("#0033CC", R.drawable.loadf));
		
		
		LayerDrawable shape = (LayerDrawable) getResources().getDrawable(R.drawable.custom_progress);
		ClipDrawable gd = (ClipDrawable) shape.findDrawableByLayerId(android.R.id.progress);
		
				
		Random rand = new Random();
		int pm = rand.nextInt(loadManager.size());
		lb = loadManager.get(pm);
		
		adinkra_logo = (ImageView) findViewById(R.id.adinkra_logo);
		adinkra_logo.setImageResource(lb.getImageResource());
		gd.setColorFilter(Color.parseColor(lb.getColor()), Mode.MULTIPLY);
		
		anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
		anim2 = AnimationUtils.loadAnimation(getBaseContext(), android.R.anim.fade_out);
		
		adinkra_logo.startAnimation(anim);
		
		anim.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				adinkra_logo.startAnimation(anim2);
				finish();
				//startActivity( new Intent(SplashActivity.this,FisrtScreen.class));
				
				
			}
		});
		
		
		

		webBar = (ProgressBar) findViewById(R.id.progressBar1);
		hanbar = new ProgressHandlerBar();
		thd = new ProgressBarThread();
		
		
		//Context cont = MainGameActivity.this;
		
		//Toast.makeText(cont, cont.getFilesDir().getPath()+"/database/", Toast.LENGTH_LONG).show();
	
		
		//Using an anonymous inner thread to start a concurrent timer for my splash screen
		Thread logoTimer = new Thread(){
			
			public void run() {
				
				 try{
	                    int logoTimer = 0;
	                    while(logoTimer < 7000){
	                   
	                    	sleep(100);
	                        logoTimer = logoTimer +100;
	                    };
	                    //startActivity(new Intent(MainGameActivity.this, WelcomeChoiceActivity.class));
	                   // startActivity(new Intent(MainGameActivity.this, AltMainGameActivity.class));
	                    startActivity( new Intent(SplashActivity.this,FisrtScreen.class));
	                    
	                    
	                   
	                    
	                }
	                 
	                catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                 
	                finally{
	                   finish();
	                }
				
			}
				
			};
			
			
			thd.start();
			logoTimer.start();
			
		
		
		
	}
	
	
	
	
	
	

	class ProgressHandlerBar extends Handler{
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			
			Bundle bn = msg.getData();
			int i = bn.getInt("level");
			webBar.setProgress(i);
			
			super.handleMessage(msg);
			
			
			
		}
		
	}
	

	class ProgressBarThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int counter = 0;counter <= 100;counter++){
				
				
				Message msg = new Message();	//Message to be sent to the handler
				Bundle bund = new Bundle();		//Bundle to contain our message
				bund.putInt("level", counter);
				msg.setData(bund);				//Message object collects the bundle
				hanbar.sendMessage(msg);
				try {
					sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			super.run();
		}
		
	}
	
	
	

}
