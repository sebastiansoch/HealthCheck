package com.gmail.ssoch.healthcheck.chart;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelData;
import com.gmail.ssoch.healthcheck.dao.data.GlucoseLevelNorm;

import java.util.ArrayList;
import java.util.List;

public class GlucoseLevelChartDataGenerator implements ChartDataGenerator {
    private final StatisticData statisticData;
    private List<String> xLabels = new ArrayList<>();

    private LineDataSet normMinDataSet;
    private LineDataSet normMaxDataSet;
    private LineDataSet normDataSet;

    public GlucoseLevelChartDataGenerator(StatisticData statisticData) {
        this.statisticData = statisticData;
    }

    @Override
    public void prepareData() throws Exception {
        List<GlucoseLevelData> dataToShow = statisticData.getDataToShow();
        List<GlucoseLevelNorm> norms = statisticData.getNorms();

        float normMin = 0;
        float normMax = 0;

        for (GlucoseLevelNorm norm : norms) {
            if (norm.getDescription().startsWith("normal")) {
                normMin = norm.getGlucoseLevel().getLower().floatValue();
                normMax = norm.getGlucoseLevel().getUpper().floatValue();
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
            dataEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getGlucoseLevelMmol())));
            xLabels.add(dataToShow.get(i).getMeasurementDate());
        }

        normMinDataSet = new LineDataSet(normMinEntries, "Glucose Level Lower Norm");
        normMinDataSet.setColor(Color.BLUE);
        normMinDataSet.setValueTextColor(Color.BLUE);
        normMinDataSet.setDrawIcons(false);

        normMaxDataSet = new LineDataSet(normMaxEntries, "Glucose Level Upper Norm");
        normMaxDataSet.setColor(Color.BLUE);
        normMaxDataSet.setValueTextColor(Color.BLUE);
        normMaxDataSet.setDrawIcons(false);

        normDataSet = new LineDataSet(dataEntries, "Glucose Level (Mmol)");
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
