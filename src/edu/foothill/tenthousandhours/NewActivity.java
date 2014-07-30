package edu.foothill.tenthousandhours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		
		//add up action
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.activity_details, menu); //add button only
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case android.R.id.home:
			Intent intent = new Intent(this,ActivitiesList.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		case R.id.mi_add:
			Intent intent2 = new Intent(this,ActivitiesList.class);
			NavUtils.navigateUpTo(this, intent2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
