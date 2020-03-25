package com.gmail.ssoch.healthcheck;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Range;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class Statistic extends AppCompatActivity {

    private HealthCheckDataDaoFile healthCheckDataDao;

    private TabLayout tabLayout;
    private TabItem bloodPressureTabItem;
    private TabItem pulseTabItem;
    private TabItem bodyWeightTabItem;
    private TabItem glucoseLevelTabItem;

    private LineChart chart;

    private String beginDate;
    private String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        healthCheckDataDao = new HealthCheckDataDaoFile(this);

        tabLayout = findViewById(R.id.statistic_tabs);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(Statistic.this, "DUPA", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        chart = findViewById(R.id.statistic_chart);


        try {
            dataForChart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dataForChart() throws Exception {

//        List<BloodPressureData> dataToShow = healthCheckDataDao.getBloodPressureInRange(beginDate, endDate);


        //new Range<>(Double.parseDouble(splitLine[0]), Double.parseDouble(splitLine[1]));



        String beginDate = "2020-02-25";
        String endDate = "2020-03-21";

        List<BloodPressureData> dataToShow = healthCheckDataDao.getBloodPressureInRange(new Range<>(beginDate, endDate));

        List<BloodPressureNorm> bloodPressureNorms = healthCheckDataDao.getBloodPressureNorms();

        int systolicNormMin = 0;
        int systolicNormMax = 0;
        int diastolicNormMin = 0;
        int diastolicNormMax = 0;

        for (BloodPressureNorm norm : bloodPressureNorms) {
            if (norm.getDescription().startsWith("normal")) {
                systolicNormMin = norm.getSystolic().getLower();
                systolicNormMax = norm.getSystolic().getUpper();
                diastolicNormMin = norm.getDiastolic().getLower();
                diastolicNormMax = norm.getDiastolic().getUpper();
            }
        }

        List<Entry> systolicNormMinEntries = new ArrayList<>();
        List<Entry> systolicNormMaxEntries = new ArrayList<>();
        List<Entry> diastolicNormMinEntries = new ArrayList<>();
        List<Entry> diastolicNormMaxEntries = new ArrayList<>();

        systolicNormMinEntries.add(new Entry(0, systolicNormMin));
        systolicNormMaxEntries.add(new Entry(0, systolicNormMax));
        diastolicNormMinEntries.add(new Entry(0, diastolicNormMin));
        diastolicNormMaxEntries.add(new Entry(0, diastolicNormMax));

        systolicNormMinEntries.add(new Entry(dataToShow.size() -1, systolicNormMin));
        systolicNormMaxEntries.add(new Entry(dataToShow.size() -1, systolicNormMax));
        diastolicNormMinEntries.add(new Entry(dataToShow.size() -1, diastolicNormMin));
        diastolicNormMaxEntries.add(new Entry(dataToShow.size() -1, diastolicNormMax));

        List<Entry> systolicEntries = new ArrayList<>();
        List<Entry> diastolicEntries = new ArrayList<>();
        final List<String> xLabels = new ArrayList<>();

        for (int i = 0; i < dataToShow.size(); i++) {
            systolicEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getSystolic())));
            diastolicEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getDiastolic())));
            xLabels.add(dataToShow.get(i).getMeasurementDate());
        }

        LineDataSet systolicNormMinDataSet = new LineDataSet(systolicNormMinEntries, "Systolic Lower Norm");
        systolicNormMinDataSet.setColor(Color.BLUE);
        systolicNormMinDataSet.setValueTextColor(Color.BLUE);
        systolicNormMinDataSet.setDrawIcons(false);

        LineDataSet systolicNormMaxDataSet = new LineDataSet(systolicNormMaxEntries, "Systolic Upper Norm");
        systolicNormMaxDataSet.setColor(Color.BLUE);
        systolicNormMaxDataSet.setValueTextColor(Color.BLUE);
        systolicNormMinDataSet.setDrawIcons(false);

        LineDataSet diastolicNormMinDataSet = new LineDataSet(diastolicNormMinEntries, "Diastolic Lower Norm");
        diastolicNormMinDataSet.setColor(Color.RED);
        diastolicNormMinDataSet.setValueTextColor(Color.RED);

        LineDataSet diastolicNormMaxDataSet = new LineDataSet(diastolicNormMaxEntries, "Diastolic Upper Norm");
        diastolicNormMaxDataSet.setColor(Color.RED);
        diastolicNormMaxDataSet.setValueTextColor(Color.RED);

        LineDataSet systolicDataSet = new LineDataSet(systolicEntries, "Systolic");
        systolicDataSet.setColor(Color.BLACK);
        systolicDataSet.setValueTextColor(Color.BLACK);
        systolicDataSet.setLineWidth(3);

        LineDataSet diastolicDataSet = new LineDataSet(diastolicEntries, "Diastolic");
        diastolicDataSet.setColor(Color.MAGENTA);
        diastolicDataSet.setValueTextColor(Color.MAGENTA);
        diastolicDataSet.setLineWidth(3);

        //X-axis label

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xLabels.get((int) value);
            }
        });


        LineData lineData = new LineData(systolicDataSet, diastolicDataSet, systolicNormMinDataSet,systolicNormMaxDataSet, diastolicNormMinDataSet, diastolicNormMaxDataSet);
//        LineData lineData = new LineData(diastolicNormMinDataSet, diastolicNormMaxDataSet);
        chart.setData(lineData);
        chart.invalidate();
        chart.getLabelFor();
    }

     private List<BloodPressureData> getDataToShow() {
        List<BloodPressureData> dataToShow = new ArrayList<>();

        dataToShow.add(new BloodPressureData("120", "75", "55", "2020-03-01"));
        dataToShow.add(new BloodPressureData("110", "90", "75", "2020-03-02"));
        dataToShow.add(new BloodPressureData("115", "80", "70", "2020-03-03"));
        dataToShow.add(new BloodPressureData("121", "84", "65", "2020-03-04"));
        dataToShow.add(new BloodPressureData("136", "65", "85", "2020-03-05"));
        dataToShow.add(new BloodPressureData("113", "78", "83", "2020-03-06"));
        dataToShow.add(new BloodPressureData("128", "66", "65", "2020-03-07"));
        dataToShow.add(new BloodPressureData("113", "83", "65", "2020-03-08"));
        dataToShow.add(new BloodPressureData("111", "69", "65", "2020-03-09"));
        dataToShow.add(new BloodPressureData("110", "75", "50", "2020-03-10"));

        return dataToShow;
    }
}
