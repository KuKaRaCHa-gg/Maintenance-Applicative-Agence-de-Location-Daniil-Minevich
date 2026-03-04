package org.example.agency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("agency")
class UnknownVehicleExceptionTest {

    @Test
    void getMessage_shouldContainVehicleToString() {
        Car car = new Car("Toyota", "Corolla", 2020, 5);
        UnknownVehicleException ex = new UnknownVehicleException(car);

        String msg = ex.getMessage();
        assertThat(msg).contains("does not exist");
        assertThat(msg).contains(car.toString());
        assertThat(ex.getVehicle()).isSameAs(car);
    }
}

