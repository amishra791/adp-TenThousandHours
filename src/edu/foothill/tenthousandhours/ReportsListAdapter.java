package edu.foothill.tenthousandhours;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReportsListAdapter extends ReportsViewAdapter{

	private ArrayList<Long> activityTimes = new ArrayList<Long>(); 
	private ArrayList<String> activityNames = new ArrayList<String>();
	public ReportsListAdapter(Context c, String mode) throws IOException {
		super(c, mode);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View rowView;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.reports_item, parent,false);
		}
		else{
			rowView = convertView;
		}
		
		TextView startDateTextView = (TextView) rowView.findViewById(R.id.activityName);
		startDateTextView.setText(getReportActivityName(position));
		TextView startTimeTextView = (TextView) rowView.findViewById(R.id.activityTotalTime);
		startTimeTextView.setText(getReportActivityTime(position));
		TextView nameTextView = (TextView) rowView.findViewById(R.id.activityPercentage);
		try {
			nameTextView.setText(getReportActivityPercentage(position));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		activityNames.add(getReportActivityName(position));
		activityTimes.add(getReportActivityLongTime(position));
		
		return rowView;
		
		
	}
	
	public long[] getActivityTimeData(){
		long[] array = new long[activityTimes.size()];
		for(int i=0;i<array.length;i++){
			array[i] = activityTimes.get(i);
		}
		return array;
	}
	
	public String[] getActivityNameData(){
		String[] array = new String[activityNames.size()];
		for(int i=0;i<array.length;i++){
			array[i] = activityNames.get(i);
		}
		return array;
	}

}
