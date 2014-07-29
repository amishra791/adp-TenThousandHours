package edu.foothill.tenthousandhours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.mi_add:
			Intent intent1 = new Intent(this,NewActivityDetail.class);
			startActivity(intent1);
			return true;
		
		case R.id.mi_report:
			Intent intent2 = new Intent(this,ReportsList.class);
			startActivity(intent2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
