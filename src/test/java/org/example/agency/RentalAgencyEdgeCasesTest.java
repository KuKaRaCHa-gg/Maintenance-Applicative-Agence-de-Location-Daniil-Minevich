package org.example.agency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Tag("agency")
class RentalAgencyEdgeCasesTest {

    @Test
    void getVehicles_shouldReturnCopy_notAffectOriginal() {
        RentalAgency agency = new RentalAgency();
        Car car = new Car("Toyota", "Corolla", 2020, 5);
        agency.add(car);

        List<Vehicle> copy = agency.getVehicles();
        copy.clear();

        // original should remain unchanged
        assertThat(agency.getVehicles()).isNotEmpty();
    }

    @Test
    void allRentedVehicles_shouldReturnCopy_notAffectOriginal() {
        RentalAgency agency = new RentalAgency();
        Car car = new Car("Toyota", "Corolla", 2020, 5);
        Client client = new Client("Alice", "Dupont", 1990);
        agency.add(car);
        agency.rentVehicle(client, car);

        // obtain a copy and mutate it
        var rented = agency.allRentedVehicles();
        if (rented instanceof java.util.List) {
            ((java.util.List<Vehicle>) rented).clear();
        }

        // original mapping should remain unaffected
        assertThat(agency.vehicleIsRented(car)).isTrue();
        assertThat(agency.aVehicleIsRentedBy(client)).isTrue();
    }

    @Test
    void add_shouldBeIdempotent_whenAddingSameVehicle() {
        RentalAgency agency = new RentalAgency();
        Car car = new Car("Toyota", "Corolla", 2020, 5);

        boolean first = agency.add(car);
        boolean second = agency.add(car);

        assertThat(first).isTrue();
        assertThat(second).isFalse();
        assertThat(agency.getVehicles()).hasSize(1);
    }
}

