package com.gmail.ssoch.healthcheck.chart;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.gmail.ssoch.healthcheck.dao.data.PulseData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;

import java.util.ArrayList;
import java.util.List;

public class PulseChartDataGenerator implements ChartDataGenerator {
    private final StatisticData statisticData;
    private List<String> xLabels = new ArrayList<>();

    private LineDataSet normMinDataSet;
    private LineDataSet normMaxDataSet;
    private LineDataSet normDataSet;

    public PulseChartDataGenerator(StatisticData statisticData) {

        this.statisticData = statisticData;
    }

    @Override
    public void prepareData() throws Exception {
        List<PulseData> dataToShow = statisticData.getDataToShow();
        List<PulseNorm> norms = statisticData.getNorms();

        int normMin = 0;
        int normMax = 0;

        for (PulseNorm norm : norms) {
            if (norm.getDescription().startsWith("normal")) {
                normMin = norm.getPulse().getLower();
                normMax = norm.getPulse().getUpper();
            }
        }

        List<Entry> normMinEntries = new ArrayList<>();
        List<Entry> normMaxEntries = new ArrayList<>();

        normMinEntries.add(new Entry(0, normMin));
        normMaxEntries.add(new Entry(0, normMax));

        normMinEntries.add(new Entry(dataToShow.size() - 1, normMin));
        normMaxEntries.add(new Entry(dataToShow.size() - 1, normMax));

        List<Entry> dataEntries = new ArrayList<>();

        for (int i = 0; i < dataToShow.size(); i++) {
            dataEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getPulse())));
            xLabels.add(dataToShow.get(i).getMeasurementDate());
        }

        normMinDataSet = new LineDataSet(normMinEntries, "Pulse Lower Norm");
        normMinDataSet.setColor(Color.BLUE);
        normMinDataSet.setValueTextColor(Color.BLUE);
        normMinDataSet.setDrawIcons(false);

        normMaxDataSet = new LineDataSet(normMaxEntries, "Pulse Upper Norm");
        normMaxDataSet.setColor(Color.BLUE);
        normMaxDataSet.setValueTextColor(Color.BLUE);
        normMaxDataSet.setDrawIcons(false);

        normDataSet = new LineDataSet(dataEntries, "Pulse");
        normDataSet.setColor(Color.BLACK);
        normDataSet.setValueTextColor(Color.BLACK);
        normDataSet.setLineWidth(3);
    }

    @Override
    public List<String> getXLabels() {
        return xLabels;
    }

    @Override
    public LineData getChartData() {
        return new LineData(normDataSet, normMinDataSet, normMaxDataSet);
    }
}
