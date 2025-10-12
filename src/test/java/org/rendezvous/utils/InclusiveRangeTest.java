package org.rendezvous.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InclusiveRangeTest {

    @Nested
    class IntegerRangeTests {

        @Test
        void of_createsValidRange() {
            InclusiveRange<Integer> range = InclusiveRange.of(1, 10);
            assertThat(range).isNotNull();
        }

        @Test
        void includes_returnsTrueForLowerBound() {
            InclusiveRange<Integer> range = InclusiveRange.of(1, 10);
            assertThat(range.includes(1)).isTrue();
        }

        @Test
        void includes_returnsTrueForUpperBound() {
            InclusiveRange<Integer> range = InclusiveRange.of(1, 10);
            assertThat(range.includes(10)).isTrue();
        }

        @Test
        void includes_returnsTrueForValueInRange() {
            InclusiveRange<Integer> range = InclusiveRange.of(1, 10);
            assertThat(range.includes(5)).isTrue();
        }

        @Test
        void includes_returnsFalseForValueBelowRange() {
            InclusiveRange<Integer> range = InclusiveRange.of(5, 10);
            assertThat(range.includes(3)).isFalse();
        }

        @Test
        void includes_returnsFalseForValueAboveRange() {
            InclusiveRange<Integer> range = InclusiveRange.of(1, 10);
            assertThat(range.includes(15)).isFalse();
        }

        @Test
        void includes_worksWithSingleElementRange() {
            InclusiveRange<Integer> range = InclusiveRange.of(5, 5);
            assertThat(range.includes(5)).isTrue();
            assertThat(range.includes(4)).isFalse();
            assertThat(range.includes(6)).isFalse();
        }

        @Test
        void includes_worksWithNegativeNumbers() {
            InclusiveRange<Integer> range = InclusiveRange.of(-10, -1);
            assertThat(range.includes(-5)).isTrue();
            assertThat(range.includes(-10)).isTrue();
            assertThat(range.includes(-1)).isTrue();
            assertThat(range.includes(0)).isFalse();
        }

        @Test
        void includes_worksWithNegativeToPositiveRange() {
            InclusiveRange<Integer> range = InclusiveRange.of(-5, 5);
            assertThat(range.includes(0)).isTrue();
            assertThat(range.includes(-5)).isTrue();
            assertThat(range.includes(5)).isTrue();
            assertThat(range.includes(-6)).isFalse();
            assertThat(range.includes(6)).isFalse();
        }
    }

    @Nested
    class DaytimeRangeTests {

        @Test
        void of_createsValidRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(9, 0),
                Daytime.at(17, 0)
            );
            assertThat(range).isNotNull();
        }

        @Test
        void includes_returnsTrueForLowerBound() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(9, 0),
                Daytime.at(17, 0)
            );
            assertThat(range.includes(Daytime.at(9, 0))).isTrue();
        }

        @Test
        void includes_returnsTrueForUpperBound() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(9, 0),
                Daytime.at(17, 0)
            );
            assertThat(range.includes(Daytime.at(17, 0))).isTrue();
        }

        @Test
        void includes_returnsTrueForTimeInRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(9, 0),
                Daytime.at(17, 0)
            );
            assertThat(range.includes(Daytime.at(12, 30))).isTrue();
        }

        @Test
        void includes_returnsFalseForTimeBeforeRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(9, 0),
                Daytime.at(17, 0)
            );
            assertThat(range.includes(Daytime.at(8, 59))).isFalse();
        }

        @Test
        void includes_returnsFalseForTimeAfterRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(9, 0),
                Daytime.at(17, 0)
            );
            assertThat(range.includes(Daytime.at(17, 1))).isFalse();
        }

        @Test
        void includes_worksWithMorningRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(8, 0),
                Daytime.at(12, 0)
            );
            assertThat(range.includes(Daytime.at(10, 30))).isTrue();
            assertThat(range.includes(Daytime.at(7, 59))).isFalse();
            assertThat(range.includes(Daytime.at(12, 1))).isFalse();
        }

        @Test
        void includes_worksWithAfternoonRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(13, 0),
                Daytime.at(18, 0)
            );
            assertThat(range.includes(Daytime.at(15, 30))).isTrue();
            assertThat(range.includes(Daytime.at(12, 59))).isFalse();
            assertThat(range.includes(Daytime.at(18, 1))).isFalse();
        }

        @Test
        void includes_worksWithEveningRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(19, 0),
                Daytime.at(23, 0)
            );
            assertThat(range.includes(Daytime.at(21, 0))).isTrue();
            assertThat(range.includes(Daytime.at(18, 59))).isFalse();
            assertThat(range.includes(Daytime.at(23, 1))).isFalse();
        }

        @Test
        void includes_worksWithSingleMinuteRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(12, 0),
                Daytime.at(12, 0)
            );
            assertThat(range.includes(Daytime.at(12, 0))).isTrue();
            assertThat(range.includes(Daytime.at(11, 59))).isFalse();
            assertThat(range.includes(Daytime.at(12, 1))).isFalse();
        }

        @Test
        void includes_worksWithEarlyMorningRange() {
            InclusiveRange<Daytime> range = InclusiveRange.of(
                Daytime.at(0, 0),
                Daytime.at(6, 0)
            );
            assertThat(range.includes(Daytime.at(0, 0))).isTrue();
            assertThat(range.includes(Daytime.at(3, 30))).isTrue();
            assertThat(range.includes(Daytime.at(6, 0))).isTrue();
            assertThat(range.includes(Daytime.at(6, 1))).isFalse();
        }
    }
}
