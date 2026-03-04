package org.example.agency;

import java.util.function.Predicate;

/**
 * Critère de sélection filtrant les véhicules par marque.
 * <p>
 * Implémente {@link Predicate}{@code <Vehicle>} et retourne {@code true}
 * uniquement si la marque du véhicule correspond exactement à la marque
 * configurée dans ce critère.
 * </p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 * BrandCriterion criterion = new BrandCriterion("Toyota");
 * criterion.test(new Car("Toyota", "Corolla", 2020, 5)) → true
 * criterion.test(new Car("Honda",  "Civic",   2020, 5)) → false
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see MaxPriceCriterion
 * @see RentalAgency#select(Predicate)
 */
public class BrandCriterion implements Predicate<Vehicle> {

    /** La marque recherchée (ex : "Toyota", "Honda"). */
    private final String brand;

    /**
     * Construit un critère de filtrage par marque.
     *
     * @param brand la marque à rechercher (sensible à la casse)
     */
    public BrandCriterion(String brand) {
        this.brand = brand;
    }

    /**
     * Teste si le véhicule correspond à la marque configurée.
     *
     * @param vehicle le véhicule à tester (peut être {@code null})
     * @return {@code true} si le véhicule n'est pas {@code null} et que sa marque
     *         est égale à {@link #brand}, {@code false} sinon
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle != null && brand.equals(vehicle.getBrand());
    }
}
