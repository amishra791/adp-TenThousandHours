package edu.foothill.tenthousandhours;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class ActivityDetailsList extends ProjectActivity {

	String activityName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_details_list);
		
		
		
		activityName = "";
		Intent in = getIntent();
		Bundle b = in.getExtras();
		if(b != null){
			activityName = b.getString("activityName");
		}
		//activityName = Integer.valueOf(pos).toString();
		ActionBar ab = getActionBar();
		ab.setTitle("Activity Details: " + activityName);
		ab.setSubtitle(R.string.app_name);
		
		ListView listView = (ListView) findViewById(R.id.activityDetialsListView);
		try {
			listView.setAdapter(new ActivityDetailsListAdapter(this,activityName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			intent1.putExtra("activityName",activityName);
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
