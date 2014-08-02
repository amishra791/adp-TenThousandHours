package edu.foothill.tenthousandhours;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ActivityDetailsViewAdapter extends ProjectBaseAdapter{
	
	/*
	class ActivityDetail{
		
		public static final long SECONDS = 1000;
		public static final long MINUTES = SECONDS * 60;
		public static final long HOURS = MINUTES * 60;
		public static final long DAYS = HOURS * 24;
		
		
		public Integer id;
		public long startTime;
		public long endTime;
		
		
		
		ActivityDetail(int id,long startTime, long endTime){
			this.id = id;
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		public Date getStartDate(){
			return new Date(startTime);
		}
		
		public String computeDuration(){
			long duration = endTime - startTime;
			int numHours = (int)(duration / HOURS);
			duration = duration - numHours * HOURS;
			int numMinutes = (int)(duration / MINUTES);
			duration = duration - numHours * MINUTES;
			int numSeconds = (int)(duration / SECONDS);
			
			return numHours + ":" + numMinutes + ":" + numSeconds;
		}
	}
	*/
	protected static ArrayList<ActivityDetail> activityDetails;
	//private static boolean isInitialized;
	protected Context context;
	
	
	public ActivityDetailsViewAdapter(Context c, String activityName) throws IOException {
		context = c;
		ArrayList<String> stringActivityDetails = activityUtil.readActivityDetailsFile(activityName);
		
		
		activityDetails = new ArrayList<ActivityDetail>();
		StringTokenizer st;
		int idCount = 0;
		for(String s: stringActivityDetails){
			st = new StringTokenizer(s);
			activityDetails.add(new ActivityDetail(idCount++,Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken())));
		}
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return activityDetails.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return activityDetails.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return activityDetails.get(position).getId();
	}

	
	//other methods to access ActivityDetail class
	
	public String getStartDate(int position){
		return activityDetails.get(position).getStartDate();
		
	}

	public String getStartTime(int position){
		return activityDetails.get(position).getStartTimeFormat();
	}
	
	public String getDuration(int position){
		return activityDetails.get(position).getDuration();
	}
	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
