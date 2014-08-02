package edu.foothill.tenthousandhours;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;


public class ActivityDetail {
	private Integer id;
	private long startTime;
	private long endTime;
	
	public ActivityDetail(int id, long start, long end) {
		this.id = (id);
		this.startTime =(start);
		this.endTime = (end);
	}
	
	public ActivityDetail(int id, String fileLine){
		this.id = id;
		StringTokenizer st = new StringTokenizer(fileLine);
		this.startTime = Long.parseLong(st.nextToken());
		this.endTime = Long.parseLong(st.nextToken());
	}

	public Integer getId() {
		return id;
	}

	

	public long getStartTime() {
		return startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getStartDate(){
		SimpleDateFormat startDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Date startTimeD = new Date(startTime);
		
		return startDateFormat.format(startTimeD);
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getStartTimeFormat(){
		SimpleDateFormat startTimeFormat = new SimpleDateFormat("HH:mm:ss");
		Date startTimeD = new Date(startTime);
		return startTimeFormat.format(startTimeD);
	}
	
	public String getDuration(){
		long duration = endTime-startTime;
		if ( duration < 0 ) {
			return "NA";
		}

		return formatInterval(duration);
	}

	private static String formatInterval(final long duration)
    {
        final long hr = TimeUnit.MILLISECONDS.toHours(duration);
        final long min = TimeUnit.MILLISECONDS.toMinutes(duration - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(duration - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(duration - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        //return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
        return String.format("%02d:%02d:%02d", hr, min, sec);
    }

}
