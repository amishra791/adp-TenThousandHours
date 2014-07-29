package edu.foothill.tenthousandhours;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ReportsViewAdapter extends BaseAdapter{

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
	
	protected static ArrayList<Report> reports;
	private static boolean isInitialized;
	protected Context context;
	
	
	public ReportsViewAdapter(Context c) {
		context = c;
		
		if(!isInitialized){
			reports = new ArrayList<Report>();
			
			reports.add(new Report(1,"foo",1,0,0));
			
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
		return reports.get(position).id;
	}
 
	//inner class methods
	public long computeTotalTime(){
		long totalTime = 0;
		for (Report r : reports){
			totalTime = totalTime + 3600 * r.numHours + 60 * r.numMins + r.numSeconds;
		}
		return totalTime;
	}
	
	public long computeReportTimeInSeconds(int position){
		long time = reports.get(position).numHours * 3600 +
				reports.get(position).numMins * 60 +
				reports.get(position).numSeconds;
		return time;
	}
	public String getName(int position){
		return reports.get(position).name;
	}
	
	public String getTime(int position){
		return reports.get(position).numHours + ":" 
				+ reports.get(position).numMins + ":"
				+ reports.get(position).numSeconds;
	}
	public String getPercentage(int position){
		long totalTime = computeTotalTime();
		long totalReportTime = computeReportTimeInSeconds(position);
		
		int percentage = ((int)(totalTime/totalReportTime)) * 100;
		return new Integer(percentage).toString();
	}
	

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
