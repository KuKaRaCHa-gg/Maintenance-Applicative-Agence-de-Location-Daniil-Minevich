package org.example.agency;

import org.example.util.TimeProvider;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Car.
 */
@Tag("agency")
class CarTest {

    @Test
    void constructor_shouldCreateCarWithValidParameters() {
        // Given / When
        Car car = new Car("Toyota", "Corolla", 2020, 5);

        // Then
        assertThat(car.getBrand()).isEqualTo("Toyota");
        assertThat(car.getModel()).isEqualTo("Corolla");
        assertThat(car.getProductionYear()).isEqualTo(2020);
        assertThat(car.getNumberOfSeats()).isEqualTo(5);
    }

    @Test
    void constructor_shouldThrowException_whenYearTooOld() {
        // When / Then
        assertThatThrownBy(() -> new Car("Toyota", "Corolla", 1899, 5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1899");
    }

    @Test
    void constructor_shouldThrowException_whenYearInFuture() {
        // Given
        int futureYear = TimeProvider.currentYearValue() + 1;

        // When / Then
        assertThatThrownBy(() -> new Car("Toyota", "Corolla", futureYear, 5))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.valueOf(futureYear));
    }

    @Test
    void constructor_shouldThrowException_whenSeatsLessThanOne() {
        // When / Then
        assertThatThrownBy(() -> new Car("Toyota", "Corolla", 2020, 0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("0");
    }

    @Test
    void constructor_shouldAcceptCurrentYear() {
        // Given
        int currentYear = TimeProvider.currentYearValue();

        // When / Then
        assertThatCode(() -> new Car("Toyota", "Corolla", currentYear, 5))
            .doesNotThrowAnyException();
    }

    @Test
    void isNew_shouldReturnTrue_forRecentCar() {
        // Given
        int recentYear = TimeProvider.currentYearValue() - 3;
        Car car = new Car("Toyota", "Corolla", recentYear, 5);

        // When
        boolean isNew = car.isNew();

        // Then
        assertThat(isNew).isTrue();
    }

    @Test
    void isNew_shouldReturnTrue_forCarExactlyFiveYearsOld() {
        // Given
        int fiveYearsAgo = TimeProvider.currentYearValue() - 5;
        Car car = new Car("Toyota", "Corolla", fiveYearsAgo, 5);

        // When
        boolean isNew = car.isNew();

        // Then
        assertThat(isNew).isTrue();
    }

    @Test
    void isNew_shouldReturnFalse_forOldCar() {
        // Given
        int oldYear = TimeProvider.currentYearValue() - 10;
        Car car = new Car("Toyota", "Corolla", oldYear, 5);

        // When
        boolean isNew = car.isNew();

        // Then
        assertThat(isNew).isFalse();
    }

    @Test
    void dailyRentalPrice_shouldBe40PerSeat_forNewCar() {
        // Given
        int recentYear = TimeProvider.currentYearValue() - 2;
        Car car = new Car("Toyota", "Corolla", recentYear, 5);

        // When
        double price = car.dailyRentalPrice();

        // Then
        assertThat(price).isEqualTo(200.0); // 5 seats * 40€
    }

    @Test
    void dailyRentalPrice_shouldBe20PerSeat_forOldCar() {
        // Given
        int oldYear = TimeProvider.currentYearValue() - 10;
        Car car = new Car("Toyota", "Corolla", oldYear, 5);

        // When
        double price = car.dailyRentalPrice();

        // Then
        assertThat(price).isEqualTo(100.0); // 5 seats * 20€
    }

    @Test
    void equals_shouldReturnTrue_forSameCars() {
        // Given
        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Toyota", "Corolla", 2020, 4);

        // When / Then
        assertThat(car1).isEqualTo(car2);
    }

    @Test
    void equals_shouldReturnFalse_forDifferentBrands() {
        // Given
        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Honda", "Corolla", 2020, 5);

        // When / Then
        assertThat(car1).isNotEqualTo(car2);
    }

    @Test
    void equals_shouldReturnFalse_forDifferentModels() {
        // Given
        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Toyota", "Camry", 2020, 5);

        // When / Then
        assertThat(car1).isNotEqualTo(car2);
    }

    @Test
    void equals_shouldReturnFalse_forDifferentYears() {
        // Given
        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Toyota", "Corolla", 2021, 5);

        // When / Then
        assertThat(car1).isNotEqualTo(car2);
    }

    @Test
    void equals_shouldReturnFalse_whenComparedToMotorbike() {
        // Given
        Car car = new Car("Toyota", "Corolla", 2020, 5);
        Motorbike motorbike = new Motorbike("Toyota", "Corolla", 2020, 500);

        // When / Then
        assertThat(car).isNotEqualTo(motorbike);
    }

    @Test
    void toString_shouldContainCarType() {
        // Given
        Car car = new Car("Toyota", "Corolla", 2020, 5);

        // When
        String result = car.toString();

        // Then
        assertThat(result).startsWith("Car");
    }

    @Test
    void toString_shouldContainBrandModelYear() {
        // Given
        Car car = new Car("Toyota", "Corolla", 2020, 5);

        // When
        String result = car.toString();

        // Then
        assertThat(result)
            .contains("Toyota")
            .contains("Corolla")
            .contains("2020");
    }

    @Test
    void toString_shouldContainSeatsInfo_singular() {
        // Given
        Car car = new Car("Toyota", "Corolla", 2020, 1);

        // When
        String result = car.toString();

        // Then
        assertThat(result).contains("1 seat");
    }

    @Test
    void toString_shouldContainSeatsInfo_plural() {
        // Given
        Car car = new Car("Toyota", "Corolla", 2020, 5);

        // When
        String result = car.toString();

        // Then
        assertThat(result).contains("5 seats");
    }

    @Test
    void toString_shouldContainPrice() {
        // Given
        Car car = new Car("Toyota", "Corolla", 2020, 5);

        // When
        String result = car.toString();

        // Then
        assertThat(result).contains("€");
    }
}

