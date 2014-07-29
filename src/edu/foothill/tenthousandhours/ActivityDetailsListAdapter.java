package edu.foothill.tenthousandhours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ActivityDetailsListAdapter extends ActivityDetailsViewAdapter {

	public ActivityDetailsListAdapter(Context c) {
		super(c);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View rowView;
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.activity_detail_item, parent,false);
		}
		else{
			rowView = convertView;
		}
		
		TextView startDateTextView = (TextView) rowView.findViewById(R.id.activityDetailDate);
		startDateTextView.setText(getStartDate(position));
		TextView startTimeTextView = (TextView) rowView.findViewById(R.id.activityDetailStartTime);
		startTimeTextView.setText(getStartTime(position));
		TextView nameTextView = (TextView) rowView.findViewById(R.id.activityDetailDuration);
		nameTextView.setText(getDuration(position));
		return rowView;
	}

}
