package org.example.agency;

/**
 * Exception levée lorsqu'on tente de supprimer ou de louer un véhicule
 * qui n'est pas référencé dans l'agence.
 * <p>
 * Cette exception est une {@link RuntimeException} non vérifiée.
 * Elle embarque le véhicule qui a causé l'erreur afin de faciliter
 * le diagnostic.
 * </p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 * RentalAgency agency = new RentalAgency();
 * Car car = new Car("Toyota", "Corolla", 2020, 5);
 * agency.remove(car); // → lève UnknownVehicleException
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see RentalAgency
 */
public class UnknownVehicleException extends RuntimeException {

    /** Le véhicule qui n'a pas été trouvé dans l'agence. */
    private final Vehicle vehicle;

    /**
     * Construit une exception pour un véhicule inconnu de l'agence.
     *
     * @param vehicle le véhicule qui n'existe pas dans l'agence
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
     * Retourne un message d'erreur indiquant que le véhicule n'existe pas dans l'agence.
     * <p>
     * Le message inclut la représentation textuelle du véhicule via {@link Vehicle#toString()}.
     * </p>
     *
     * @return le message d'erreur
     */
    @Override
    public String getMessage() {
        return "Vehicle does not exist in this rental agency: " + vehicle;
    }
}
