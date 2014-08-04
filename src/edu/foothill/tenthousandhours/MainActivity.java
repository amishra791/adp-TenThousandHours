package edu.foothill.tenthousandhours;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Property;
import android.view.animation.DecelerateInterpolator;
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
	
	private long SPLASH_TIME_OUT = 3000;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		
		// Animate the text
		boolean animateText = false;
		if (animateText) {
//			Integer colorFrom = getResources().getColor(Color.BLACK);
//			Integer colorTo = getResources().getColor(R.color.app_theme_color);
			
			/* 1st
			final TextView textView = (TextView) findViewById(R.id.textView1);
			textView.setTextColor(Color.WHITE);
			*/
	
			/* 2nd
			final ObjectAnimator animator = ObjectAnimator.ofInt(textView, "textColor", colorTo);
			*/

			/*
			final Property<TextView, Integer> property = new Property<TextView, Integer>(int.class, "textColor") {
			   @Override
			    public Integer get(TextView object) {
			        return object.getCurrentTextColor();
			    }

			   @Override
			    public void set(TextView object, Integer value) {
			        object.setTextColor(value);
			    }
			};

			final ObjectAnimator animator = ObjectAnimator.ofInt(textView, property, colorTo);
			*/
			
			/*
			// 
			animator.setDuration(1000L);
			animator.setEvaluator(new ArgbEvaluator());
			animator.setInterpolator(new DecelerateInterpolator(2));
			animator.start();
			*/
			
			/* 3rd
			ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), Color.WHITE, R.color.app_theme_color);
			colorAnimation.addUpdateListener(new AnimatorUpdateListener() {

			    @Override
			    public void onAnimationUpdate(ValueAnimator animator) {
			        textView.setTextColor((Integer)animator.getAnimatedValue());
			    }

			});
			colorAnimation.start();
			*/
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
