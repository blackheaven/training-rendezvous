package org.rendezvous;

import java.util.List;

public class SchedulerConflict implements Scheduler {
    private final List<Appointment> appointments;

    public SchedulerConflict(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public boolean canBook(Appointment plannedAppointment) {
        for (Appointment appointment : this.appointments) {
            if (appointment.hasConflict(plannedAppointment)) {
                return false;
            }
        }
        return true;
    }
}
