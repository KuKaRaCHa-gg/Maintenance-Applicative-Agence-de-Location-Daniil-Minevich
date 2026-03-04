package org.example.agency;

import java.util.function.Predicate;

/**
 * Critère de sélection filtrant les véhicules dont le prix journalier
 * est inférieur ou égal à un montant maximum.
 * <p>
 * Implémente {@link Predicate}{@code <Vehicle>} et retourne {@code true}
 * si le prix de location du véhicule est inférieur ou égal à {@code maxPrice}.
 * </p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 * MaxPriceCriterion criterion = new MaxPriceCriterion(150.0);
 * // Moto 500cm³ → 125.0€ → true
 * criterion.test(new Motorbike("Honda", "CB500", 2020, 500)) → true
 * // Voiture récente 5 places → 200.0€ → false
 * criterion.test(new Car("Toyota", "Corolla", 2024, 5))      → false
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see BrandCriterion
 * @see RentalAgency#select(Predicate)
 */
public class MaxPriceCriterion implements Predicate<Vehicle> {

    /** Le prix journalier maximum autorisé (en euros). */
    private final double maxPrice;

    /**
     * Construit un critère de filtrage par prix maximum.
     *
     * @param maxPrice le prix journalier maximum en euros (inclusif)
     */
    public MaxPriceCriterion(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Teste si le prix journalier du véhicule est dans la limite autorisée.
     *
     * @param vehicle le véhicule à tester (peut être {@code null})
     * @return {@code true} si le véhicule n'est pas {@code null} et que son prix
     *         est inférieur ou égal à {@link #maxPrice}, {@code false} sinon
     */
    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle != null && vehicle.dailyRentalPrice() <= maxPrice;
    }
}
