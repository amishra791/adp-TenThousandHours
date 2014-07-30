package edu.foothill.tenthousandhours;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class NewActivityDetail extends Activity {

	private ImageButton startButton;
	private ImageButton pauseButton;
	private TextView timerValue;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	long timeInMilliseconds = 0L;
	long timeSwapBuff = 0L;
	long updatedTime = 0L;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_activity_detail);
		timerValue = (TextView) findViewById(R.id.timerValue);
		startButton = (ImageButton) findViewById(R.id.startButton);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread,0);
				
			}
		});
		pauseButton = (ImageButton) findViewById(R.id.pauseButton);
		pauseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				timeSwapBuff += timeInMilliseconds;
				customHandler.removeCallbacks(updateTimerThread);
				
			}
		});
		
		//add up action
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	
	private Runnable updateTimerThread = new Runnable() {
		
		
		public void run(){
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			int hours = mins / 60;
			mins = mins % 60;
			secs = secs % 60;
			
			timerValue.setText("%02d" + hours + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
			
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
			Intent intent = new Intent(this,ActivitiesList.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		/*	
		case R.id.mi_add:
			Intent intent2 = new Intent(this,ActivitiesList.class);
			NavUtils.navigateUpTo(this, intent2);
			return true;
		*/
	}
		return super.onOptionsItemSelected(item);
	}
	
}
