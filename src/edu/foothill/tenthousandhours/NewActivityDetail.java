package edu.foothill.tenthousandhours;


import java.io.IOException;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivityDetail extends ProjectActivity {

	private NewActivityDetail baseActivity = this;
	
	private ImageButton startButton;
	private ImageButton pauseButton;
	private TextView timerValue;
	private long startTime = 0L;
	private long endTime = 0L;
	private Date startDate;
	private Date endDate;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	
	
	private final String BUTTON_NOT_CLICKED = "not started";
	private final String PLAY_BUTTON_CLICKED = "play";
	private final String PAUSE_BUTTON_CLICKED = "pause";
	
	private String buttonMode = BUTTON_NOT_CLICKED;
	
	private String activityName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_activity_detail);
		
		Intent in = getIntent();
		Bundle b = in.getExtras();
		if(b!=null){
			activityName = (String) b.getString("activityName");
		}
		
		ActionBar ab = getActionBar();
		ab.setTitle("Track Activity: " + activityName);
		ab.setSubtitle(R.string.app_name);
		
		timerValue = (TextView) findViewById(R.id.timerValue);
		startButton = (ImageButton) findViewById(R.id.startButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				if(buttonMode.equals(BUTTON_NOT_CLICKED) || buttonMode.equals(PAUSE_BUTTON_CLICKED)){
					buttonMode = PLAY_BUTTON_CLICKED;
					startDate = new Date();
					//startTime = startDate.getTime();
					startTime = SystemClock.elapsedRealtime();
					customHandler.postDelayed(updateTimerThread,0);
				}
				else if(buttonMode.equals(PLAY_BUTTON_CLICKED)){
					//show toast saying play button is already clicked
					Toast.makeText(baseActivity, "Timer has already started!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		pauseButton = (ImageButton) findViewById(R.id.pauseButton);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				if(buttonMode.equals(PLAY_BUTTON_CLICKED)){
					buttonMode = PAUSE_BUTTON_CLICKED;
					endDate = new Date(); 
					//endTime = endDate.getTime();
					endTime = SystemClock.elapsedRealtime();
					timeSwapBuff += timeInMilliseconds;
					customHandler.removeCallbacks(updateTimerThread);
				}
				else if(buttonMode.equals(PAUSE_BUTTON_CLICKED)){
					//show toast saying that pause button is already clicked
					Toast.makeText(baseActivity, "Timer has already stopped!", Toast.LENGTH_SHORT).show();
				}
				else if(buttonMode.equals(BUTTON_NOT_CLICKED)){
					//show toast saying that pause button is already clicked
					Toast.makeText(baseActivity, "Timer has not started yet!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//add up action
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	
	private Runnable updateTimerThread = new Runnable() {
		
		
		public void run(){
			Date d = new Date();
			long curTime;
			//curTime = d.getTime();
			curTime = SystemClock.elapsedRealtime();
			timeInMilliseconds = curTime - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			int hours = mins / 60;
			mins = mins % 60;
			secs = secs % 60;
			
			timerValue.setText(String.format("%02d",hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
			
			customHandler.postDelayed(this, 0);
			
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater mi = getMenuInflater();
	    mi.inflate(R.menu.activity_details, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case android.R.id.home:
			if(buttonMode.equals(this.BUTTON_NOT_CLICKED)){
				Intent intent = new Intent(this,ActivitiesList.class);
				NavUtils.navigateUpTo(this, intent);
				return true;
			}
		
			else if(buttonMode.equals(this.PAUSE_BUTTON_CLICKED)){
				Toast.makeText(baseActivity, "Please log in your time before moving away from page.", Toast.LENGTH_LONG).show();
				return true;
			}
			else if(buttonMode.equals(this.PLAY_BUTTON_CLICKED)){
				//show action bar dialog
				Toast.makeText(baseActivity, "Please log in your time before moving away from page.", Toast.LENGTH_LONG).show();
				return true;
			}
			
		case R.id.mi_add:
			if(buttonMode.equals(this.PAUSE_BUTTON_CLICKED)){
				try {
					util.writeActivityDetailsOntoFile(activityName, startDate.getTime(), endDate.getTime());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Intent intent2 = new Intent(this,ActivitiesList.class);
				NavUtils.navigateUpTo(this, intent2);
				return true;
			}
			else if(buttonMode.equals(this.BUTTON_NOT_CLICKED)){
				//show toast 
				Toast.makeText(baseActivity, "Timer has not started yet!", Toast.LENGTH_SHORT).show();
			}
			else if(buttonMode.equals(this.PLAY_BUTTON_CLICKED)){
				// show toast
				Toast.makeText(baseActivity, "Please stop timer before logging in time.", Toast.LENGTH_SHORT).show();
			}
		}	
		return super.onOptionsItemSelected(item);
	}
	
}
