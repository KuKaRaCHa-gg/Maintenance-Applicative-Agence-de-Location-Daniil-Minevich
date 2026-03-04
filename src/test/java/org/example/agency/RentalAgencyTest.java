package org.example.agency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe RentalAgency.
 */
@Tag("agency")
class RentalAgencyTest {

    private Car car1;
    private Car car2;
    private Motorbike motorbike1;
    private Client client1;
    private Client client2;

    @BeforeEach
    void setUp() {
        car1 = new Car("Toyota", "Corolla", 2020, 5);
        car2 = new Car("Honda", "Civic", 2019, 4);
        motorbike1 = new Motorbike("Yamaha", "MT-07", 2021, 689);
        client1 = new Client("John", "Doe", 1990);
        client2 = new Client("Jane", "Smith", 1985);
    }

    @Test
    void constructor_shouldCreateEmptyAgency() {
        // When
        RentalAgency agency = new RentalAgency();

        // Then
        assertThat(agency.getVehicles()).isEmpty();
    }

    @Test
    void constructor_shouldCreateAgencyWithVehicles() {
        // Given
        List<Vehicle> vehicles = Arrays.asList(car1, car2);

        // When
        RentalAgency agency = new RentalAgency(vehicles);

        // Then
        assertThat(agency.getVehicles()).hasSize(2);
        assertThat(agency.contains(car1)).isTrue();
        assertThat(agency.contains(car2)).isTrue();
    }

    @Test
    void add_shouldAddVehicle_whenNotPresent() {
        // Given
        RentalAgency agency = new RentalAgency();

        // When
        boolean result = agency.add(car1);

        // Then
        assertThat(result).isTrue();
        assertThat(agency.contains(car1)).isTrue();
    }

    @Test
    void add_shouldNotAddVehicle_whenAlreadyPresent() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);

        // When
        boolean result = agency.add(car1);

        // Then
        assertThat(result).isFalse();
        assertThat(agency.getVehicles()).hasSize(1);
    }

    @Test
    void remove_shouldRemoveVehicle_whenPresent() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);

        // When
        agency.remove(car1);

        // Then
        assertThat(agency.contains(car1)).isFalse();
    }

    @Test
    void remove_shouldThrowException_whenVehicleNotPresent() {
        // Given
        RentalAgency agency = new RentalAgency();

        // When / Then
        assertThatThrownBy(() -> agency.remove(car1))
            .isInstanceOf(UnknownVehicleException.class)
            .hasMessageContaining("does not exist");
    }

    @Test
    void contains_shouldReturnTrue_whenVehiclePresent() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);

        // When
        boolean result = agency.contains(car1);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void contains_shouldReturnFalse_whenVehicleNotPresent() {
        // Given
        RentalAgency agency = new RentalAgency();

        // When
        boolean result = agency.contains(car1);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void select_shouldReturnMatchingVehicles() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.add(car2);
        agency.add(motorbike1);
        BrandCriterion criterion = new BrandCriterion("Toyota");

        // When
        List<Vehicle> result = agency.select(criterion);

        // Then
        assertThat(result).hasSize(1);
        assertThat(result).contains(car1);
    }

    @Test
    void select_shouldReturnEmptyList_whenNoMatch() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        BrandCriterion criterion = new BrandCriterion("BMW");

        // When
        List<Vehicle> result = agency.select(criterion);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void rentVehicle_shouldRentVehicle_whenAvailable() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);

        // When
        double price = agency.rentVehicle(client1, car1);

        // Then
        assertThat(price).isEqualTo(car1.dailyRentalPrice());
        assertThat(agency.vehicleIsRented(car1)).isTrue();
        assertThat(agency.aVehicleIsRentedBy(client1)).isTrue();
    }

    @Test
    void rentVehicle_shouldThrowException_whenVehicleNotInAgency() {
        // Given
        RentalAgency agency = new RentalAgency();

        // When / Then
        assertThatThrownBy(() -> agency.rentVehicle(client1, car1))
            .isInstanceOf(UnknownVehicleException.class);
    }

    @Test
    void rentVehicle_shouldThrowException_whenVehicleAlreadyRented() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.rentVehicle(client1, car1);

        // When / Then
        assertThatThrownBy(() -> agency.rentVehicle(client2, car1))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("already rented");
    }

    @Test
    void rentVehicle_shouldThrowException_whenClientAlreadyRentingVehicle() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.add(car2);
        agency.rentVehicle(client1, car1);

        // When / Then
        assertThatThrownBy(() -> agency.rentVehicle(client1, car2))
            .isInstanceOf(IllegalStateException.class)
            .hasMessageContaining("already renting");
    }

    @Test
    void aVehicleIsRentedBy_shouldReturnTrue_whenClientRenting() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.rentVehicle(client1, car1);

        // When
        boolean result = agency.aVehicleIsRentedBy(client1);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void aVehicleIsRentedBy_shouldReturnFalse_whenClientNotRenting() {
        // Given
        RentalAgency agency = new RentalAgency();

        // When
        boolean result = agency.aVehicleIsRentedBy(client1);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void vehicleIsRented_shouldReturnTrue_whenVehicleRented() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.rentVehicle(client1, car1);

        // When
        boolean result = agency.vehicleIsRented(car1);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void vehicleIsRented_shouldReturnFalse_whenVehicleNotRented() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);

        // When
        boolean result = agency.vehicleIsRented(car1);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void returnVehicle_shouldReturnVehicle() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.rentVehicle(client1, car1);

        // When
        agency.returnVehicle(client1);

        // Then
        assertThat(agency.vehicleIsRented(car1)).isFalse();
        assertThat(agency.aVehicleIsRentedBy(client1)).isFalse();
    }

    @Test
    void returnVehicle_shouldDoNothing_whenClientNotRenting() {
        // Given
        RentalAgency agency = new RentalAgency();

        // When / Then
        assertThatCode(() -> agency.returnVehicle(client1))
            .doesNotThrowAnyException();
    }

    @Test
    void allRentedVehicles_shouldReturnAllRentedVehicles() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);
        agency.add(car2);
        agency.add(motorbike1);
        agency.rentVehicle(client1, car1);
        agency.rentVehicle(client2, motorbike1);

        // When
        Collection<Vehicle> rented = agency.allRentedVehicles();

        // Then
        assertThat(rented).hasSize(2);
        assertThat(rented).contains(car1, motorbike1);
        assertThat(rented).doesNotContain(car2);
    }

    @Test
    void allRentedVehicles_shouldReturnEmptyCollection_whenNoRentals() {
        // Given
        RentalAgency agency = new RentalAgency();
        agency.add(car1);

        // When
        Collection<Vehicle> rented = agency.allRentedVehicles();

        // Then
        assertThat(rented).isEmpty();
    }
}

