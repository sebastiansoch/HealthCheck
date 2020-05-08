package com.gmail.ssoch.healthcheck;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Range;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.BLOOD_PRESSURE;
import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.BODY_WEIGHT;
import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.GLUCOSE_LEVEL;
import static com.gmail.ssoch.healthcheck.Statistic.TAB_ITEM.PULSE;


public class Statistic extends BaseActivity {

    private TabLayout tabLayout;
    private LineChart chart;
    private Range<String> statisticRange;

    private TextView beginDateTV;
    private ImageButton beginDateBtn;
    private ImageButton.OnClickListener beginDateBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] split = beginDateTV.getText().toString().split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]) - 1;
            int day = Integer.parseInt(split[2]);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Statistic.this, android.R.style.Theme_Material_Dialog_MinWidth,
                    beginDatePickerDateSetListener, year, month, day);
            datePickerDialog.show();
        }
    };
    private TextView endDateTV;
    private ImageButton endDateBtn;
    private ImageButton.OnClickListener endDateBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] split = endDateTV.getText().toString().split("-");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]) - 1;
            int day = Integer.parseInt(split[2]);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Statistic.this, android.R.style.Theme_Material_Dialog_MinWidth,
                    endDatePickerDateSetListener, year, month, day);
            datePickerDialog.show();
        }
    };
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
    private DatePickerDialog.OnDateSetListener beginDatePickerDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(year + "-" + (month + 1) + "-" + dayOfMonth);
                if (date.after(new Date())) {
                    Toast.makeText(Statistic.this, "Begin date is not seated correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sBeginDate = dateFormat.format(date);
                beginDateTV.setText(sBeginDate);

                String sEndDate = endDateTV.getText().toString();
                statisticRange = Range.create(sBeginDate, sEndDate);

                tabSelectedListener.onTabReselected(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };
    private DatePickerDialog.OnDateSetListener endDatePickerDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(year + "-" + (month + 1) + "-" + dayOfMonth);
                Date beginDate = dateFormat.parse(beginDateTV.getText().toString());

                if (date.before(beginDate)) {
                    Toast.makeText(Statistic.this, "End date is not seated correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sEndDate = dateFormat.format(date);
                endDateTV.setText(sEndDate);

                String sBeginDate = beginDateTV.getText().toString();
                statisticRange = Range.create(sBeginDate, sEndDate);

                tabSelectedListener.onTabReselected(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
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

        tabLayout = findViewById(R.id.statistic_tabs);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        configureTabLayoutView();

        chart = findViewById(R.id.statistic_chart);

        beginDateTV = findViewById(R.id.statistic_begin_date_TV);
        beginDateTV.setText(getPreviousMonth());
        beginDateBtn = findViewById(R.id.statistic_begin_date_Btn);
        beginDateBtn.setOnClickListener(beginDateBtnListener);

        endDateTV = findViewById(R.id.statistic_end_date_TV);
        endDateTV.setText(getCurrentMonth());
        endDateBtn = findViewById(R.id.statistic_end_date_Btn);
        endDateBtn.setOnClickListener(endDateBtnListener);

        statisticRange = new Range<>(getPreviousMonth(), getCurrentMonth());

        cancelBtn = findViewById(R.id.statistic_cancel_Btn);
        cancelBtn.setOnClickListener(cancelBtnListener);
    }

    private String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return cal.get(Calendar.YEAR) + "-" + String.format("%02d", month) + "-" + String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
    }

    private String getPreviousMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        return cal.get(Calendar.YEAR) + "-" + String.format("%02d", month) + "-" + String.format("%02d" , cal.get(Calendar.DAY_OF_MONTH));
    }

    private void configureTabLayoutView() {
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary, null));
        tabLayout.setTabRippleColor(ColorStateList.valueOf(this.getResources().getColor(R.color.colorAccent, null)));
    }

    private void prepareChart(LineData linedata, List<String> xLabels) {
        formatXAxis(xLabels);

        chart.getLegend().setWordWrapEnabled(true);
        chart.getDescription().setEnabled(false);
        chart.setData(linedata);
        chart.invalidate();
    }

    private void formatXAxis(final List<String> xLabels) {
        chart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if (xLabels.size() >= value) {
                    return xLabels.get((int) value);
                }
                return String.valueOf(xLabels.size());
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
