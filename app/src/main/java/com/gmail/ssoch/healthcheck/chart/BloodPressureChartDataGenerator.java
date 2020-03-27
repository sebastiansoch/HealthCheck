package com.gmail.ssoch.healthcheck.chart;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureNorm;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureChartDataGenerator implements ChartDataGenerator {
    private final StatisticData statisticData;
    private final List<String> xLabels = new ArrayList<>();

    private LineDataSet systolicNormMinDataSet;
    private LineDataSet systolicNormMaxDataSet;
    private LineDataSet diastolicNormMinDataSet;
    private LineDataSet diastolicNormMaxDataSet;
    private LineDataSet systolicDataSet;
    private LineDataSet diastolicDataSet;

    public BloodPressureChartDataGenerator(StatisticData statisticData) {
        this.statisticData = statisticData;
    }

    @Override
    public void prepareData() throws Exception {
        List<BloodPressureData> dataToShow = statisticData.getDataToShow();
        List<BloodPressureNorm> norms = statisticData.getNorms();

        int systolicNormMin = 0;
        int systolicNormMax = 0;
        int diastolicNormMin = 0;
        int diastolicNormMax = 0;

        for (BloodPressureNorm norm : norms) {
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

        systolicNormMinEntries.add(new Entry(dataToShow.size() - 1, systolicNormMin));
        systolicNormMaxEntries.add(new Entry(dataToShow.size() - 1, systolicNormMax));
        diastolicNormMinEntries.add(new Entry(dataToShow.size() - 1, diastolicNormMin));
        diastolicNormMaxEntries.add(new Entry(dataToShow.size() - 1, diastolicNormMax));

        List<Entry> systolicEntries = new ArrayList<>();
        List<Entry> diastolicEntries = new ArrayList<>();

        for (int i = 0; i < dataToShow.size(); i++) {
            systolicEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getSystolic())));
            diastolicEntries.add(new Entry(i, Float.parseFloat(dataToShow.get(i).getDiastolic())));
            xLabels.add(dataToShow.get(i).getMeasurementDate());
        }

        systolicNormMinDataSet = new LineDataSet(systolicNormMinEntries, "Systolic Lower Norm");
        systolicNormMinDataSet.setColor(Color.BLUE);
        systolicNormMinDataSet.setValueTextColor(Color.BLUE);
        systolicNormMinDataSet.setDrawIcons(false);

        systolicNormMaxDataSet = new LineDataSet(systolicNormMaxEntries, "Systolic Upper Norm");
        systolicNormMaxDataSet.setColor(Color.BLUE);
        systolicNormMaxDataSet.setValueTextColor(Color.BLUE);
        systolicNormMaxDataSet.setDrawIcons(false);

        diastolicNormMinDataSet = new LineDataSet(diastolicNormMinEntries, "Diastolic Lower Norm");
        diastolicNormMinDataSet.setColor(Color.RED);
        diastolicNormMinDataSet.setValueTextColor(Color.RED);
        diastolicNormMinDataSet.setDrawIcons(false);

        diastolicNormMaxDataSet = new LineDataSet(diastolicNormMaxEntries, "Diastolic Upper Norm");
        diastolicNormMaxDataSet.setColor(Color.RED);
        diastolicNormMaxDataSet.setValueTextColor(Color.RED);
        diastolicNormMaxDataSet.setDrawIcons(false);

        systolicDataSet = new LineDataSet(systolicEntries, "Systolic");
        systolicDataSet.setColor(Color.BLACK);
        systolicDataSet.setValueTextColor(Color.BLACK);
        systolicDataSet.setLineWidth(3);

        diastolicDataSet = new LineDataSet(diastolicEntries, "Diastolic");
        diastolicDataSet.setColor(Color.MAGENTA);
        diastolicDataSet.setValueTextColor(Color.MAGENTA);
        diastolicDataSet.setLineWidth(3);
    }

    @Override
    public List<String> getXLabels() {
        return xLabels;
    }

    @Override
    public LineData getChartData() {
        return new LineData(systolicDataSet, systolicNormMinDataSet, systolicNormMaxDataSet,
                diastolicDataSet, diastolicNormMinDataSet, diastolicNormMaxDataSet);
    }
}
