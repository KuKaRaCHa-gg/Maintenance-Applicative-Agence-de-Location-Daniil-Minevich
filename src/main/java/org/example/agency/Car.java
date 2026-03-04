package org.example.agency;

import org.example.util.TimeProvider;

/**
 * Représente une voiture disponible à la location.
 * <p>
 * Le prix journalier dépend de l'ancienneté :
 * <ul>
 *     <li>Récente (≤ 5 ans) : {@code 40€ × nombre de sièges}</li>
 *     <li>Ancienne (&gt; 5 ans) : {@code 20€ × nombre de sièges}</li>
 * </ul>
 * </p>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see AbstractVehicle
 * @see Vehicle
 */
public class Car extends AbstractVehicle {

    /** Le nombre de sièges de la voiture (≥ 1). */
    private final int numberOfSeats;

    /**
     * Construit une voiture avec ses caractéristiques.
     *
     * @param brand          la marque
     * @param model          le modèle
     * @param productionYear l'année de fabrication
     * @param numberOfSeats  le nombre de sièges (≥ 1)
     * @throws IllegalArgumentException si l'année ou le nombre de sièges est invalide
     */
    public Car(String brand, String model, int productionYear, int numberOfSeats) {
        super(brand, model, productionYear);
        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Invalid number of seats: " + numberOfSeats);
        }
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Retourne le nombre de sièges.
     *
     * @return le nombre de sièges (≥ 1)
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Indique si la voiture est récente (5 ans ou moins).
     *
     * @return {@code true} si la voiture a 5 ans ou moins
     */
    public boolean isNew() {
        return (TimeProvider.currentYearValue() - productionYear) <= 5;
    }

    /** {@inheritDoc} */
    @Override
    public double dailyRentalPrice() {
        return isNew() ? 40.0 * numberOfSeats : 20.0 * numberOfSeats;
    }

    /** {@inheritDoc} */
    @Override
    protected String getVehicleType() {
        return "Car";
    }

    /** {@inheritDoc} */
    @Override
    protected String getSpecificDetails() {
        return numberOfSeats == 1 ? "1 seat" : numberOfSeats + " seats";
    }

    /**
     * {@inheritDoc}
     * <p>Exemple : {@code Car Toyota Corolla 2023 (5 seats) : 200.0€}</p>
     */
    @Override
    public String toString() {
        return String.format("%s %s %s %d (%s) : %.1f\u20ac",
            getVehicleType(), brand, model, productionYear,
            getSpecificDetails(), dailyRentalPrice());
    }
}

