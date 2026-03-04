package org.example.agency;

import org.example.util.TimeProvider;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests unitaires pour la classe Motorbike.
 */
@Tag("agency")
class MotorbikeTest {

    @Test
    void constructor_shouldCreateMotorbikeWithValidParameters() {
        // Given / When
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // Then
        assertThat(motorbike.getBrand()).isEqualTo("Yamaha");
        assertThat(motorbike.getModel()).isEqualTo("MT-07");
        assertThat(motorbike.getProductionYear()).isEqualTo(2020);
        assertThat(motorbike.getCylinderCapacity()).isEqualTo(689);
    }

    @Test
    void constructor_shouldThrowException_whenYearTooOld() {
        // When / Then
        assertThatThrownBy(() -> new Motorbike("Yamaha", "MT-07", 1899, 689))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1899");
    }

    @Test
    void constructor_shouldThrowException_whenYearInFuture() {
        // Given
        int futureYear = TimeProvider.currentYearValue() + 1;

        // When / Then
        assertThatThrownBy(() -> new Motorbike("Yamaha", "MT-07", futureYear, 689))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.valueOf(futureYear));
    }

    @Test
    void constructor_shouldThrowException_whenCylinderCapacityTooLow() {
        // When / Then
        assertThatThrownBy(() -> new Motorbike("Yamaha", "MT-07", 2020, 49))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("49");
    }

    @Test
    void constructor_shouldAcceptMinimumCylinderCapacity() {
        // When / Then
        assertThatCode(() -> new Motorbike("Yamaha", "MT-07", 2020, 50))
            .doesNotThrowAnyException();
    }

    @Test
    void dailyRentalPrice_shouldBe025PerCm3() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // When
        double price = motorbike.dailyRentalPrice();

        // Then
        assertThat(price).isEqualTo(172.25); // 689 * 0.25
    }

    @Test
    void dailyRentalPrice_shouldBe12Point5_for50Cm3() {
        // Given
        Motorbike motorbike = new Motorbike("Honda", "Cub", 2020, 50);

        // When
        double price = motorbike.dailyRentalPrice();

        // Then
        assertThat(price).isEqualTo(12.5); // 50 * 0.25
    }

    @Test
    void equals_shouldReturnTrue_forSameMotorbikes() {
        // Given
        Motorbike motorbike1 = new Motorbike("Yamaha", "MT-07", 2020, 689);
        Motorbike motorbike2 = new Motorbike("Yamaha", "MT-07", 2020, 500);

        // When / Then
        assertThat(motorbike1).isEqualTo(motorbike2);
    }

    @Test
    void equals_shouldReturnFalse_forDifferentBrands() {
        // Given
        Motorbike motorbike1 = new Motorbike("Yamaha", "MT-07", 2020, 689);
        Motorbike motorbike2 = new Motorbike("Honda", "MT-07", 2020, 689);

        // When / Then
        assertThat(motorbike1).isNotEqualTo(motorbike2);
    }

    @Test
    void equals_shouldReturnFalse_forDifferentModels() {
        // Given
        Motorbike motorbike1 = new Motorbike("Yamaha", "MT-07", 2020, 689);
        Motorbike motorbike2 = new Motorbike("Yamaha", "MT-09", 2020, 689);

        // When / Then
        assertThat(motorbike1).isNotEqualTo(motorbike2);
    }

    @Test
    void equals_shouldReturnFalse_forDifferentYears() {
        // Given
        Motorbike motorbike1 = new Motorbike("Yamaha", "MT-07", 2020, 689);
        Motorbike motorbike2 = new Motorbike("Yamaha", "MT-07", 2021, 689);

        // When / Then
        assertThat(motorbike1).isNotEqualTo(motorbike2);
    }

    @Test
    void equals_shouldReturnFalse_whenComparedToCar() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);
        Car car = new Car("Yamaha", "MT-07", 2020, 5);

        // When / Then
        assertThat(motorbike).isNotEqualTo(car);
    }

    @Test
    void toString_shouldContainMotorbikeType() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // When
        String result = motorbike.toString();

        // Then
        assertThat(result).startsWith("Motorbike");
    }

    @Test
    void toString_shouldContainBrandModelYear() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // When
        String result = motorbike.toString();

        // Then
        assertThat(result)
            .contains("Yamaha")
            .contains("MT-07")
            .contains("2020");
    }

    @Test
    void toString_shouldContainCylinderCapacity() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // When
        String result = motorbike.toString();

        // Then
        assertThat(result).contains("689cm³");
    }

    @Test
    void toString_shouldContainPrice() {
        // Given
        Motorbike motorbike = new Motorbike("Yamaha", "MT-07", 2020, 689);

        // When
        String result = motorbike.toString();

        // Then
        assertThat(result).contains("€");
    }
}

