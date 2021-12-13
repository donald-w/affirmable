package com.donaldw.affirmable;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.donaldw.affirmable");

        noClasses()
            .that()
            .resideInAnyPackage("com.donaldw.affirmable.service..")
            .or()
            .resideInAnyPackage("com.donaldw.affirmable.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.donaldw.affirmable.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
