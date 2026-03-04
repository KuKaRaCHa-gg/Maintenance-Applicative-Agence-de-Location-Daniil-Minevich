package org.example.agency;

/**
 * Représente une moto disponible à la location.
 * <p>
 * Le prix journalier est calculé ainsi : {@code 0.25 × cylindrée (cm³)}
 * </p>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see AbstractVehicle
 * @see Vehicle
 */
public class Motorbike extends AbstractVehicle {

    /** La cylindrée du moteur en cm³ (≥ 50). */
    private final int cylinderCapacity;

    /**
     * Construit une moto avec ses caractéristiques.
     *
     * @param brand            la marque
     * @param model            le modèle
     * @param productionYear   l'année de fabrication
     * @param cylinderCapacity la cylindrée en cm³ (≥ 50)
     * @throws IllegalArgumentException si l'année ou la cylindrée est invalide
     */
    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity) {
        super(brand, model, productionYear);
        if (cylinderCapacity < 50) {
            throw new IllegalArgumentException("Invalid cylinder capacity: " + cylinderCapacity);
        }
        this.cylinderCapacity = cylinderCapacity;
    }

    /**
     * Retourne la cylindrée en cm³.
     *
     * @return la cylindrée (≥ 50)
     */
    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    /** {@inheritDoc} */
    @Override
    public double dailyRentalPrice() {
        return 0.25 * cylinderCapacity;
    }

    /** {@inheritDoc} */
    @Override
    protected String getVehicleType() {
        return "Motorbike";
    }

    /** {@inheritDoc} */
    @Override
    protected String getSpecificDetails() {
        return cylinderCapacity + "cm\u00b3";
    }

    /**
     * {@inheritDoc}
     * <p>Exemple : {@code Motorbike Yamaha MT-07 2021 (689cm³) : 172.3€}</p>
     */
    @Override
    public String toString() {
        return String.format("%s %s %s %d (%s) : %.1f\u20ac",
            getVehicleType(), brand, model, productionYear,
            getSpecificDetails(), dailyRentalPrice());
    }
}

