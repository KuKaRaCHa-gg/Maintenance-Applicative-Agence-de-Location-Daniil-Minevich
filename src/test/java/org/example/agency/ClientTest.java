package org.example.agency;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Tag("agency")
class ClientTest {

    @Test
    void equalsAndHashCode_shouldBeConsistent() {
        Client c1 = new Client("Alice", "Dupont", 1990);
        Client c2 = new Client("Alice", "Dupont", 1990);
        Client c3 = new Client("Bob", "Martin", 1985);

        // equals
        assertThat(c1).isEqualTo(c2);
        assertThat(c1).isNotEqualTo(c3);

        // hashCode
        assertThat(c1.hashCode()).isEqualTo(c2.hashCode());

        // toString contains useful info
        String s = c1.toString();
        assertThat(s).contains("Alice").contains("Dupont").contains("1990");
    }
}

