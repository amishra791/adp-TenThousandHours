package edu.foothill.tenthousandhours;

import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class GraphActivity extends Activity {

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
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		LinearLayout layout = (LinearLayout) findViewById(R.id.pieChart);
		//construct the pie graph
		CategorySeries series = new CategorySeries("Pie Graph");
		
		for (int i=0;i<activityTimeData.length;i++){
			series.add(activityNameData[i],activityTimeData[i]);
		}
		/*
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA,
									Color.YELLOW, Color.CYAN};
		
		
		DefaultRenderer renderer = new DefaultRenderer();
		for (int i = 0, colorCount = 0; i < series.getItemCount(); i++,colorCount++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
			if(colorCount==4){
				colorCount = -1;
			}
		}
		*/
		
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
		int[] colorArray = new int[numColors];
		Random randomNumGenerator = new Random();
		int fred = randomNumGenerator.nextInt(255);
		int fgreen = randomNumGenerator.nextInt(255);
		int fblue = randomNumGenerator.nextInt(255);
		int first = Color.rgb(randomNumGenerator.nextInt(255), randomNumGenerator.nextInt(255), randomNumGenerator.nextInt(255));
		Color f = new Color();
		colorArray[0] = first;
		int colorCount = 1;
		while(colorCount<numColors){
			double diff = 0;
			int sred = 0;
			int sgreen = 0;
			int sblue = 0;
			do{
				sred = randomNumGenerator.nextInt(255);
				sgreen = randomNumGenerator.nextInt(255);
				sblue = randomNumGenerator.nextInt(255);
				long d = Math.abs(sred-fred) * Math.abs(sred-fred) + 
						Math.abs(sgreen-fgreen) * Math.abs(sgreen-fgreen) +
						Math.abs(sblue-fblue) * Math.abs(sblue-fblue);
				diff = Math.sqrt(d);
			}while(diff > 360);
			colorArray[colorCount] = Color.rgb(sred, sgreen, sblue);
			colorCount++;
		}
		return colorArray;
	}
}
