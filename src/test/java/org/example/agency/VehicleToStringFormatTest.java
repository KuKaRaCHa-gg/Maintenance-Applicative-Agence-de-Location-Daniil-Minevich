package org.example.agency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("agency")
class VehicleToStringFormatTest {

    @Test
    void carToString_format() {
        Car car = new Car("Toyota", "Corolla", 2020, 3);
        String s = car.toString();

        assertThat(s).startsWith("Car ");
        assertThat(s).contains("Toyota");
        assertThat(s).contains("Corolla");
        assertThat(s).contains("(3 seats)");
        assertThat(s).contains("€");
    }

    @Test
    void motorbikeToString_format() {
        Motorbike m = new Motorbike("Yamaha", "MT-07", 2020, 689);
        String s = m.toString();

        assertThat(s).startsWith("Motorbike ");
        assertThat(s).contains("689cm³");
        assertThat(s).contains("€");
    }
}

