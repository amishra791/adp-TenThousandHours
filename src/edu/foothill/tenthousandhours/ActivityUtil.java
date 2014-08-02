package edu.foothill.tenthousandhours;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import android.os.Environment;
import android.util.Log;

public class ActivityUtil {
	
	//final boolean useMockData = true;
	final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();
	final String APP_PATH = SDCARD_PATH + "/TenThousandHours";
	private static ArrayList<Activity> activities;
	
	private final long DAY_MILLIS = 1000 * 60 * 60 * 24;
	private final long WEEK_MILLIS = DAY_MILLIS * 7;
	private final long MONTH_MILLIS = DAY_MILLIS * 30;
	
		
	public ActivityUtil() {
		//this(Environment.getExternalStorageDirectory().getPath());
		File f = new File(APP_PATH);
		if(!(f.exists())||(f.isDirectory())){
			boolean flag = f.mkdirs();
			if(!flag){
				Log.d("DEBUG", "EPIC FAIL");
			}
		}
		activities = new ArrayList<Activity>();
	}
	
	public void createActivity(String name){
		File f = new File(APP_PATH + "/" + name);
		if(f.exists()&&f.isDirectory()){
			System.out.println("Activity already exists");
		}
		else{
			boolean flag = f.mkdirs();
		}
		activities.add(new Activity(activities.size()+1,name));
	}
	
	public String getFilePathForActivityDetail(String activityName) {
		String filePath = APP_PATH + "/" + activityName + "/" + activityName + ".txt";
		
		return filePath;
	}

	/**
	 * From a file path, sdCard/TenThousandHours/Math, returns Math
	 * @param filePath
	 * @return
	 */
	public String getActivityNamefromFilePath(String filePath){
		int start = filePath.lastIndexOf('/');
		return filePath.substring(start + 1);
	}
	
	public void createActivityDetail(String activityName, long start, long end) throws IOException{
		String filePath = getFilePathForActivityDetail(activityName); 
		File f = new File (filePath);
		if(!f.exists()){
			f.createNewFile();
		}
		writeActivityDetailsOntoFile(filePath, start, end);
	}
	
	public ArrayList<String> readActivityDetailsFile(String activityName) throws IOException{
		String filePath = getFilePathForActivityDetail(activityName);
		ArrayList<String> activityDetails = new ArrayList<String>();
		File file = new File(filePath);
		if (file.exists()) {
			BufferedReader f = new BufferedReader(new FileReader(file));
			if (f != null) {
				String s = f.readLine();
				while(s != null){
					activityDetails.add(s);
					s = f.readLine();
				}
				f.close();
			}
		}
		return activityDetails;
	}
	
	public void writeActivityDetailsOntoFile(String activityName, long start, long end) throws IOException{
		String filePath = getFilePathForActivityDetail(activityName);
		ArrayList<String> activityDetails = readActivityDetailsFile(filePath);
		activityDetails.add(start + " " + end);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
		
		for(String s:activityDetails){
			out.println(s);
		}
		out.close();
	}
	
	
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)){
			return true;
		}
		return false;
	}
	
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	
	public List<String> getListOfActivitesDir(){
		File dir = new File(this.APP_PATH);
		File[] files = dir.listFiles();
		List<String> fileList = new ArrayList<String>();
		if (files != null){
			for(File file: files){
				String activityName = this.getActivityNamefromFilePath(file.getPath());
				fileList.add(activityName);
			}
		} 
		return fileList; 
	}
	
	public String getActivityNamefromPosition(int position){
		return activities.get(position).getName();
	}
	
	public ArrayList<Activity> reload(){
		List<String> activitiesInDir = this.getListOfActivitesDir();
		
		activities.clear();
		int idCount = 0;
		for(String s: activitiesInDir){
			activities.add(new Activity(idCount++, s));
		}
		
		return activities;
	}
	
	public long computeTotalTimeForAllActivities(String mode) throws IOException{
		File dir = new File(this.APP_PATH);
		File[] files = dir.listFiles();
		Date curDate = new Date();
		long curDateMillis = curDate.getTime();
		long minDateMillis =  -1;
		if(mode.equals("daily")){
			minDateMillis = curDateMillis - this.DAY_MILLIS;
		}
		else if (mode.equals("weekly")){
			minDateMillis = curDateMillis - this.WEEK_MILLIS;
		}
		else if(mode.equals("monthly")){
			minDateMillis = curDateMillis - this.MONTH_MILLIS;
		}
		long totalTime = -1;
		StringTokenizer st;
		if(files != null){
			totalTime = 0;
			for(File file:files){
				String activityName = this.getActivityNamefromFilePath(file.getPath());
				List<String> activityDetailLines = this.readActivityDetailsFile(activityName);
				for(String line:activityDetailLines){
					if ((line == null) || (line !="")){
						break;
					}
					st = new StringTokenizer(line);
					long begTime = Long.parseLong(st.nextToken());
					if(begTime < minDateMillis){
						continue;
					}
					long endTime = Long.parseLong(st.nextToken());
					totalTime = totalTime + (endTime - begTime);
				}
			}
		}
			
		return totalTime;
	}
	
	public long computeTotalTimeForActivity(String activityName, String mode) throws IOException{
		File activityDetailFile = new File(this.getFilePathForActivityDetail(activityName));
		Date curDate = new Date();
		long curDateMillis = curDate.getTime();
		long minDateMillis = -1;
		if(mode.equals("daily")){
			minDateMillis = curDateMillis - this.DAY_MILLIS;
		}
		else if (mode.equals("weekly")){
			minDateMillis = curDateMillis - this.WEEK_MILLIS;
		}
		else if(mode.equals("monthly")){
			minDateMillis = curDateMillis - this.MONTH_MILLIS;
		}
		long totalTime = -1;
		StringTokenizer st;
		if (activityDetailFile != null){
			totalTime = 0;
			List<String> activityDetailLines = this.readActivityDetailsFile(activityName);
			for(String line: activityDetailLines){
				if((line == null) || (line !="")){
					return totalTime;
				}
				st = new StringTokenizer(line);
				long begTime = Long.parseLong(st.nextToken());
				if(begTime < minDateMillis){
					continue;
				}
				long endTime = Long.parseLong(st.nextToken());
				totalTime = totalTime + (endTime - begTime);
			}
		}
		return totalTime;
	}
	
	
	
	
	

}
