package org.rendezvous.utils;

import java.util.Objects;

public class Duration implements Comparable<Duration> {
    private final int minutes;

    private Duration(int minutes) {
        this.minutes = minutes;
    }

    public static Duration minutes(int minutes) {
        assert minutes > 0;
        return new Duration(minutes);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Duration duration)) return false;
        return minutes == duration.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(minutes);
    }

    @Override
    public String toString() {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }


    @Override
    public int compareTo(Duration other) {
        return Integer.compare(this.minutes, other.minutes);
    }

    public int getMinutes() {
        return minutes;
    }
}
