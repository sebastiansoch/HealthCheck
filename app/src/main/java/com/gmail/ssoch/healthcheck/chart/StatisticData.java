package com.gmail.ssoch.healthcheck.chart;

import java.io.IOException;
import java.util.List;

public interface StatisticData<T, V> {

    List<T> getDataToShow() throws IOException;

    List<V> getNorms() throws Exception;
}
