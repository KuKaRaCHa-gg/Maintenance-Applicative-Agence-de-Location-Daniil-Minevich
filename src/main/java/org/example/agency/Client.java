package org.example.agency;

import java.util.Objects;

/**
 * Représente un client de l'agence de location.
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
     * Construit un client.
     *
     * @param firstName le prénom
     * @param lastName  le nom de famille
     * @param birthYear l'année de naissance
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
     *
     * @param o l'objet à comparer
     * @return {@code true} si les deux clients sont égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return birthYear == client.birthYear
                && Objects.equals(firstName, client.firstName)
                && Objects.equals(lastName, client.lastName);
    }

    /**
     * Retourne un code de hachage basé sur le prénom, nom et année de naissance.
     *
     * @return le code de hachage
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthYear);
    }

    /**
     * Retourne une représentation textuelle du client.
     * <p>Format : {@code "Prénom Nom (année)"}</p>
     *
     * @return la représentation textuelle
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + birthYear + ")";
    }
}

