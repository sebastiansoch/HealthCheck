package com.gmail.ssoch.healthcheck;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;
import com.gmail.ssoch.healthcheck.utils.BloodPressureChartData;
import com.gmail.ssoch.healthcheck.utils.BodyWeightChartData;
import com.gmail.ssoch.healthcheck.utils.GlucoseLevelChartData;
import com.gmail.ssoch.healthcheck.utils.PulseChartData;
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
    private String statBeginDate = "2020-02-25";
    private String statEndDate = "2020-03-21";

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            try {
                tab.parent.setTabRippleColor(ColorStateList.valueOf(Statistic.this.getResources().getColor(R.color.colorAccent, null)));

                if (tab.getPosition() == BLOOD_PRESSURE.tabPosition) {
                    BloodPressureChartData bloodPressureChartData = new BloodPressureChartData(healthCheckDataDao, statBeginDate, statEndDate);
                    bloodPressureChartData.prepareData();

                    prepareChart(bloodPressureChartData.getChartData(), bloodPressureChartData.getXLabels());

                } else if (tab.getPosition() == PULSE.tabPosition) {
                    PulseChartData pulseChartData = new PulseChartData(healthCheckDataDao, statBeginDate, statEndDate);
                    pulseChartData.prepareData();

                    prepareChart(pulseChartData.getChartData(), pulseChartData.getXLabels());

                } else if (tab.getPosition() == BODY_WEIGHT.tabPosition) {
                    BodyWeightChartData bodyWeightChartData = new BodyWeightChartData(healthCheckDataDao, statBeginDate, statEndDate);
                    bodyWeightChartData.prepareData();

                    prepareChart(bodyWeightChartData.getChartData(), bodyWeightChartData.getXLabels());

                } else if (tab.getPosition() == GLUCOSE_LEVEL.tabPosition) {
                    GlucoseLevelChartData glucoseLevelChartData = new GlucoseLevelChartData(healthCheckDataDao, statBeginDate, statEndDate);
                    glucoseLevelChartData.prepareData();

                    prepareChart(glucoseLevelChartData.getChartData(), glucoseLevelChartData.getXLabels());

                } else {
                    Toast.makeText(Statistic.this, "Category wasn't found", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
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

    private void aaa() throws Exception {
        BloodPressureChartData bloodPressureChartData = new BloodPressureChartData(healthCheckDataDao, statBeginDate, statEndDate);
        bloodPressureChartData.prepareData();

        prepareChart(bloodPressureChartData.getChartData(), bloodPressureChartData.getXLabels());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        healthCheckDataDao = new HealthCheckDataDaoFile(this);

        tabLayout = findViewById(R.id.statistic_tabs);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));
        tabLayout.addOnTabSelectedListener(tabSelectedListener);

        chart = findViewById(R.id.statistic_chart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tabLayout.getTabAt(0).select();
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

    enum TAB_ITEM {
        BLOOD_PRESSURE(0),
        PULSE(1),
        BODY_WEIGHT(2),
        GLUCOSE_LEVEL(3);

        int tabPosition;

        private TAB_ITEM(int tabPosition) {
            this.tabPosition = tabPosition;
        }
    }
}
