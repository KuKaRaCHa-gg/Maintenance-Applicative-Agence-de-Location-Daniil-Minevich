package org.example;

import org.example.agency.*;

/**
 * Classe principale démontrant l'utilisation de l'agence de location.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Agence de Location de Véhicules ===\n");

        // Création de l'agence
        RentalAgency agency = new RentalAgency();

        // Ajout de véhicules
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

        System.out.println("Véhicules disponibles dans l'agence:");
        for (Vehicle vehicle : agency.getVehicles()) {
            System.out.println("  - " + vehicle);
        }

        // Filtrage par marque
        System.out.println("\n--- Véhicules de marque Honda ---");
        BrandCriterion hondaCriterion = new BrandCriterion("Honda");
        agency.printSelectedVehicles(hondaCriterion);

        // Filtrage par prix maximal
        System.out.println("\n--- Véhicules à moins de 200€/jour ---");
        MaxPriceCriterion priceCriterion = new MaxPriceCriterion(200.0);
        agency.printSelectedVehicles(priceCriterion);

        // Création de clients
        Client client1 = new Client("Jean", "Dupont", 1990);
        Client client2 = new Client("Marie", "Martin", 1985);

        // Location de véhicules
        System.out.println("\n--- Locations ---");
        try {
            double price1 = agency.rentVehicle(client1, car1);
            System.out.println(client1.getFirstName() + " loue: " + car1.getBrand() + " " + car1.getModel() + " pour " + price1 + "€/jour");

            double price2 = agency.rentVehicle(client2, motorbike1);
            System.out.println(client2.getFirstName() + " loue: " + motorbike1.getBrand() + " " + motorbike1.getModel() + " pour " + price2 + "€/jour");
        } catch (Exception e) {
            System.err.println("Erreur: " + e.getMessage());
        }

        // Véhicules loués
        System.out.println("\n--- Véhicules actuellement loués ---");
        for (Vehicle vehicle : agency.allRentedVehicles()) {
            System.out.println("  - " + vehicle);
        }

        // Retour d'un véhicule
        System.out.println("\n--- Retour de véhicule ---");
        agency.returnVehicle(client1);
        System.out.println(client1.getFirstName() + " a rendu son véhicule");

        System.out.println("\nVéhicules encore loués: " + agency.allRentedVehicles().size());
    }
}
