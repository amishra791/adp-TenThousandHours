package edu.foothill.tenthousandhours;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ReportsViewAdapter extends ProjectBaseAdapter{


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
		
		// Sort it
		Collections.sort(reports);
		
		int idCount = 0;
		for(String activityName:activityNames){
			long totalTimeActivity = activityUtil.computeTotalTimeForActivity(activityName, mode);
			reports.add(new Report(idCount++,activityName,totalTimeActivity));
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
	
	public long getReportActivityLongTime(int position){
		return reports.get(position).getDuration();
	}
	
	public String getReportActivityTime(int position){
		Report r = reports.get(position);
		String time = r.getTotalTimeFormat();
		return time;
	}
	
	public String getReportActivityPercentage(int position) throws IOException{
		
		if(this.getReportActivityTime(position).equals("N/A")){
			return "N/A";
		}
		
		String activityName = getReportActivityName(position);
		long activityTime = activityUtil.computeTotalTimeForActivity(activityName, mode);
		long totalTime = activityUtil.computeTotalTimeForAllActivities(mode);
		
		int percentage = (int) ((((double)activityTime)/totalTime) * 100);
		return Integer.valueOf(percentage).toString() + "%";
	}
 

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
