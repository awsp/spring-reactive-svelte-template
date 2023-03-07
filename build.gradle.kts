import cz.habarta.typescript.generator.JsonLibrary
import cz.habarta.typescript.generator.OptionalProperties
import cz.habarta.typescript.generator.TypeScriptFileType
import cz.habarta.typescript.generator.TypeScriptOutputKind
import cz.habarta.typescript.generator.EnumMapping

plugins {
    java
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"
    id("cz.habarta.typescript-generator") version "3.1.1185"
}

group = "com.github.awsp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("io.r2dbc:r2dbc-h2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    generateTypeScript {
        outputKind = TypeScriptOutputKind.module
        jsonLibrary = JsonLibrary.jackson2
        outputFileType = TypeScriptFileType.implementationFile
        classPatterns = mutableListOf(
            "com.github.awsp.springsveltetemplate.model.*",
            "com.github.awsp.springsveltetemplate.security.domain.model.*",
            "com.github.awsp.springsveltetemplate.security.payload.**",
        )
        classesWithAnnotations = mutableListOf(
            "com.github.awsp.springsveltetemplate.annotations.GenerateTypescript"
        )
        excludeClassPatterns = mutableListOf(
            "com.github.awsp.springsveltetemplate.security.model._*",
        )
        outputFile = "frontend/src/resources.d.ts"
        outputFileType = TypeScriptFileType.declarationFile
        optionalProperties = OptionalProperties.useLibraryDefinition
        mapEnum = EnumMapping.asEnum
    }
}
