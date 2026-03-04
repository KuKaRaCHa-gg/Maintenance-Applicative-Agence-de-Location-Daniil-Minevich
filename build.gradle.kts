import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification

plugins {
    id("java")
    id("jacoco")
    id("org.sonarqube") version "4.4.1.3373"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    // Utiliser le toolchain Java pour reproductibilité (Java 17 recommandé)
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Forcer l'encodage UTF-8 pour éviter les BOM/illegal character issues
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
    // Affichage des tests pour faciliter le debug
    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
    finalizedBy(tasks.jacocoTestReport)
}

// Rapport JaCoCo
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}

// Tâches personnalisées pour exécuter des tags de tests
tasks.register<Test>("testUtils") {
    description = "Exécute uniquement les tests du package util"
    group = "verification"
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("utils")
    }
}

tasks.register<Test>("testAgency") {
    description = "Exécute uniquement les tests du package agency"
    group = "verification"
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("agency")
    }
}

// Vérification de la couverture JaCoCo (seuil minimal)
val coverageThreshold = 0.80.toBigDecimal()

// Utiliser un nom unique pour éviter les doublons
tasks.register<JacocoCoverageVerification>("jacocoCoverageVerificationCustom") {
    dependsOn(tasks.test)
    group = "verification"
    description = "Vérifie que la couverture de tests respecte le seuil fixé (Lignes >= 80%)"
    violationRules {
        rule {
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = coverageThreshold
            }
        }
    }
    // Recherche tous les fichiers .exec générés par JaCoCo dans build/jacoco
    executionData.setFrom(fileTree(mapOf("dir" to layout.buildDirectory.dir("jacoco").get().asFile, "include" to listOf("**/*.exec"))))
}

sonarqube {
    properties {
        property("sonar.projectKey", "rental-agency")
        property("sonar.projectName", "Rental Agency")
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.gradle.skipCompile", "true")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}