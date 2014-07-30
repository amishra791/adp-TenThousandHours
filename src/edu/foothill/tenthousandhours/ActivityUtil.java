package edu.foothill.tenthousandhours;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.os.Environment;
import android.util.Log;

public class ActivityUtil {
	
	final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();
	final String APP_PATH = SDCARD_PATH + "/TenThousandHours";
	
	ArrayList<String> activities = new ArrayList<String>();
	PrintWriter writer = null;
	Scanner reader = null;
	
	/*
	public ActivityUtil() {
		// TODO Auto-generated constructor stub
	}
	*/
	
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
				fileList.add(file.getPath());
			}
		}
		return fileList;
	}
	 public int createActivity(String name){
		 List<String> directories = getListOfActivitesDir();
		 String newDir = APP_PATH + "/" + name;
		 if(directories.contains(newDir)){
			 return 0;
		 }
		 File file = new File(newDir);
		 
		 if(!file.mkdirs()){
			 Log.d("DEBUG", "Directory not created - already exists?");
			 return -1;
		 }
		 return 1;
	 }
	
	
	
	

}
