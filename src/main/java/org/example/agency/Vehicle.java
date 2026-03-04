package org.example.agency;

/**
 * Interface représentant un véhicule disponible à la location.
 * <p>
 * Tout véhicule de l'agence doit implémenter cette interface afin
 * de garantir un contrat commun pour :
 * <ul>
 *     <li>L'identification du véhicule (marque, modèle, année)</li>
 *     <li>Le calcul du prix journalier de location</li>
 *     <li>L'affichage formaté du véhicule</li>
 * </ul>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 * @see Car
 * @see Motorbike
 */
public interface Vehicle {

    /**
     * Retourne la marque du véhicule.
     *
     * @return la marque (ex : "Toyota", "Yamaha")
     */
    String getBrand();

    /**
     * Retourne le modèle du véhicule.
     *
     * @return le modèle (ex : "Corolla", "MT-07")
     */
    String getModel();

    /**
     * Retourne l'année de fabrication du véhicule.
     *
     * @return l'année de production (ex : 2020)
     */
    int getProductionYear();

    /**
     * Calcule et retourne le prix de location journalier du véhicule.
     * <p>
     * Le calcul dépend du type de véhicule :
     * <ul>
     *     <li>Voiture récente (≤ 5 ans) : 40€ × nombre de sièges</li>
     *     <li>Voiture ancienne (&gt; 5 ans) : 20€ × nombre de sièges</li>
     *     <li>Moto : 0,25€ × cylindrée (cm³)</li>
     * </ul>
     * </p>
     *
     * @return le prix journalier en euros
     */
    double dailyRentalPrice();

    /**
     * Retourne une représentation textuelle du véhicule.
     * <p>
     * Format attendu :
     * <pre>
     * [Type] [Marque] [Modèle] [Année] ([Détails]) : [Prix]€
     * </pre>
     * Exemples :
     * <pre>
     * Car Toyota Corolla 2020 (5 seats) : 200.0€
     * Motorbike Yamaha MT-07 2021 (689cm³) : 172.3€
     * </pre>
     * </p>
     *
     * @return la représentation textuelle du véhicule
     */
    String toString();
}
