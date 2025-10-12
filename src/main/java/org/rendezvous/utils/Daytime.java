package org.rendezvous.utils;

import java.util.Objects;
import java.util.Optional;

public class Daytime implements Comparable<Daytime> {
    private static int DAY_LIMIT = 24 * 60;
    private final int dayMinutes;

    private Daytime(int dayMinutes) {
        this.dayMinutes = dayMinutes;
    }

    public static Daytime at(int hours, int minutes) {
        assert hours >= 0 && hours <= 23;
        assert minutes >= 0 && minutes <= 59;
        var dayMinutes = hours * 60 + minutes;
        assert dayMinutes < DAY_LIMIT;
        return new Daytime(dayMinutes);
    }

    @Override
    public String toString() {
        int hours = dayMinutes / 60;
        int mins = dayMinutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Daytime daytime)) return false;
        return dayMinutes == daytime.dayMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayMinutes);
    }

    @Override
    public int compareTo(Daytime other) {
        return Integer.compare(this.dayMinutes, other.dayMinutes);
    }

    public Optional<Daytime> add(Duration duration) {
        int newDayMinutes = this.dayMinutes + duration.getMinutes();
        if (newDayMinutes >= DAY_LIMIT) {
            return Optional.empty();
        }
        return Optional.of(new Daytime(newDayMinutes));
    }

    public int getMinutes() {
        return this.dayMinutes%60;
    }

    public int getHours() {
        return this.dayMinutes/60;
    }
}
