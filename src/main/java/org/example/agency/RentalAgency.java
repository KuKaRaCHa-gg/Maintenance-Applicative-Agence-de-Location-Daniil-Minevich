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
 * Gère un inventaire de {@link Vehicle} et permet la location par des {@link Client}s.
 * </p>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see Vehicle
 * @see Client
 * @see UnknownVehicleException
 */
public class RentalAgency {

    /** Liste des véhicules disponibles. */
    private final List<Vehicle> vehicles;

    /** Associations client → véhicule loué. */
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
     * @param vehicles la liste des véhicules initiaux
     */
    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = new ArrayList<>(vehicles);
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Ajoute un véhicule s'il n'est pas déjà présent.
     *
     * @param vehicle le véhicule à ajouter
     * @return {@code true} si ajouté, {@code false} s'il était déjà présent
     */
    public boolean add(Vehicle vehicle) {
        if (vehicles.contains(vehicle)) return false;
        return vehicles.add(vehicle);
    }

    /**
     * Supprime un véhicule de l'agence.
     *
     * @param vehicle le véhicule à supprimer
     * @throws UnknownVehicleException si le véhicule n'est pas dans l'agence
     */
    public void remove(Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) throw new UnknownVehicleException(vehicle);
        vehicles.remove(vehicle);
    }

    /**
     * Vérifie si un véhicule est présent dans l'agence.
     *
     * @param vehicle le véhicule à rechercher
     * @return {@code true} si présent
     */
    public boolean contains(Vehicle vehicle) {
        return vehicles.contains(vehicle);
    }

    /**
     * Retourne une copie de la liste des véhicules.
     *
     * @return la liste des véhicules
     */
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    /**
     * Sélectionne les véhicules satisfaisant le critère donné.
     *
     * @param criterion le critère de sélection
     * @return la liste des véhicules correspondants
     */
    public List<Vehicle> select(Predicate<Vehicle> criterion) {
        List<Vehicle> selected = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (criterion.test(vehicle)) selected.add(vehicle);
        }
        return selected;
    }

    /**
     * Affiche les véhicules satisfaisant le critère.
     *
     * @param criterion le critère de sélection
     */
    public void printSelectedVehicles(Predicate<Vehicle> criterion) {
        for (Vehicle vehicle : select(criterion)) {
            System.out.println(vehicle);
        }
    }

    /**
     * Loue un véhicule à un client.
     *
     * @param client  le client
     * @param vehicle le véhicule à louer
     * @return le prix journalier
     * @throws UnknownVehicleException si le véhicule n'est pas dans l'agence
     * @throws IllegalStateException   si le véhicule est déjà loué ou le client loue déjà
     */
    public double rentVehicle(Client client, Vehicle vehicle) {
        if (!vehicles.contains(vehicle)) throw new UnknownVehicleException(vehicle);
        if (vehicleIsRented(vehicle)) throw new IllegalStateException("Vehicle already rented: " + vehicle);
        if (aVehicleIsRentedBy(client)) throw new IllegalStateException("Client already renting: " + client);
        rentedVehicles.put(client, vehicle);
        return vehicle.dailyRentalPrice();
    }

    /**
     * Vérifie si un client loue actuellement un véhicule.
     *
     * @param client le client
     * @return {@code true} si le client est en train de louer
     */
    public boolean aVehicleIsRentedBy(Client client) {
        return rentedVehicles.containsKey(client);
    }

    /**
     * Vérifie si un véhicule est actuellement loué.
     *
     * @param v le véhicule
     * @return {@code true} si le véhicule est loué
     */
    public boolean vehicleIsRented(Vehicle v) {
        return rentedVehicles.containsValue(v);
    }

    /**
     * Le client rend le véhicule loué. Ne fait rien s'il n'en loue pas.
     *
     * @param client le client qui rend le véhicule
     */
    public void returnVehicle(Client client) {
        rentedVehicles.remove(client);
    }

    /**
     * Retourne la collection des véhicules actuellement loués.
     *
     * @return les véhicules loués
     */
    public Collection<Vehicle> allRentedVehicles() {
        return new ArrayList<>(rentedVehicles.values());
    }
}

