package edu.foothill.tenthousandhours;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ReportsViewAdapter extends ProjectBaseAdapter{

	/*
	class Report{
		public int id;
		public String name;
		public int numHours;
		public int numMins;
		public int numSeconds;
		
		
		Report(int id,String name, int numHours, int numMins, int numSeconds){
			this.id = id;
			this.name = name;
			this.numHours = numHours;
			this.numMins = numMins;
			this.numSeconds = numSeconds;
		}
		
		
	}
	*/
	protected static ArrayList<Report> reports;
	
	protected Context context;
	String mode = "";
	
	
	public ReportsViewAdapter(Context c, String mode) throws IOException {
		context = c;
		reports = new ArrayList<Report>();
		this.mode = mode;
		List<String> activityFilePath = activityUtil.getListOfActivitesDir();
		List<String> activityNames = new ArrayList<String>();
		for(String filePath: activityFilePath){
			activityNames.add(activityUtil.getActivityNamefromFilePath(filePath));
		}
		
		int idCount = 0;
		for(String activityName:activityNames){
			reports.add(new Report(idCount++,activityName,activityUtil.computeTotalTimeForActivity(activityName, mode)));
		}
		
	}


	@Override
	public int getCount() {
		return reports.size();
	}


	@Override
	public Object getItem(int position) {
		return reports.get(position);
	}


	@Override
	public long getItemId(int position) {
		return reports.get(position).getId();
	}
	
	public String getReportActivityName(int position){
		return reports.get(position).getName();
	}
	
	public String getReportActivityTime(int position){
		return reports.get(position).getTotalTimeFormat();
	}
	
	public String getReportActivityPercentage(int position) throws IOException{
		String activityName = getReportActivityName(position);
		long activityTime = activityUtil.computeTotalTimeForActivity(activityName, mode);
		long totalTime = activityUtil.computeTotalTimeForAllActivities(mode);
		
		int percentage = (int) ((((double)activityTime)/totalTime) * 100);
		return Integer.valueOf(percentage).toString();
	}
 

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
