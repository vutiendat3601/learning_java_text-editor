package com.datvutech.texteditor.util;

import java.time.Duration;
import java.time.Instant;

public class Stopwatch {
    private Instant startTime;
    private Instant endTime;
    private long numOfSeconds;

    public Stopwatch() {
        startTime = Instant.now();
        endTime = startTime;
    }

    public void stopAndResetNow() {
        endTime = Instant.now();
        numOfSeconds = Duration.between(startTime, endTime).toSeconds();
        startTime = endTime;
    }

    public long getSeconds() {
        return numOfSeconds;
    }

}
