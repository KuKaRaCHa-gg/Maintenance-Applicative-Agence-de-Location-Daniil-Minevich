package org.example.util;

import java.time.Year;

/**
 * Classe utilitaire fournissant des informations temporelles.
 * <p>
 * Centralise la récupération de l'année courante afin de faciliter
 * les tests et l'évolution du code (on peut surcharger ou mocker cette classe).
 * </p>
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 * int annee = TimeProvider.currentYearValue(); // ex : 2026
 * </pre>
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 */
public class TimeProvider {

    /**
     * Retourne la valeur de l'année courante.
     * <p>
     * Utilise {@link java.time.Year#now()} pour obtenir l'année du système.
     * </p>
     *
     * @return l'année courante sous forme d'entier (ex : 2026)
     */
    public static int currentYearValue() {
        return Year.now().getValue();
    }
}
