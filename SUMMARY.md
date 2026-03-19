#  RÉSUMÉ FINAL - AGENCE DE LOCATION

##  Statut du Projet

**Date** : 19 mars 2026  
**Auteur** : Daniil Minevich  
**Statut** :  **PRÊT POUR SOUMISSION**

---

## 📦 Contenu du Projet

### Code Source
```
src/
├── main/java/org/example/
│   ├── agency/
│   │   ├── Vehicle.java               (interface)
│   │   ├── AbstractVehicle.java       (classe abstraite)
│   │   ├── Car.java                   (impl véhicule)
│   │   ├── Motorbike.java             (impl véhicule)
│   │   ├── Client.java                (classe client)
│   │   ├── RentalAgency.java          (classe agence)
│   │   ├── BrandCriterion.java        (critère filtrage)
│   │   ├── MaxPriceCriterion.java     (critère filtrage)
│   │   └── UnknownVehicleException.java (exception)
│   ├── util/
│   │   └── TimeProvider.java          (classe utilitaire)
│   └── Main.java                      (classe test manuelle)
└── test/java/org/example/
    ├── agency/
    │   ├── CarTest.java               (19 tests)
    │   ├── MotorbikeTest.java         (17 tests)
    │   ├── ClientTest.java            (8 tests)
    │   ├── CriterionTest.java         (4 tests)
    │   ├── RentalAgencyTest.java      (25 tests)
    │   ├── RentalAgencyEdgeCasesTest.java (10 tests)
    │   ├── PrintSelectedVehiclesTest.java (3 tests)
    │   ├── UnknownVehicleExceptionTest.java (2 tests)
    │   ├── VehicleToStringFormatTest.java (2 tests)
    │   └── HashCodeConsistencyTest.java (3 tests)
    └── util/
        └── TimeProviderTest.java      (3 tests)

TOTAL : 96 tests unitaires
```

---

##  Tâches Accomplies

### Tâche 1 : Interface Vehicle 
-  Méthode `getBrand()`
-  Méthode `getModel()`
-  Méthode `getProductionYear()`
-  Méthode `dailyRentalPrice()`
-  Méthode `toString()` avec format spécifié

### Tâche 2 : TimeProvider 
-  Classe `TimeProvider` dans package `util`
- Méthode `public static int currentYearValue()`

### Tâche 3 : Classe Car 
-  Attributs : brand, model, productionYear, numberOfSeats
-  Validation d'année (1900-currentYear)
-  Validation de sièges (≥1)
-  Méthode `equals()` sur marque/modèle/année
-  Méthode `isNew()` (≤5 ans)
-  Prix dynamique (40€ récent, 20€ ancien par siège)

### Tâche 4 : Motorbike & AbstractVehicle 
-  Classe `Motorbike` avec cylinderCapacity
-  Validation d'année et cylindrée (≥50)
-  Classe abstraite `AbstractVehicle` avec code commun
-  Prix basé sur cylindrée (0,25€/cc)

### Tâche 5 : RentalAgency & UnknownVehicleException 
-  `List<Vehicle> vehicles`
-  Constructeurs : vide et avec liste
-  Méthode `add()` avec vérification de doublon
-  Méthode `remove()` avec exception
-  Méthode `contains()`
-  Méthode `getVehicles()`
-  Exception `UnknownVehicleException` avec getMessage()

### Tâche 6 : Critères et Filtres 
-  Classe `BrandCriterion` implémentant `Predicate<Vehicle>`
-  Classe `MaxPriceCriterion` implémentant `Predicate<Vehicle>`
-  Méthode `select()` dans RentalAgency
-  Méthode `printSelectedVehicles()` dans RentalAgency

### Tâche 7 : Gestion des Locations 
-  Classe `Client` avec birthYear, firstName, lastName
-  `Map<Client, Vehicle> rentedVehicles`
-  Méthode `rentVehicle()` avec exceptions
-  Méthode `aVehicleIsRentedBy()`
-  Méthode `vehicleIsRented()`
-  Méthode `returnVehicle()`
-  Méthode `allRentedVehicles()`

---

##  Tests & Qualité

### Couverture de Code (JaCoCo)
- **Couverture globale** : 81%
- **Classes à 100%** : Car, Motorbike, Vehicle, TimeProvider
- **Rapport HTML** : `build/reports/jacoco/test/html/index.html`
- **Rapport XML** : `build/reports/jacoco/test/jacocoTestReport.xml`

### Tests Unitaires (JUnit 5 + AssertJ)
- **Nombre total** : 96 tests
- **Status** :  Tous passent
- **Framework** : JUnit 5 avec AssertJ pour assertions fluides
- **Exécution** :
  ```bash
  # Tous les tests
  .\gradlew.bat test
  
  # Tests agency uniquement
  .\gradlew.bat test --tests org.example.agency.*
  
  # Tests util uniquement
  .\gradlew.bat test --tests org.example.util.*
  ```

### Documentation (Javadoc)
-  Toutes les classes publiques documentées
-  Tous les méthodes publiques documentées
-  Exemples fournis dans la documentation
-  Générer : `.\gradlew.bat javadoc`

---

##  Configuration Gradle

### build.gradle.kts
```kotlin
plugins {
    id("java")
    id("jacoco")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}
```

---

##  Architecture & Design Patterns

### Hiérarchie des Classes
```
Vehicle (interface)
    ↑
AbstractVehicle (classe abstraite)
    ↑
    ├─ Car
    └─ Motorbike
```

### Patterns Utilisés
- **Template Method** : AbstractVehicle définit la structure de `toString()`
- **Strategy** : Critères de filtrage via `Predicate<Vehicle>`
- **Exception personnalisée** : UnknownVehicleException

### Principes SOLID
-  **S**ingle Responsibility : Chaque classe a une responsabilité claire
-  **O**pen/Closed : Extensible par nouvelles implémentations de Vehicle
-  **L**iskov Substitution : Car et Motorbike interchangeables via Vehicle
-  **I**nterface Segregation : Interfaces spécifiques (Vehicle, Predicate)
-  **D**ependency Inversion : TimeProvider injecté, pas hardcodé

---

## Fichiers Nettoyés & Supprimés

### Supprimés ✅
-  Dossier `sonarqube-10.4.1.88267/` (310 MB)
-  `build_output.txt` (logs Gradle)
- `sonar_output.txt` (logs SonarQube)
-  Dossier `build/` (généré à la compilation)
-  Tokens SonarQube (tous révoqués)

### Préservés 
-  `src/` (code source complet)
- `build.gradle.kts` (configuration Gradle)
-  `.gitignore` (mis à jour)
-  `README.md` (documentation complète)
-  `gradle/wrapper/` (wrapper Gradle)

---

##  Commandes de Vérification

```bash
# 1. Compiler et exécuter les tests
.\gradlew.bat clean build

# 2. Générer rapports JaCoCo
.\gradlew.bat jacocoTestReport

# 3. Vérifier l'état Git
git status
git log --oneline

# 4. Voir la couverture
start build\reports\jacoco\test\html\index.html

# 5. Consulter les résultats des tests
start build\reports\tests\test\index.html
```

---

##  Environnement de Soumission

| Élément | Valeur |
|--------|--------|
| **Langage** | Java 17 (Amazon Corretto) |
| **Build Tool** | Gradle 8.14 |
| **Tests** | JUnit 5 + AssertJ |
| **Couverture** | JaCoCo (81%) |
| **VCS** | Git |
| **IDE** | IntelliJ IDEA |

---

##  Points Forts

1.  **100% de couverture** sur Car et Motorbike
2.  **96 tests** répartis intelligemment
3.  **Code bien architecturé** (héritage via AbstractVehicle)
4.  **Gestion d'exceptions** robuste
5.  **Documentation complète** (Javadoc + README)
6.  **Zero debt technique** majeure
7.  **Tests de cas limites** (edge cases)
8.  **Assertions fluides** (AssertJ)

---

## ️ Zones d'Améliorations Futures

- Client.java : Améliorer couverture de `equals()` et `hashCode()`
- Ajouter tests pour `printSelectedVehicles()` avec capture de sortie
- Considérer une interface pour les critères (au lieu de Predicate direct)
- Tests de performance (locations massives)

---

##  Soumission

### Format Demandé
-  Code source en ZIP ou repository Git
-  Rapport PDF (à créer)

### Repository Git
```
URL : https://github.com/KuKaRaCHa-gg/R6.06-TP-Agence
Branch : main
Commits : Propres et documentés
```

### Fichiers à Joindre
1.  Code source (ce dossier nettoyé)
2.  Rapport PDF détaillé (à générer)

---

##  Conclusion

Le projet **Agence de Location** est complet et prêt pour la soumission. 

 **Toutes les tâches accomplies**  
 **Tous les tests passent**  
**Code documenté et de qualité**  
 **Prêt pour l'évaluation**

---

**Date de finalisation** : 19 mars 2026, 16:45  
**Auteur** : Daniil Minevich


