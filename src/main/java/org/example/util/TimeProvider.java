package org.example.util;

import java.time.Year;

/**
 * Classe utilitaire fournissant l'année courante.
 *
 * @author Daniil Minevich
 * @version 1.0
 * @since 2026-03-03
 */
public class TimeProvider {

    /**
     * Retourne l'année courante.
     *
     * @return l'année courante (ex : 2026)
     */
    public static int currentYearValue() {
        return Year.now().getValue();
    }
}

