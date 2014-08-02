package edu.foothill.tenthousandhours;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.os.Environment;
import android.util.Log;

public class ActivityUtil {
	
	//final boolean useMockData = true;
	final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();
	final String APP_PATH = SDCARD_PATH + "/TenThousandHours";
	private static ArrayList<Activity> activities;
	
		
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
	
	
	
	
	

}
