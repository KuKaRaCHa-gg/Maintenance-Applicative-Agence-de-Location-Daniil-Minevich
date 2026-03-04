package org.example.agency;

/**
 * Exception levée quand on tente d'accéder à un véhicule absent de l'agence.
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see RentalAgency
 */
public class UnknownVehicleException extends RuntimeException {

    /** Le véhicule introuvable. */
    private final Vehicle vehicle;

    /**
     * Construit l'exception pour un véhicule inconnu.
     *
     * @param vehicle le véhicule absent de l'agence
     */
    public UnknownVehicleException(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Retourne le véhicule à l'origine de l'exception.
     *
     * @return le véhicule inconnu
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Retourne le message d'erreur incluant la description du véhicule.
     *
     * @return le message d'erreur
     */
    @Override
    public String getMessage() {
        return "Vehicle does not exist in this rental agency: " + vehicle;
    }
}

