package org.rendezvous;

import org.rendezvous.utils.Daytime;
import org.rendezvous.utils.Duration;
import org.rendezvous.utils.InclusiveRange;

import java.time.DayOfWeek;

public class Appointment {
    private DayOfWeek day;
    private Daytime daytime;
    private AppointmentType type;


    public Appointment(DayOfWeek day, Daytime daytime, AppointmentType type) {
        this.day = day;
        this.daytime = daytime;
        this.type = type;
    }

    public boolean hasConflict(Appointment otherAppointment) {
        InclusiveRange<Daytime> busyRange = InclusiveRange.of(
            this.daytime,
            this.daytime.add(this.type.getDuration()).get()
        );
        return
            this.day == otherAppointment.day
                && busyRange.includes(otherAppointment.daytime)
                && busyRange.includes(otherAppointment.daytime.add(otherAppointment.type.getDuration()).get());
    }
}
