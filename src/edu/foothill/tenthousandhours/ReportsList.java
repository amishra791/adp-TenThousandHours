package edu.foothill.tenthousandhours;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class ReportsList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reports_list);
		ListView listView = (ListView) findViewById(R.id.reportsListView);
		listView.setAdapter(new ReportsListAdapter(this));
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.report, menu);
		return true;
	}
}
