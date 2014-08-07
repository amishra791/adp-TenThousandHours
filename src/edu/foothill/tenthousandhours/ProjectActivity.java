package edu.foothill.tenthousandhours;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class ProjectActivity extends android.app.Activity {

	protected static ActivityUtil util = new ActivityUtil();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(44, 135, 135)));
	}

}
