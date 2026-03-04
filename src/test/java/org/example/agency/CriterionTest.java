package org.example.agency;

import org.example.util.TimeProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests unitaires pour les critères de sélection.
 */
@Tag("agency")
class CriterionTest {

    private Car toyotaCar;
    private Car hondaCar;
    private Car expensiveCar;
    private Motorbike motorbike;

    @BeforeEach
    void setUp() {
        int currentYear = TimeProvider.currentYearValue();
        // voiture récente (2 ans) : 5 * 40 = 200€
        toyotaCar = new Car("Toyota", "Corolla", currentYear - 2, 5);
        // voiture récente (3 ans) : 4 * 40 = 160€
        hondaCar = new Car("Honda", "Civic", currentYear - 3, 4);
        // voiture récente (1 an) : 7 * 40 = 280€
        expensiveCar = new Car("Mercedes", "S-Class", currentYear - 1, 7);
        // moto : 689 * 0.25 = 172.25€
        motorbike = new Motorbike("Yamaha", "MT-07", currentYear - 3, 689);
    }

    @Test
    void brandCriterion_shouldReturnTrue_whenBrandMatches() {
        // Given
        BrandCriterion criterion = new BrandCriterion("Toyota");

        // When
        boolean result = criterion.test(toyotaCar);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void brandCriterion_shouldReturnFalse_whenBrandDoesNotMatch() {
        // Given
        BrandCriterion criterion = new BrandCriterion("Toyota");

        // When
        boolean result = criterion.test(hondaCar);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void brandCriterion_shouldWorkWithMotorbikes() {
        // Given
        BrandCriterion criterion = new BrandCriterion("Yamaha");

        // When
        boolean result = criterion.test(motorbike);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void maxPriceCriterion_shouldReturnTrue_whenPriceIsLower() {
        // Given
        MaxPriceCriterion criterion = new MaxPriceCriterion(200.0);

        // When
        boolean result = criterion.test(motorbike); // 172.25€

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void maxPriceCriterion_shouldReturnTrue_whenPriceIsEqual() {
        // Given
        MaxPriceCriterion criterion = new MaxPriceCriterion(172.25);

        // When
        boolean result = criterion.test(motorbike); // 172.25€

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void maxPriceCriterion_shouldReturnFalse_whenPriceIsHigher() {
        // Given
        MaxPriceCriterion criterion = new MaxPriceCriterion(100.0);

        // When
        boolean result = criterion.test(toyotaCar); // 200€

        // Then
        assertThat(result).isFalse();
    }

    @Test
    void maxPriceCriterion_shouldWorkWithExpensiveCars() {
        // Given
        MaxPriceCriterion criterion = new MaxPriceCriterion(300.0);

        // When
        boolean result = criterion.test(expensiveCar); // 280€ (7 seats * 40€)

        // Then
        assertThat(result).isTrue();
    }
}

