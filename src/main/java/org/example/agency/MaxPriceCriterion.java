package org.example.agency;

import java.util.function.Predicate;

/**
 * Critère filtrant les véhicules par prix maximum journalier.
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 */
public class MaxPriceCriterion implements Predicate<Vehicle> {

    /** Le prix journalier maximum autorisé (en euros). */
    private final double maxPrice;

    /**
     * Construit un critère de filtrage par prix maximum.
     *
     * @param maxPrice le prix maximum en euros (inclusif)
     */
    public MaxPriceCriterion(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Teste si le prix du véhicule est dans la limite autorisée.
     *
     * @param vehicle le véhicule à tester
     * @return {@code true} si le prix est inférieur ou égal à maxPrice
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle != null && vehicle.dailyRentalPrice() <= maxPrice;
    }
}

