package edu.foothill.tenthousandhours;

import java.io.IOException;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReportsListAdapter extends ReportsViewAdapter{

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
		
		return rowView;
		
		
	}

}
