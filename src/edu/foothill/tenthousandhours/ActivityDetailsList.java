package edu.foothill.tenthousandhours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class ActivityDetailsList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_details_list);
		
		ListView listView = (ListView) findViewById(R.id.activityDetialsListView);
		listView.setAdapter(new ActivityDetailsListAdapter(this));
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.activity_details, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.mi_add:
			Intent intent1 = new Intent(this,NewActivityDetail.class);
			startActivity(intent1);
			return true;
		
		case android.R.id.home:
			Intent intent3 = new Intent(this,ActivitiesList.class);
			NavUtils.navigateUpTo(this, intent3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
