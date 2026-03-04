package org.example.agency;

import org.example.util.TimeProvider;

/**
 * Représente une voiture disponible à la location.
 * <p>
 * Une voiture est caractérisée par sa marque, son modèle, son année de fabrication
 * et son nombre de sièges. Le prix journalier dépend de l'ancienneté du véhicule :
 * <ul>
 *     <li>Voiture récente (≤ 5 ans) : {@code 40€ × nombre de sièges}</li>
 *     <li>Voiture ancienne (&gt; 5 ans) : {@code 20€ × nombre de sièges}</li>
 * </ul>
 * </p>
 *
 * <p>Exemples :</p>
 * <pre>
 * Car toyota = new Car("Toyota", "Corolla", 2023, 5);
 * toyota.dailyRentalPrice() → 200.0  // voiture récente : 5 * 40
 * toyota.toString() → "Car Toyota Corolla 2023 (5 seats) : 200.0€"
 *
 * Car old = new Car("Renault", "Clio", 2010, 4);
 * old.dailyRentalPrice() → 80.0     // vieux modèle : 4 * 20
 * old.toString() → "Car Renault Clio 2010 (4 seats) : 80.0€"
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see AbstractVehicle
 * @see Vehicle
 */
public class Car extends AbstractVehicle {

    /**
     * Le nombre de sièges de la voiture.
     * Doit être supérieur ou égal à 1.
     */
    private final int numberOfSeats;

    /**
     * Construit une voiture avec ses caractéristiques.
     *
     * @param brand          la marque de la voiture (ex : "Toyota")
     * @param model          le modèle de la voiture (ex : "Corolla")
     * @param productionYear l'année de fabrication
     * @param numberOfSeats  le nombre de sièges (≥ 1)
     * @throws IllegalArgumentException si l'année est strictement inférieure à 1900
     *                                  ou strictement supérieure à l'année courante,
     *                                  ou si le nombre de sièges est strictement inférieur à 1
     */
    public Car(String brand, String model, int productionYear, int numberOfSeats) {
        super(brand, model, productionYear);
        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Invalid number of seats: " + numberOfSeats);
        }
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Retourne le nombre de sièges de la voiture.
     *
     * @return le nombre de sièges (≥ 1)
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Indique si la voiture est considérée comme récente.
     * <p>
     * Une voiture est récente si elle a 5 ans ou moins par rapport à l'année courante.
     * </p>
     * <p>Exemples (en 2026) :</p>
     * <pre>
     * 2021 → isNew() = true   (5 ans)
     * 2020 → isNew() = false  (6 ans)
     * </pre>
     *
     * @return {@code true} si la voiture a 5 ans ou moins, {@code false} sinon
     */
    public boolean isNew() {
        return (TimeProvider.currentYearValue() - productionYear) <= 5;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Calcul :
     * <ul>
     *     <li>Récente (≤ 5 ans) : {@code 40.0 × numberOfSeats}</li>
     *     <li>Ancienne (&gt; 5 ans) : {@code 20.0 × numberOfSeats}</li>
     * </ul>
     * </p>
     */
    @Override
    public double dailyRentalPrice() {
        return isNew() ? 40.0 * numberOfSeats : 20.0 * numberOfSeats;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code "Car"}
     */
    @Override
    protected String getVehicleType() {
        return "Car";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Exemples :
     * <pre>
     * 1 siège  → "1 seat"
     * 5 sièges → "5 seats"
     * </pre>
     * </p>
     */
    @Override
    protected String getSpecificDetails() {
        return numberOfSeats == 1 ? "1 seat" : numberOfSeats + " seats";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Exemple :
     * <pre>
     * Car Toyota Corolla 2023 (5 seats) : 200.0€
     * </pre>
     * </p>
     */
    @Override
    public String toString() {
        return String.format("%s %s %s %d (%s) : %.1f€",
            getVehicleType(), brand, model, productionYear,
            getSpecificDetails(), dailyRentalPrice());
    }
}
