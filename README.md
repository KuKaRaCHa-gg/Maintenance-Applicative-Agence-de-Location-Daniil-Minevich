# Maintenance Applicative - Agence de Location de Vehicules

## Vue d'ensemble

Ce projet implémente une agence de location de véhicules (voitures et motos) en Java. Le projet respecte les normes modernes de qualité : tests JUnit 5, assertions AssertJ, couverture JaCoCo et analyse statique.

**Auteur** : Daniil Minevich  
**Date** : 19 mars 2026

---

## Architecture du Projet

### Packages

#### org.example.agency
Logique métier de l'agence de location.

Classes principales :
- Vehicle : Interface définissant les contrats de tous les véhicules
- AbstractVehicle : Classe abstraite pour factoriser le code commun
- Car : Implémentation pour les voitures
- Motorbike : Implémentation pour les motos
- RentalAgency : Gestion de l'agence (ajout/suppression de véhicules, filtrage)
- Client : Représentation d'un client
- BrandCriterion : Critère de filtrage par marque
- MaxPriceCriterion : Critère de filtrage par prix maximum
- UnknownVehicleException : Exception levée si tentative de supprimer un véhicule inexistant

#### org.example.util
Utilitaires techniques.

Classes :
- TimeProvider : Fournit l'année courante de manière centralisée

---

## Environnement Technique

| Élément | Version |
|---------|---------|
| OS | Windows 11 (amd64) |
| Java | Amazon Corretto 17.0.17 (LTS) |
| Gradle | 8.14 |
| JUnit | 5.10.0 |
| AssertJ | 3.24.2 |
| JaCoCo | 0.8.10 |

---

## Dépendances

```gradle
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
}
```

---

## Tests Unitaires

### Approche

- JUnit 5 : Framework de test moderne avec support des tests paramétrés
- AssertJ : Bibliothèque d'assertions fluides pour une meilleure lisibilité

### Couverture

- Couverture globale : 81% des instructions
- Packages testés :
  - org.example.agency : Tests complets des véhicules, agence, critères et clients
  - org.example.util : Tests de TimeProvider

### Exécution

Tous les tests :
```bash
.\gradlew.bat test
```

Tests du package util uniquement :
```bash
.\gradlew.bat test --tests org.example.util.*
```

Tests du package agency uniquement :
```bash
.\gradlew.bat test --tests org.example.agency.*
```

Exécution depuis l'IDE :
- Clic droit sur une classe de test -> Run 'NomDuTest'
- Ou sur le dossier src/test/java pour tous les tests

---

## Couverture de Code (JaCoCo)

### Rapports générés

Rapport HTML (visualisation locale) :
```
build/reports/jacoco/test/html/index.html
```

Rapport XML (pour SonarQube) :
```
build/reports/jacoco/test/jacocoTestReport.xml
```

### Interprétation

La couverture mesure le pourcentage de code exécuté par les tests :
- Lignes couvertes : Exécutées au moins une fois
- Lignes non couvertes : Jamais exécutées

### Limites

- La couverture ne garantit pas l'absence de bugs (seule la pertinence des tests importe)
- Un code à 100% peut avoir des défauts logiques complexes
- Un code peu couvert reste un risque de régression

---

## Analyse Statique (SonarQube)

### Indicateurs

| Indicateur | Signification |
|-----------|--------------|
| Coverage | Pourcentage de code couvert par les tests |
| Bugs | Défauts potentiels détectés |
| Code Smells | Code "malodorant" (complexité, duplication) |
| Vulnerabilities | Failles de sécurité |
| Security Hotspots | Points sensibles à vérifier |
| Technical Debt | Effort pour atteindre la qualité |

### Différences : Tests, Couverture, Analyse Statique

| Aspect | Tests Unitaires | JaCoCo | SonarQube |
|--------|-----------------|--------|-----------|
| Objectif | Valide le comportement | Mesure la couverture | Analyse la structure |
| Question | Cela marche ? | C'est tout testé ? | C'est propre ? |
| Outil | JUnit 5 | JaCoCo | SonarQube |
| Sortie | Réussi/Échoué | Pourcentage couvert | Métriques de qualité |

---

## Résultats et Zones à Risque

### Points Forts

- 100% de couverture sur les classes Car et Motorbike
- Pas de bugs critiques détectés
- Code bien documenté avec Javadoc
- Zéro duplication de code (héritée via AbstractVehicle)

### Zones d'Amélioration

- Client.java : Couverture partielle des méthodes equals() et hashCode()
- RentalAgency.printSelectedVehicles() : Non couverte (méthode I/O)
- Maintenabilité : Quelques code smells mineurs sur la complexité cyclomatique

---

## Structure Finale

```
Final/
├── src/
│   ├── main/java/org/example/
│   │   ├── agency/          (logique métier)
│   │   ├── util/            (TimeProvider)
│   │   └── Main.java
│   └── test/java/org/example/
│       ├── agency/          (tests de l'agence)
│       └── util/            (tests utilitaires)
├── build/                   (généré par Gradle)
│   ├── classes/
│   ├── reports/
│   │   ├── jacoco/          (rapports de couverture)
│   │   └── tests/           (résultats des tests)
├── gradle/
├── .gitignore
├── build.gradle.kts         (configuration Gradle)
├── settings.gradle.kts
├── gradlew / gradlew.bat
└── README.md
```

---

## Fonctionnalités Principales

### 1. Gestion des Véhicules

- Ajout/Suppression : agency.add(vehicle) / agency.remove(vehicle)
- Détails : Marque, modèle, année, prix journalier
- Types : Voitures (sièges) et motos (cylindrée)
- Validation : Année >= 1900 et <= année courante

### 2. Critères de Filtrage

```java
Predicate<Vehicle> brandFilter = new BrandCriterion("Toyota");
Predicate<Vehicle> priceFilter = new MaxPriceCriterion(100.0);
List<Vehicle> results = agency.select(brandFilter);
```

### 3. Gestion des Locations

```java
RentalAgency agency = new RentalAgency();
Client client = new Client("Jean", "Dupont", 1990);
double price = agency.rentVehicle(client, car);  // Louer
agency.returnVehicle(client);                    // Rendre
```

---

## Commandes Utiles

| Commande | Description |
|----------|------------|
| .\gradlew.bat clean | Nettoyer les artefacts générés |
| .\gradlew.bat build | Compiler et tester |
| .\gradlew.bat test | Exécuter tous les tests |
| .\gradlew.bat jacocoTestReport | Générer rapports JaCoCo |
| .\gradlew.bat sonar | Lancer analyse SonarQube |

---

## Javadoc

Toutes les classes et méthodes publiques sont documentées.

Générer la Javadoc :
```bash
.\gradlew.bat javadoc
```

Consulter :
```
build/docs/javadoc/index.html
```

---

## Checklist de Soumission

- Tous les tests passent
- Couverture JaCoCo générée
- Code documenté (Javadoc)
- .gitignore correctement configuré
- Dossier build/ exclu du versionning
- Fichiers sensibles supprimés (logs, tokens)
- Code du projet prêt pour soumission

---

## Contact

Auteur : Daniil Minevich  
Année : 2026

