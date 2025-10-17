package org.rendezvous;

import org.rendezvous.utils.Duration;

public class SchedulerMain implements Scheduler {
    @Override
    public boolean canBook(Appointment plannedAppointment) {
        Scheduler screening = new SchedulerAll(new SchedulerDuration(Duration.minutes(15)), new SchedulerBuffer(Duration.minutes(10)))
        return false;
    }
}
