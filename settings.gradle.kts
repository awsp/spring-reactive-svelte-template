rootProject.name = "spring-svelte-template"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "cz.habarta.typescript-generator") {
                useModule("cz.habarta.typescript-generator:typescript-generator-gradle-plugin:${requested.version ?: "+"}")
            }
        }
    }
}
