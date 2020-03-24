package com.gmail.ssoch.healthcheck.dao.data;

import android.util.Range;

public class PulseNorm {
    private final Range<Integer> pulse;
    private final String description;

    public PulseNorm(Range<Integer> pulse, String description) {
        this.pulse = pulse;
        this.description = description;
    }

    public Range<Integer> getPulse() {
        return pulse;
    }

    public String getDescription() {
        return description;
    }
}
