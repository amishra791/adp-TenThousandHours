package edu.foothill.tenthousandhours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReportsListAdapter extends ReportsViewAdapter{

	public ReportsListAdapter(Context c) {
		super(c);
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
		startDateTextView.setText(getName(position));
		TextView startTimeTextView = (TextView) rowView.findViewById(R.id.activityTotalTime);
		startTimeTextView.setText(getTime(position));
		TextView nameTextView = (TextView) rowView.findViewById(R.id.activityPercentage);
		nameTextView.setText(getPercentage(position));
		return rowView;
	}

}
