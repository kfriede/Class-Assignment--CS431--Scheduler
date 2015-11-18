package com.kfriede.Scheduler.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kfriede.Scheduler.Algorithms.FirstComeFirstServe;
import com.kfriede.Scheduler.Algorithms.RoundRobin;
import com.kfriede.Scheduler.Algorithms.SchedulingAlgorithm;
import com.kfriede.Scheduler.Algorithms.ShortestJobFirst;
import com.kfriede.Scheduler.Util.GanttChart;
import com.kfriede.Scheduler.Util.GanttChart.ExtraData;
import com.kfriede.Scheduler.Util.Job;
import com.kfriede.Scheduler.Util.JobFileHandler;
import com.kfriede.Scheduler.Util.SegmentTuple;
import com.kfriede.Scheduler.Util.Util;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindow {
	
	private static final String GANTTCHART_CSS_FILEPATH = "/stylesheets/ganttchart.css";

	private static String jobFile = "src/main/resources/testdata1.txt";
	
	@FXML
	private AnchorPane chartAnchor;
	
	@FXML
	private Button selectFileButton;
	
	@FXML
	private ListView<String> averagesListView;
	
	private FirstComeFirstServe fcfs;
	private ShortestJobFirst sjf;
	private RoundRobin rr3;
	private RoundRobin rr4;
	
	private float fcfsAvg;
	private float sjfAvg;
	private float rr3Avg;
	private float rr4Avg;

	@FXML
	private void initialize() {
		
	}
	
	// Called after each new file is opened
	private void start() {
		// load and parse file
		List<Job> jobs = JobFileHandler.ParseJobFile(jobFile);
		
		fcfs = new FirstComeFirstServe();
		fcfsAvg = fcfs.start(Util.cloneList(jobs));
		
		sjf = new ShortestJobFirst();
		sjfAvg = sjf.start(Util.cloneList(jobs));
		
		rr3 = new RoundRobin();
		rr3.setTimeSlice(3);
		rr3Avg = rr3.start(Util.cloneList(jobs));
		
		rr4 = new RoundRobin();
		rr4.setTimeSlice(4);
		rr4Avg = rr4.start(Util.cloneList(jobs));
		
		displayGantt(fcfs, sjf, rr3, rr4);
		displayAverages();
	}
	
	@SuppressWarnings({ "serial", "rawtypes", "unchecked", "static-access" })
	public void displayGantt(final FirstComeFirstServe fcfs, final ShortestJobFirst sjf, final RoundRobin rr3, final RoundRobin rr4) {
		
		List<SchedulingAlgorithm> algObjectList = new ArrayList<SchedulingAlgorithm>() {{
			add(fcfs);
			add(sjf);
			add(rr3);
			add(rr4);
		}};
		
		List<String> algNames = new ArrayList<String>() {{
			add("FCFS");
			add("SJF");
			add("RR3");
			add("RR4");
		}};
		
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(1);

        //yAxis.setLabel("");
        yAxis.setTickLabelFill(Color.CHOCOLATE);
        yAxis.setTickLabelGap(10);
        yAxis.setCategories(FXCollections.<String>observableArrayList(algNames));
        
        chart.setLegendVisible(false);
        chart.setBlockHeight(50);
		
        // start loading data from each algorithm
		for (SchedulingAlgorithm algObject : algObjectList) {
			int algIndex = algObjectList.indexOf(algObject);
			
			List<Job> jobs = algObject.getJobList();
			Map<Integer, List<SegmentTuple>> segmentTimesMap = algObject.getSegmentTimesMap();
			
			// get data from each job in file for specified algorithm
	        for (Job j : jobs) {
	        	int jobIndex = jobs.indexOf(j);
	        	
	        	List<SegmentTuple> jobSegments = segmentTimesMap.get(jobIndex);
	        	
	    		XYChart.Series workingSeries = new XYChart.Series();
	    		
	    		// get data for each time segment from algorithm
	        	for (SegmentTuple t : jobSegments) {
	        		workingSeries.getData().add(new XYChart.Data(t.getStartTime(), algNames.get(algIndex), new ExtraData((long) (t.getEndTime() - t.getStartTime()), "job" + (jobIndex + 1))));
	        	}
	        	
	        	chart.getData().add(workingSeries);
	        }      
		}
		
        chart.getStylesheets().add(getClass().getResource(GANTTCHART_CSS_FILEPATH).toExternalForm());
		
        chartAnchor.setTopAnchor(chart, 0.0);
        chartAnchor.setBottomAnchor(chart, 0.0);
        chartAnchor.setLeftAnchor(chart, 0.0);
        chartAnchor.setRightAnchor(chart, 0.0);
        chartAnchor.getChildren().setAll(chart);
	}
	
	private void displayAverages() {
		List<String> averagesList = new ArrayList<String>();
		averagesList.add("RR4: " + rr4Avg);
		averagesList.add("RR3: " + rr3Avg);
		averagesList.add("SJF: " + sjfAvg);
		averagesList.add("FCFS: " + fcfsAvg);
		
		
		averagesListView.setItems(FXCollections.<String>observableArrayList(averagesList));
	}
	
	@FXML
	private void openFileButtonAction() {
		Stage stage = new Stage();
		final FileChooser fileChooser = new FileChooser();
		
		jobFile = fileChooser.showOpenDialog(stage).getPath();
		
		start();
		
	}
	
}
