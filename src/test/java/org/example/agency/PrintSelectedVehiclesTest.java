package org.example.agency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

@Tag("agency")
class PrintSelectedVehiclesTest {

    @Test
    void printSelectedVehicles_shouldPrintMatchingVehicles() {
        RentalAgency agency = new RentalAgency();
        Car car1 = new Car("Toyota", "Corolla", 2020, 5);
        Car car2 = new Car("Honda", "Civic", 2019, 4);
        agency.add(car1);
        agency.add(car2);

        BrandCriterion toyota = new BrandCriterion("Toyota");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        try {
            agency.printSelectedVehicles(toyota);
        } finally {
            System.setOut(original);
        }

        String printed = out.toString();
        assertThat(printed).contains("Toyota");
        assertThat(printed).doesNotContain("Honda");
    }
}

