package com.gmail.ssoch.healthcheck.chart;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightData;
import com.gmail.ssoch.healthcheck.dao.data.BodyWeightNorm;

import java.util.ArrayList;
import java.util.List;

public class BodyWeightChartDataGenerator implements ChartDataGenerator {
    private final StatisticData statisticData;
    private List<String> xLabels = new ArrayList<>();

    private LineDataSet normMinDataSet;
    private LineDataSet normMaxDataSet;
    private LineDataSet normDataSet;

    public BodyWeightChartDataGenerator(StatisticData statisticData) {
        this.statisticData = statisticData;
    }

    @Override
    public void prepareData() throws Exception {
        List<BodyWeightData> dataToShow = statisticData.getDataToShow();
        List<BodyWeightNorm> norms = statisticData.getNorms();

        float normMin = 0;
        float normMax = 0;

        for (BodyWeightNorm norm : norms) {
            if (norm.getDescription().startsWith("normal")) {
                normMin = norm.getBodyWeight().getLower().floatValue();
                normMax = norm.getBodyWeight().getUpper().floatValue();
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
            dataEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getBodyWeight())));
            xLabels.add(dataToShow.get(i).getMeasurementDate());
        }

        normMinDataSet = new LineDataSet(normMinEntries, "Body Weight Lower Norm");
        normMinDataSet.setColor(Color.BLUE);
        normMinDataSet.setValueTextColor(Color.BLUE);
        normMinDataSet.setDrawIcons(false);

        normMaxDataSet = new LineDataSet(normMaxEntries, "Body Weight Upper Norm");
        normMaxDataSet.setColor(Color.BLUE);
        normMaxDataSet.setValueTextColor(Color.BLUE);
        normMaxDataSet.setDrawIcons(false);

        normDataSet = new LineDataSet(dataEntries, "Body Weight");
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
