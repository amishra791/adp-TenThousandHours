package edu.foothill.tenthousandhours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	/*
	Context c;
	//Activity thisActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		c = this.getBaseContext();
		//thisActivity = this;
		
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(c,ActivitiesList.class);
                startActivity(i);
            }
        });
	}
	*/
	private Animation animFadein;	// Animation    
	private long SPLASH_TIME_OUT = 3000;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		
		// Animate the text
		boolean animateText = true;
		if (animateText) {
			final TextView textView = (TextView) findViewById(R.id.textView1);
			
			// load the animation
        	animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        	
        	// start the animation
			textView.startAnimation(animFadein);
		}
		
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //Intent i = new Intent(SplashScreen.this, MainActivity.class);
                Intent i = new Intent(MainActivity.this,ActivitiesList.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
