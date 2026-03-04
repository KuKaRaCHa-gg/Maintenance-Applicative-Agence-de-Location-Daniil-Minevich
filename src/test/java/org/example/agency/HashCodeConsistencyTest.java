package org.example.agency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("agency")
class HashCodeConsistencyTest {

    @Test
    void equalCars_shouldHaveSameHashCode() {
        Car c1 = new Car("Toyota", "Corolla", 2020, 5);
        Car c2 = new Car("Toyota", "Corolla", 2020, 4);

        assertThat(c1).isEqualTo(c2);
        assertThat(c1.hashCode()).isEqualTo(c2.hashCode());
    }

    @Test
    void equalMotorbikes_shouldHaveSameHashCode() {
        Motorbike m1 = new Motorbike("Yamaha", "MT-07", 2020, 689);
        Motorbike m2 = new Motorbike("Yamaha", "MT-07", 2020, 500);

        assertThat(m1).isEqualTo(m2);
        assertThat(m1.hashCode()).isEqualTo(m2.hashCode());
    }
}

