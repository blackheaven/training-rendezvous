package org.rendezvous;

import org.rendezvous.utils.Duration;

public enum AppointmentType {
    Screening(Duration.minutes(15)), Behavior(Duration.minutes(45)), Technical(Duration.minutes(120));

    private final Duration duration;

    AppointmentType(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
