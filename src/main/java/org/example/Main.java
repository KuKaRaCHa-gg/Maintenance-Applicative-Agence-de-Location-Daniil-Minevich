package org.example;

import org.example.agency.*;

/**
 * Classe principale démontrant l'utilisation de l'agence de location.
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 */
public class Main {

    /**
     * Point d'entrée de l'application.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        System.out.println("=== Agence de Location de Vehicules ===\n");

        RentalAgency agency = new RentalAgency();

        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Honda", "Civic", 2019, 4);
        Car car3 = new Car("Mercedes", "S-Class", 2023, 7);
        Motorbike motorbike1 = new Motorbike("Yamaha", "MT-07", 2021, 689);
        Motorbike motorbike2 = new Motorbike("Honda", "CBR500R", 2022, 471);

        agency.add(car1);
        agency.add(car2);
        agency.add(car3);
        agency.add(motorbike1);
        agency.add(motorbike2);

        System.out.println("Vehicules disponibles:");
        for (Vehicle v : agency.getVehicles()) {
            System.out.println("  - " + v);
        }

        System.out.println("\n--- Honda ---");
        agency.printSelectedVehicles(new BrandCriterion("Honda"));

        System.out.println("\n--- Moins de 200/jour ---");
        agency.printSelectedVehicles(new MaxPriceCriterion(200.0));

        Client client1 = new Client("Jean", "Dupont", 1990);
        Client client2 = new Client("Marie", "Martin", 1985);

        System.out.println("\n--- Locations ---");
        try {
            double p1 = agency.rentVehicle(client1, car1);
            System.out.println(client1 + " loue " + car1.getModel() + " : " + p1 + "EUR/jour");
            double p2 = agency.rentVehicle(client2, motorbike1);
            System.out.println(client2 + " loue " + motorbike1.getModel() + " : " + p2 + "EUR/jour");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        agency.returnVehicle(client1);
        System.out.println("\n" + client1 + " a rendu son vehicule.");
    }
}

