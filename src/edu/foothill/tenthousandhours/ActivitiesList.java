package edu.foothill.tenthousandhours;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ActivitiesList extends ProjectActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activities_list);
		
		getActionBar().setSubtitle(R.string.app_name);
		
		
		ListView listView = (ListView) findViewById(R.id.activitiesListView);
		listView.setAdapter(new ActivitiesListAdapter(this));
		
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() { 
	    	 @Override
	    	 public void onItemClick(AdapterView<?> parent, View v, int position, long id) { 
		    	 // Tell ActivityDetailsListActivity which activity has been tapped 
		    	 Intent intent = new Intent(getBaseContext(), ActivityDetailsList.class); 
		    	 util.reload();
		    	 String name = util.getActivityNamefromPosition(position);
		    	 intent.putExtra("activityName",name);
		    	 
		    	 startActivity(intent); 
		    	 }
	    	 });
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.activity, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.mi_add:
			Intent intent1 = new Intent(this,NewActivity.class);
			startActivity(intent1);
			return true;
			
		case R.id.mi_report:
			Intent intent2 = new Intent(this,ReportsList.class);
			startActivity(intent2);
			return true;	
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
	
}
