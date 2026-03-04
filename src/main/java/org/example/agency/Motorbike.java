package org.example.agency;

/**
 * Représente une moto disponible à la location.
 * <p>
 * Une moto est caractérisée par sa marque, son modèle, son année de fabrication
 * et sa cylindrée en cm³. Le prix journalier est calculé ainsi :
 * <pre>
 * prix = 0.25 × cylindrée (cm³)
 * </pre>
 * </p>
 *
 * <p>Exemples :</p>
 * <pre>
 * Motorbike m = new Motorbike("Yamaha", "MT-07", 2021, 689);
 * m.dailyRentalPrice() → 172.25   // 689 * 0.25
 * m.toString() → "Motorbike Yamaha MT-07 2021 (689cm³) : 172.3€"
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see AbstractVehicle
 * @see Vehicle
 */
public class Motorbike extends AbstractVehicle {

    /**
     * La cylindrée du moteur en centimètres cubes (cm³).
     * Doit être supérieure ou égale à 50.
     */
    private final int cylinderCapacity;

    /**
     * Construit une moto avec ses caractéristiques.
     *
     * @param brand            la marque de la moto (ex : "Yamaha")
     * @param model            le modèle de la moto (ex : "MT-07")
     * @param productionYear   l'année de fabrication
     * @param cylinderCapacity la cylindrée en cm³ (≥ 50)
     * @throws IllegalArgumentException si l'année est strictement inférieure à 1900
     *                                  ou strictement supérieure à l'année courante,
     *                                  ou si la cylindrée est strictement inférieure à 50
     */
    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity) {
        super(brand, model, productionYear);
        if (cylinderCapacity < 50) {
            throw new IllegalArgumentException("Invalid cylinder capacity: " + cylinderCapacity);
        }
        this.cylinderCapacity = cylinderCapacity;
    }

    /**
     * Retourne la cylindrée du moteur en cm³.
     *
     * @return la cylindrée (≥ 50)
     */
    public int getCylinderCapacity() {
        return cylinderCapacity;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Calcul : {@code 0.25 × cylindrée (cm³)}
     * </p>
     * <p>Exemple :</p>
     * <pre>
     * cylindrée = 500 cm³ → 500 * 0.25 = 125.0€
     * cylindrée = 689 cm³ → 689 * 0.25 = 172.25€
     * </pre>
     */
    @Override
    public double dailyRentalPrice() {
        return 0.25 * cylinderCapacity;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code "Motorbike"}
     */
    @Override
    protected String getVehicleType() {
        return "Motorbike";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Exemple :
     * <pre>
     * cylindrée = 689 → "689cm³"
     * </pre>
     * </p>
     */
    @Override
    protected String getSpecificDetails() {
        return cylinderCapacity + "cm³";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Exemple :
     * <pre>
     * Motorbike Yamaha MT-07 2021 (689cm³) : 172.3€
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
