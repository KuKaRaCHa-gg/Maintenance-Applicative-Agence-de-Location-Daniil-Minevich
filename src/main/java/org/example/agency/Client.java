package org.example.agency;

import java.util.Objects;

/**
 * Représente un client de l'agence de location.
 * <p>
 * Un client est identifié par son prénom, son nom de famille et son année de naissance.
 * Deux clients sont considérés comme égaux s'ils partagent ces trois informations.
 * </p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 * Client client = new Client("Jean", "Dupont", 1990);
 * client.getFirstName()  // "Jean"
 * client.getLastName()   // "Dupont"
 * client.getBirthYear()  // 1990
 * client.toString()      // "Jean Dupont (1990)"
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see RentalAgency
 */
public class Client {

    /** Le prénom du client. */
    private final String firstName;

    /** Le nom de famille du client. */
    private final String lastName;

    /** L'année de naissance du client. */
    private final int birthYear;

    /**
     * Construit un client avec ses informations personnelles.
     *
     * @param firstName le prénom du client
     * @param lastName  le nom de famille du client
     * @param birthYear l'année de naissance du client
     */
    public Client(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    /**
     * Retourne le prénom du client.
     *
     * @return le prénom
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retourne le nom de famille du client.
     *
     * @return le nom de famille
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retourne l'année de naissance du client.
     *
     * @return l'année de naissance
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Teste l'égalité entre ce client et un autre objet.
     * <p>
     * Deux clients sont égaux s'ils ont le même prénom, le même nom de famille
     * et la même année de naissance.
     * </p>
     *
     * @param o l'objet à comparer
     * @return {@code true} si les deux clients sont égaux, {@code false} sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Client client = (Client) o;
        return birthYear == client.birthYear
                && Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName);
    }

    /**
     * Retourne un code de hachage basé sur le prénom, le nom de famille et l'année de naissance.
     *
     * @return le code de hachage du client
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthYear);
    }

    /**
     * Retourne une représentation textuelle du client.
     * <p>
     * Format : {@code "Prénom Nom (année)"}
     * </p>
     * <p>Exemple :</p>
     * <pre>
     * new Client("Jean", "Dupont", 1990).toString() → "Jean Dupont (1990)"
     * </pre>
     *
     * @return la représentation textuelle du client
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + birthYear + ")";
    }
}


