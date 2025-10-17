package org.rendezvous;

import org.rendezvous.utils.Daytime;
import org.rendezvous.utils.Duration;

import java.time.DayOfWeek;

public interface Scheduler {
    boolean canBook(Appointment plannedAppointment);
}
