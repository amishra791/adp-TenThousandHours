package edu.foothill.tenthousandhours;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ActivitiesViewAdapter extends BaseAdapter {
	
	/*
	class Activity{
		public Integer id;
		public String name;
		
		Activity(int id, String name){
			this.id = id;
			this.name = name;
		}
	}
	*/
	
	protected static ArrayList<Activity> activities;
	private static boolean isInitialized;
	protected Context context;
	
	public ActivitiesViewAdapter(Context c) {
		context = c;
		
		if(!isInitialized) {
			activities = new ArrayList<Activity>();
			
			activities.add(new Activity(1,"Math"));
			activities.add(new Activity(2,"Clarinet"));
			activities.add(new Activity(3,"Football"));
			activities.add(new Activity(4,"Volunteering"));
		}
	}

	@Override
	public int getCount() {
		return activities.size();
	}

	@Override
	public Object getItem(int position) {
		return activities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return activities.get(position).getId();
	}

	
	// other methods to access inner class
	
	public String getActivityName(int position) {
		return activities.get(position).getName();
	}
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
