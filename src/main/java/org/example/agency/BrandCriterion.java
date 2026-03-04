package org.example.agency;

import java.util.function.Predicate;

/**
 * Critère filtrant les véhicules par marque.
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 */
public class BrandCriterion implements Predicate<Vehicle> {

    /** La marque recherchée. */
    private final String brand;

    /**
     * Construit un critère de filtrage par marque.
     *
     * @param brand la marque à rechercher
     */
    public BrandCriterion(String brand) {
        this.brand = brand;
    }

    /**
     * Teste si le véhicule correspond à la marque configurée.
     *
     * @param vehicle le véhicule à tester
     * @return {@code true} si la marque correspond
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle != null && brand.equals(vehicle.getBrand());
    }
}

