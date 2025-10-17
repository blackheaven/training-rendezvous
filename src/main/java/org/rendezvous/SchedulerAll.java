package org.rendezvous;

import java.util.List;

public class SchedulerAll implements Scheduler {
    private final List<Scheduler> schedulers;

    public SchedulerAll(List<Scheduler> schedulers) {
        this.schedulers = schedulers;
    }

    public SchedulerAll(Scheduler ...schedulers) {
        this.schedulers = List.of(schedulers);
    }

    @Override
    public boolean canBook(Appointment plannedAppointment) {
        for (Scheduler scheduler : this.schedulers) {
            if (!scheduler.canBook(plannedAppointment)) {
                return false;
            }
        }
        return true;
    }
}
