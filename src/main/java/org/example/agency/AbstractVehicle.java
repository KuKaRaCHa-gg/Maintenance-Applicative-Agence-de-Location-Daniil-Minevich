package org.example.agency;

import org.example.util.TimeProvider;

/**
 * Classe abstraite factorisant le comportement commun des véhicules de l'agence.
 * <p>
 * Toute implémentation concrète ({@link Car}, {@link Motorbike}) doit étendre
 * cette classe et fournir :
 * <ul>
 *     <li>Le type du véhicule via {@link #getVehicleType()}</li>
 *     <li>Les détails spécifiques via {@link #getSpecificDetails()}</li>
 *     <li>Le calcul du prix via {@link #dailyRentalPrice()}</li>
 * </ul>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see Car
 * @see Motorbike
 */
public abstract class AbstractVehicle implements Vehicle {

    /** La marque du véhicule (ex : "Toyota", "Yamaha"). */
    protected final String brand;

    /** Le modèle du véhicule (ex : "Corolla", "MT-07"). */
    protected final String model;

    /**
     * L'année de fabrication du véhicule.
     * Doit être comprise entre 1900 et l'année courante incluse.
     */
    protected final int productionYear;

    /**
     * Construit un véhicule avec ses informations de base.
     *
     * @param brand          la marque du véhicule
     * @param model          le modèle du véhicule
     * @param productionYear l'année de fabrication
     * @throws IllegalArgumentException si l'année est strictement inférieure à 1900
     *                                  ou strictement supérieure à l'année courante
     */
    protected AbstractVehicle(String brand, String model, int productionYear) {
        if (productionYear < 1900 || productionYear > TimeProvider.currentYearValue()) {
            throw new IllegalArgumentException("Invalid production year: " + productionYear);
        }
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBrand() {
        return brand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getModel() {
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getProductionYear() {
        return productionYear;
    }

    /**
     * Teste l'égalité entre ce véhicule et un autre objet.
     * <p>
     * Deux véhicules sont considérés égaux s'ils sont de la même classe
     * et partagent la même marque, le même modèle et la même année de fabrication.
     * </p>
     *
     * @param o l'objet à comparer
     * @return {@code true} si les deux véhicules sont égaux, {@code false} sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVehicle that = (AbstractVehicle) o;
        if (productionYear != that.productionYear) return false;
        if (!brand.equals(that.brand)) return false;
        return model.equals(that.model);
    }

    /**
     * Retourne un code de hachage basé sur la marque, le modèle et l'année.
     *
     * @return le code de hachage du véhicule
     */
    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + productionYear;
        return result;
    }

    /**
     * Retourne le type du véhicule sous forme de chaîne.
     * <p>
     * Exemples : {@code "Car"}, {@code "Motorbike"}.
     * </p>
     *
     * @return le type du véhicule
     */
    protected abstract String getVehicleType();

    /**
     * Retourne les détails spécifiques au type de véhicule,
     * destinés à être affichés entre parenthèses dans {@link #toString()}.
     * <p>
     * Exemples :
     * <pre>
     * Car      → "5 seats" ou "1 seat"
     * Motorbike → "689cm³"
     * </pre>
     * </p>
     *
     * @return les détails spécifiques du véhicule
     */
    protected abstract String getSpecificDetails();
}
