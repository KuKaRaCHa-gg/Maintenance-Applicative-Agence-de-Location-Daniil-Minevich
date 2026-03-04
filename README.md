# Agence de Location de Véhicules

Projet Java réalisé dans le cadre du TP **Maintenance Applicative** — BUT Informatique.

## Description

Application de gestion d'une agence de location de véhicules (voitures et motos).  
Le projet implémente une hiérarchie de classes, des critères de filtrage, et la gestion complète des locations par client.

## Structure du projet

```
src/
├── main/java/org/example/
│   ├── agency/
│   │   ├── Vehicle.java              # Interface commune
│   │   ├── AbstractVehicle.java      # Classe abstraite partagée
│   │   ├── Car.java                  # Voiture
│   │   ├── Motorbike.java            # Moto
│   │   ├── RentalAgency.java         # Agence de location
│   │   ├── Client.java               # Client
│   │   ├── BrandCriterion.java       # Filtre par marque
│   │   ├── MaxPriceCriterion.java    # Filtre par prix max
│   │   └── UnknownVehicleException.java
│   └── util/
│       └── TimeProvider.java         # Utilitaire année courante
└── test/java/org/example/
    ├── agency/                        # Tests du package agency
    └── util/                          # Tests du package util
```

## Technologies

| Outil | Version |
|-------|---------|
| Java (Amazon Corretto) | 17 |
| Gradle | 8.14 |
| JUnit 5 | 5.10.0 |
| AssertJ | 3.24.2 |
| JaCoCo | intégré Gradle |
| SonarQube Plugin | 4.4.1 |

## Lancer les tests

```bash
# Tous les tests
./gradlew test

# Tests du package util uniquement
./gradlew testUtils

# Tests du package agency uniquement
./gradlew testAgency

# Rapport de couverture JaCoCo
./gradlew jacocoTestReport
```

## Auteur

**Daniil Minevich** — 2026

