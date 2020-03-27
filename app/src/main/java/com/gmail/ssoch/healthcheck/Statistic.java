package com.gmail.ssoch.healthcheck;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Range;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.gmail.ssoch.healthcheck.chart.BloodPressureChartDataGenerator;
import com.gmail.ssoch.healthcheck.chart.BloodPressureStatisticData;
import com.gmail.ssoch.healthcheck.chart.BodyWeightChartDataGenerator;
import com.gmail.ssoch.healthcheck.chart.BodyWeightStatisticData;
import com.gmail.ssoch.healthcheck.chart.ChartDataGenerator;
import com.gmail.ssoch.healthcheck.chart.GlucoseLevelChartDataGenerator;
import com.gmail.ssoch.healthcheck.chart.GlucoseLevelStatisticData;
import com.gmail.ssoch.healthcheck.chart.PulseChartDataGenerator;
import com.gmail.ssoch.healthcheck.chart.PulseStatisticData;
import com.gmail.ssoch.healthcheck.chart.StatisticData;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.BLOOD_PRESSURE;
import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.BODY_WEIGHT;
import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.GLUCOSE_LEVEL;
import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.PULSE;


public class Statistic extends AppCompatActivity {

    private HealthCheckDataDaoFile healthCheckDataDao;
    private TabLayout tabLayout;
    private LineChart chart;
    private Range<String> statisticRange = new Range<>("2020-02-25", "2020-03-21");

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            try {
                if (tab.getPosition() == BLOOD_PRESSURE.tabPosition) {
                    StatisticData statisticData = new BloodPressureStatisticData(healthCheckDataDao, statisticRange);
                    ChartDataGenerator chartDataGenerator = new BloodPressureChartDataGenerator(statisticData);
                    chartDataGenerator.prepareData();
                    prepareChart(chartDataGenerator.getChartData(), chartDataGenerator.getXLabels());

                } else if (tab.getPosition() == PULSE.tabPosition) {
                    StatisticData statisticData = new PulseStatisticData(healthCheckDataDao, statisticRange);
                    ChartDataGenerator chartDataGenerator = new PulseChartDataGenerator(statisticData);
                    chartDataGenerator.prepareData();
                    prepareChart(chartDataGenerator.getChartData(), chartDataGenerator.getXLabels());

                } else if (tab.getPosition() == BODY_WEIGHT.tabPosition) {
                    StatisticData statisticData = new BodyWeightStatisticData(healthCheckDataDao, statisticRange);
                    ChartDataGenerator chartDataGenerator = new BodyWeightChartDataGenerator(statisticData);
                    chartDataGenerator.prepareData();
                    prepareChart(chartDataGenerator.getChartData(), chartDataGenerator.getXLabels());

                } else if (tab.getPosition() == GLUCOSE_LEVEL.tabPosition) {
                    StatisticData statisticData = new GlucoseLevelStatisticData(healthCheckDataDao, statisticRange);
                    ChartDataGenerator chartDataGenerator = new GlucoseLevelChartDataGenerator(statisticData);
                    chartDataGenerator.prepareData();
                    prepareChart(chartDataGenerator.getChartData(), chartDataGenerator.getXLabels());

                } else {
                    prepareChart(null, null);
                    Toast.makeText(Statistic.this, "Category wasn't found", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                prepareChart(null, null);
                Toast.makeText(Statistic.this, "Something went wrong, we can not show statistics", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            onTabSelected(tab);
        }
    };

    private Button cancelBtn;
    private Button.OnClickListener cancelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Statistic.this, MainActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        healthCheckDataDao = new HealthCheckDataDaoFile(this);

        tabLayout = findViewById(R.id.statistic_tabs);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        configureTabLayoutView();

        chart = findViewById(R.id.statistic_chart);

        cancelBtn = findViewById(R.id.statistic_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }

    private void configureTabLayoutView() {
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));
        tabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorAccent, null)));
    }

    private void prepareChart(LineData linedata, List<String> xLabels) {
        formatXAxis(xLabels);

        chart.getLegend().setWordWrapEnabled(true);
        chart.setData(linedata);
        chart.invalidate();
    }

    private void formatXAxis(final List<String> xLabels) {
        chart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xLabels.get((int) value);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.getTabAt(0).select();
    }

    enum TAB_ITEM {
        BLOOD_PRESSURE(0),
        PULSE(1),
        BODY_WEIGHT(2),
        GLUCOSE_LEVEL(3);

        int tabPosition;

        TAB_ITEM(int tabPosition) {
            this.tabPosition = tabPosition;
        }
    }
}
