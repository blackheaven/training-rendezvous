package org.rendezvous.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DurationTest {

    @Test
    void minutes_createsValidDuration() {
        Duration duration = Duration.minutes(30);
        assertThat(duration.getMinutes()).isEqualTo(30);
    }

    @Test
    void toString_displaysHoursAndMinutes() {
        assertThat(Duration.minutes(30).toString()).isEqualTo("00:30");
        assertThat(Duration.minutes(60).toString()).isEqualTo("01:00");
        assertThat(Duration.minutes(135).toString()).isEqualTo("02:15");
        assertThat(Duration.minutes(645).toString()).isEqualTo("10:45");
    }

    @Test
    void toString_padsSingleDigits() {
        assertThat(Duration.minutes(5).toString()).isEqualTo("00:05");
        assertThat(Duration.minutes(69).toString()).isEqualTo("01:09");
    }

    @Test
    void equals_returnsTrueForSameDuration() {
        Duration duration1 = Duration.minutes(60);
        Duration duration2 = Duration.minutes(60);
        assertThat(duration1).isEqualTo(duration2);
    }

    @Test
    void equals_returnsFalseForDifferentDuration() {
        Duration duration1 = Duration.minutes(60);
        Duration duration2 = Duration.minutes(90);
        assertThat(duration1).isNotEqualTo(duration2);
    }

    @Test
    void equals_returnsFalseForNull() {
        Duration duration = Duration.minutes(60);
        assertThat(duration).isNotEqualTo(null);
    }

    @Test
    void hashCode_isConsistentForEqualDurations() {
        Duration duration1 = Duration.minutes(60);
        Duration duration2 = Duration.minutes(60);
        assertThat(duration1.hashCode()).isEqualTo(duration2.hashCode());
    }

    @Test
    void compareTo_returnsZeroForEqualDurations() {
        Duration duration1 = Duration.minutes(60);
        Duration duration2 = Duration.minutes(60);
        assertThat(duration1).isEqualByComparingTo(duration2);
    }

    @Test
    void compareTo_returnsNegativeWhenLess() {
        Duration shorter = Duration.minutes(30);
        Duration longer = Duration.minutes(60);
        assertThat(shorter).isLessThan(longer);
    }

    @Test
    void compareTo_returnsPositiveWhenGreater() {
        Duration longer = Duration.minutes(90);
        Duration shorter = Duration.minutes(60);
        assertThat(longer).isGreaterThan(shorter);
    }
}
