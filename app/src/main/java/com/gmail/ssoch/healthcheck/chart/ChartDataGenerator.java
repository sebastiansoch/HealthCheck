package com.gmail.ssoch.healthcheck.chart;

import com.github.mikephil.charting.data.LineData;

import java.util.List;

public interface ChartDataGenerator {

    void prepareData() throws Exception;

    List<String> getXLabels();

    LineData getChartData();
}
