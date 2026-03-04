package org.example.agency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Agence de location de véhicules.
 * <p>
 * Gère un inventaire de {@link Vehicle} et permet :
 * <ul>
 *     <li>L'ajout et la suppression de véhicules</li>
 *     <li>La recherche par critère ({@link Predicate})</li>
 *     <li>La location et le retour de véhicules par des {@link Client}s</li>
 * </ul>
 * </p>
 *
 * <p>Règles de location :</p>
 * <ul>
 *     <li>Un client ne peut louer qu'un seul véhicule à la fois.</li>
 *     <li>Un véhicule ne peut être loué que par un seul client à la fois.</li>
 *     <li>Un véhicule doit appartenir à l'agence pour être loué.</li>
 * </ul>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 * RentalAgency agency = new RentalAgency();
 * Car car = new Car("Toyota", "Corolla", 2023, 5);
 * agency.add(car);
 *
 * Client client = new Client("Jean", "Dupont", 1990);
 * double price = agency.rentVehicle(client, car); // 200.0€
 * agency.returnVehicle(client);
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see Vehicle
 * @see Client
 * @see UnknownVehicleException
 */
public class RentalAgency {

    /** Liste des véhicules disponibles dans l'agence. */
    private final List<Vehicle> vehicles;

    /**
     * Table associant chaque client au véhicule qu'il loue actuellement.
     * Un client n'est présent dans la table que s'il loue activement un véhicule.
     */
    private final Map<Client, Vehicle> rentedVehicles;

    /**
     * Construit une agence sans véhicule.
     */
    public RentalAgency() {
        this.vehicles = new ArrayList<>();
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Construit une agence avec une liste initiale de véhicules.
     *
     * @param vehicles la liste des véhicules à intégrer dans l'agence
     */
    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = new ArrayList<>(vehicles);
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Ajoute un véhicule à l'agence s'il n'y est pas déjà.
     *
     * @param vehicle le véhicule à ajouter
     * @return {@code true} si le véhicule a été ajouté, {@code false} s'il était déjà présent
     */
    public boolean add(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) {
            return false;
        }
        return vehicles.add(vehicle);
    }

    /**
     * Supprime un véhicule de l'agence.
     *
     * @param vehicle le véhicule à supprimer
     * @throws UnknownVehicleException si le véhicule n'est pas dans l'agence
     */
    public void remove(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        vehicles.remove(vehicle);
    }

    /**
     * Vérifie si un véhicule est présent dans l'agence.
     *
     * @param vehicle le véhicule à rechercher
     * @return {@code true} si le véhicule est dans l'agence, {@code false} sinon
     */
    public boolean contains(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * Retourne la liste des véhicules de l'agence.
     * <p>
     * La liste retournée est une copie défensive.
     * </p>
     *
     * @return une nouvelle liste contenant tous les véhicules de l'agence
     */
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    /**
     * Sélectionne les véhicules satisfaisant le critère donné.
     * <p>
     * Exemple :
     * <pre>
     * List&lt;Vehicle&gt; hondas = agency.select(new BrandCriterion("Honda"));
     * </pre>
     * </p>
     *
     * @param criterion le critère de sélection (prédicat sur {@link Vehicle})
     * @return la liste des véhicules satisfaisant le critère (peut être vide)
     */
    public List<Vehicle> select(Predicate<Vehicle> criterion) {
        List<Vehicle> selected = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (criterion.test(vehicle)) {
                selected.add(vehicle);
            }
        }
        return selected;
    }

    /**
     * Affiche sur la sortie standard les véhicules satisfaisant le critère donné.
     * <p>
     * Chaque véhicule est affiché via sa méthode {@link Vehicle#toString()}.
     * </p>
     *
     * @param criterion le critère de sélection
     */
    public void printSelectedVehicles(Predicate<Vehicle> criterion) {
        List<Vehicle> selected = select(criterion);
        for (Vehicle vehicle : selected) {
            System.out.println(vehicle);
        }
    }

    /**
     * Permet à un client de louer un véhicule de l'agence.
     * <p>
     * Préconditions :
     * <ul>
     *     <li>Le véhicule doit appartenir à l'agence.</li>
     *     <li>Le véhicule ne doit pas être déjà loué.</li>
     *     <li>Le client ne doit pas louer déjà un autre véhicule.</li>
     * </ul>
     * </p>
     *
     * @param client  le client qui souhaite louer
     * @param vehicle le véhicule à louer
     * @return le prix journalier de location en euros
     * @throws UnknownVehicleException si le véhicule n'existe pas dans l'agence
     * @throws IllegalStateException   si le véhicule est déjà loué,
     *                                 ou si le client loue déjà un autre véhicule
     */
    public double rentVehicle(Client client, Vehicle vehicle)
            throws UnknownVehicleException, IllegalStateException {
        if (!vehicles.contains(vehicle)) {
            throw new UnknownVehicleException(vehicle);
        }
        if (vehicleIsRented(vehicle)) {
            throw new IllegalStateException("Vehicle is already rented: " + vehicle.toString());
        }
        if (aVehicleIsRentedBy(client)) {
            throw new IllegalStateException("Client is already renting a vehicle: " + client.toString());
        }
        rentedVehicles.put(client, vehicle);
        return vehicle.dailyRentalPrice();
    }

    /**
     * Vérifie si un client loue actuellement un véhicule.
     *
     * @param client le client à vérifier
     * @return {@code true} si le client est en train de louer un véhicule, {@code false} sinon
     */
    public boolean aVehicleIsRentedBy(Client client) {
        return rentedVehicles.containsKey(client);
    }

    /**
     * Vérifie si un véhicule est actuellement loué.
     *
     * @param vehicle le véhicule à vérifier
     * @return {@code true} si le véhicule est loué, {@code false} sinon
     */
    public boolean vehicleIsRented(Vehicle vehicle) {
        return rentedVehicles.containsValue(vehicle);
    }

    /**
     * Enregistre le retour du véhicule loué par un client.
     * <p>
     * Si le client ne loue pas de véhicule, cette méthode ne fait rien.
     * </p>
     *
     * @param client le client qui rend son véhicule
     */
    public void returnVehicle(Client client) {
        rentedVehicles.remove(client);
    }

    /**
     * Retourne la collection de tous les véhicules actuellement loués.
     * <p>
     * La collection retournée est une copie défensive.
     * </p>
     *
     * @return une collection des véhicules en cours de location (peut être vide)
     */
    public Collection<Vehicle> allRentedVehicles() {
        return new ArrayList<>(rentedVehicles.values());
    }
}
