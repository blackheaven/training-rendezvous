package org.rendezvous.utils;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class DaytimeTest {

    @Test
    void at_createsValidDaytime() {
        Daytime daytime = Daytime.at(14, 30);
        assertThat(daytime.getHours()).isEqualTo(14);
        assertThat(daytime.getMinutes()).isEqualTo(30);
    }

    @Test
    void at_createsValidDaytimeAtMidnight() {
        Daytime daytime = Daytime.at(0, 0);
        assertThat(daytime.getHours()).isEqualTo(0);
        assertThat(daytime.getMinutes()).isEqualTo(0);
    }

    @Test
    void at_createsValidDaytimeAtEndOfDay() {
        Daytime daytime = Daytime.at(23, 59);
        assertThat(daytime.getHours()).isEqualTo(23);
        assertThat(daytime.getMinutes()).isEqualTo(59);
    }

    @Test
    void toString_displaysHoursAndMinutes() {
        assertThat(Daytime.at(9, 30).toString()).isEqualTo("09:30");
        assertThat(Daytime.at(14, 45).toString()).isEqualTo("14:45");
        assertThat(Daytime.at(0, 0).toString()).isEqualTo("00:00");
        assertThat(Daytime.at(23, 59).toString()).isEqualTo("23:59");
    }

    @Test
    void toString_padsSingleDigits() {
        assertThat(Daytime.at(9, 5).toString()).isEqualTo("09:05");
        assertThat(Daytime.at(0, 0).toString()).isEqualTo("00:00");
    }

    @Test
    void equals_returnsTrueForSameDaytime() {
        Daytime daytime1 = Daytime.at(14, 30);
        Daytime daytime2 = Daytime.at(14, 30);
        assertThat(daytime1).isEqualTo(daytime2);
    }

    @Test
    void equals_returnsFalseForDifferentDaytime() {
        Daytime daytime1 = Daytime.at(14, 30);
        Daytime daytime2 = Daytime.at(15, 30);
        assertThat(daytime1).isNotEqualTo(daytime2);
    }

    @Test
    void equals_returnsFalseForNull() {
        Daytime daytime = Daytime.at(14, 30);
        assertThat(daytime).isNotEqualTo(null);
    }

    @Test
    void hashCode_isConsistentForEqualDaytimes() {
        Daytime daytime1 = Daytime.at(14, 30);
        Daytime daytime2 = Daytime.at(14, 30);
        assertThat(daytime1.hashCode()).isEqualTo(daytime2.hashCode());
    }

    @Test
    void compareTo_returnsZeroForEqualDaytimes() {
        Daytime daytime1 = Daytime.at(14, 30);
        Daytime daytime2 = Daytime.at(14, 30);
        assertThat(daytime1).isEqualByComparingTo(daytime2);
    }

    @Test
    void compareTo_returnsNegativeWhenEarlier() {
        Daytime earlier = Daytime.at(9, 30);
        Daytime later = Daytime.at(14, 30);
        assertThat(earlier).isLessThan(later);
    }

    @Test
    void compareTo_returnsPositiveWhenLater() {
        Daytime later = Daytime.at(16, 30);
        Daytime earlier = Daytime.at(14, 30);
        assertThat(later).isGreaterThan(earlier);
    }

    @Test
    void add_returnsNewDaytimeWhenWithinDayLimit() {
        Daytime daytime = Daytime.at(10, 30);
        Duration duration = Duration.minutes(90);
        Optional<Daytime> result = daytime.add(duration);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(Daytime.at(12, 0));
    }

    @Test
    void add_returnsEmptyWhenExceedsDayLimit() {
        Daytime daytime = Daytime.at(23, 30);
        Duration duration = Duration.minutes(60);
        Optional<Daytime> result = daytime.add(duration);

        assertThat(result).isEmpty();
    }

    @Test
    void add_returnsEmptyWhenExactlyAtDayLimit() {
        Daytime daytime = Daytime.at(23, 0);
        Duration duration = Duration.minutes(60);
        Optional<Daytime> result = daytime.add(duration);

        assertThat(result).isEmpty();
    }

    @Test
    void add_returnsValidDaytimeJustBeforeDayLimit() {
        Daytime daytime = Daytime.at(23, 0);
        Duration duration = Duration.minutes(59);
        Optional<Daytime> result = daytime.add(duration);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(Daytime.at(23, 59));
    }
}