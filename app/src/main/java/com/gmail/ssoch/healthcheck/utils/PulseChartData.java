package com.gmail.ssoch.healthcheck.utils;

import android.graphics.Color;
import android.util.Range;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.gmail.ssoch.healthcheck.dao.data.BloodPressureData;
import com.gmail.ssoch.healthcheck.dao.data.PulseNorm;
import com.gmail.ssoch.healthcheck.dao.file.HealthCheckDataDaoFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PulseChartData {
    private final HealthCheckDataDaoFile healthCheckDataDao;
    private final String statBeginDate;
    private final String statEndDate;

    private LineDataSet normMinDataSet;
    private LineDataSet normMaxDataSet;
    private LineDataSet normDataSet;

    private List<String> xLabels = new ArrayList<>();

    public PulseChartData(HealthCheckDataDaoFile healthCheckDataDao, String statBeginDate, String statEndDate) {
        this.healthCheckDataDao = healthCheckDataDao;
        this.statBeginDate = statBeginDate;
        this.statEndDate = statEndDate;
    }

    public void prepareData() throws Exception {
        List<BloodPressureData> dataToShow = getDataToShow();
        List<PulseNorm> norms = getNorms();

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

    private List<BloodPressureData> getDataToShow() throws IOException {
        return healthCheckDataDao.getBloodPressureInRange(new Range<>(statBeginDate, statEndDate));
    }

    private List<PulseNorm> getNorms() throws Exception {
        return healthCheckDataDao.getPulseNorms();
    }

    public List<String> getXLabels() {
        return xLabels;
    }

    public LineData getChartData() {
        return new LineData(normDataSet, normMinDataSet, normMaxDataSet);
    }
}
