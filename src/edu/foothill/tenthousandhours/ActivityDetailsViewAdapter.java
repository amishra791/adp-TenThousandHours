package edu.foothill.tenthousandhours;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ActivityDetailsViewAdapter extends BaseAdapter{
	
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
	
	protected static ArrayList<ActivityDetail> activityDetails;
	private static boolean isInitialized;
	protected Context context;
	
	
	public ActivityDetailsViewAdapter(Context c) {
		context = c;
		
		if(!isInitialized){
			activityDetails = new ArrayList<ActivityDetail>();
			
			activityDetails.add(new ActivityDetail(1,System.currentTimeMillis(),System.currentTimeMillis()+1000));
			activityDetails.add(new ActivityDetail(2,System.currentTimeMillis(),System.currentTimeMillis()+1000));
			activityDetails.add(new ActivityDetail(3,System.currentTimeMillis(),System.currentTimeMillis()+1000));
			
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
		return activityDetails.get(position).id;
	}

	//other methods to access inner class
	@SuppressWarnings("deprecation")
	public String getStartDate(int position){
		String s = "";
		Date date = activityDetails.get(position).getStartDate();
		int year = date.getYear();
		int month = date.getMonth();
		int day = date.getDay();
		s += year + "/" + month + "/" + day;
		return s;
		
	}
	@SuppressWarnings("deprecation")
	public String getStartTime(int position){
		String s = "";
		Date date = activityDetails.get(position).getStartDate();
		int hour = date.getHours();
		int minute = date.getMinutes();
		s += hour + ":" + minute;
		return s;
	}
	
	public String getDuration(int position){
		return activityDetails.get(position).computeDuration();
	}
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
