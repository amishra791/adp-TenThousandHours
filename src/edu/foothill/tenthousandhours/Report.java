package edu.foothill.tenthousandhours;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;

public class Report {
	private Integer id;
	private String activityName;
	private long totalTime;
	
	public Report(int id, String activityName, long totalTime) {
		this.id = id;
		this.activityName = activityName;
		this.totalTime = totalTime;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return activityName;
	}
	
	public long getDuration(){
		return totalTime;
	}
	
	
	@SuppressLint("SimpleDateFormat")
	public String getTotalTimeFormat(){
		SimpleDateFormat startTimeFormat = new SimpleDateFormat("HH:mm:ss");
		Date startTimeD = new Date(totalTime);
		return startTimeFormat.format(startTimeD);
	}
	
	private static String formatInterval(final long duration){
        final long hr = TimeUnit.MILLISECONDS.toHours(duration);
        final long min = TimeUnit.MILLISECONDS.toMinutes(duration - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(duration - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(duration - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        //return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }
}
