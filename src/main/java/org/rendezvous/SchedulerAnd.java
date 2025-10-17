package org.rendezvous;

public class SchedulerAnd implements Scheduler {
    private final Scheduler scheduler0;
    private final Scheduler scheduler1;

    public SchedulerAnd(Scheduler scheduler0, Scheduler scheduler1) {
        this.scheduler0 = scheduler0;
        this.scheduler1 = scheduler1;
    }

    @Override
    public boolean canBook(Appointment plannedAppointment) {
        return this.scheduler0.canBook(plannedAppointment) && this.scheduler1.canBook(plannedAppointment);
    }
}
