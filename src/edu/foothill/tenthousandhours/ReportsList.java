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
import android.view.View;
import android.widget.ListView;

public class ReportsList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports_list);
		ListView listView = (ListView) findViewById(R.id.reportsListView);
		Intent in = getIntent();
		Bundle b = in.getExtras();
		String mode = "daily";
		if(b!=null){
			mode = (String) b.getString("mode");
		}
		
		try {
			listView.setAdapter(new ReportsListAdapter(this, mode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ActionBar ab = getActionBar();
		ab.setTitle("Reports (" + mode+")");
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.report, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case android.R.id.home:
			Intent intent = new Intent(this,ActivitiesList.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		case R.id.reportMenuDaily:
			Intent intentDaily = new Intent(this,ReportsList.class);
			intentDaily.putExtra("mode", "daily");
			startActivity(intentDaily);
			return true;
		case R.id.reportMenuWeekly:
			Intent intentWeekly = new Intent(this,ReportsList.class);
			intentWeekly.putExtra("mode", "weekly");
			startActivity(intentWeekly);
			return true;
		case R.id.reportMenuMonthly:
			Intent intentMonthly = new Intent(this,ReportsList.class);
			intentMonthly.putExtra("mode", "monthly");
			startActivity(intentMonthly);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	public void daily(View view){
		
	}
	public void weekly(View view){
		setContentView(R.layout.activity_reports_list);
		ListView listView = (ListView) findViewById(R.id.reportsListView);
		try {
			listView.setAdapter(new ReportsListAdapter(this, "weekly"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void monthly(View view){
	
	}
	*/
}

