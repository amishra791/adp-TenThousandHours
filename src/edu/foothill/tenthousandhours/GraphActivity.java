package edu.foothill.tenthousandhours;

import java.util.ArrayList;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class GraphActivity extends ProjectActivity {

	private GraphicalView pieChartView;
	private String mode = "";
	private String[] activityNameData;
	private long[] activityTimeData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graph);
		
		
		Intent in = getIntent();
		Bundle b = in.getExtras();
		
		if(b!=null){
			mode = (String) b.getString("mode");
			activityNameData = (String[]) b.getStringArray("activityNameData");
			activityTimeData = (long[]) b.getLongArray("activityTimeData");
		}
		
		ActionBar ab = getActionBar();
		ab.setTitle("Graphs (" + mode+")");
		ab.setSubtitle(R.string.app_name);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.pieChart);
		//construct the pie graph
		CategorySeries series = new CategorySeries("Pie Graph");
		
		for (int i=0;i<activityTimeData.length;i++){
			series.add(activityNameData[i],activityTimeData[i]);
		}
		
		int[] colors = generateColorArray(activityTimeData.length);
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color: colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		
		pieChartView = ChartFactory.getPieChartView(this, series, renderer);
		
		
		layout.addView(pieChartView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case android.R.id.home:
			Intent intent = new Intent(this,ReportsList.class);
			intent.putExtra("mode", mode);
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int[] generateColorArray(int numColors){
		ArrayList<Integer> list = new ArrayList<Integer>(numColors);
		for(int i=0;i<numColors;i++){
			list.add(0);
		}
		int[] colorArray = new int[numColors];
		Random randomNumGenerator = new Random();
		int fred = randomNumGenerator.nextInt(255);
		int fgreen = randomNumGenerator.nextInt(255);
		int fblue = randomNumGenerator.nextInt(255);
		int first = Color.rgb(fred, fgreen, fblue);
		list.set(0, first);
		//colorArray[0] = first;
		int colorCount = 1;
		while(colorCount<numColors){
			int sred = 0;
			int sgreen = 0;
			int sblue = 0;
			do{
				sred = randomNumGenerator.nextInt(255);
				sgreen = randomNumGenerator.nextInt(255);
				sblue = randomNumGenerator.nextInt(255);
			}while(!isValidColor(sred,sgreen,sblue,list));
			list.set(colorCount, Color.rgb(sred, sgreen, sblue));
			colorCount++;
		}
		for(int i=0;i<list.size();i++){
			colorArray[i] = list.get(i);
		}
		return colorArray;
	}
	
	private boolean isValidColor(int fred, int fblue, int fgreen, ArrayList<Integer> list){
		boolean isValid = true;
		for(int color:list){
			int sred = Color.red(color);
			int sgreen = Color.green(color);
			int sblue = Color.blue(color);
			long d = Math.abs(sred-fred) * Math.abs(sred-fred) + 
					Math.abs(sgreen-fgreen) * Math.abs(sgreen-fgreen) +
					Math.abs(sblue-fblue) * Math.abs(sblue-fblue);
			double diff = Math.sqrt(d);
			if(diff < 50){
				isValid = false;
				return isValid;
			}
		}
		return isValid;
	}
}
