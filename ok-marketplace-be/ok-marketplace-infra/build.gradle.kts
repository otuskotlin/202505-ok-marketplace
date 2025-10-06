plugins {
    id("build-jvm")
    id("maven-publish")
}

kotlin {
    kotlin {
        tasks.processResources {
            copy {
                from("backend")
                into(layout.buildDirectory.dir("resources/main/backend"))
            }

        }

    }

}

publishing {
    repositories {
        maven {
            name = "LocalRepo"
            url = uri("${rootProject.buildDir}/repo") // Локальный репозиторий для примера
        }
    }
    publications {
        val backendInfra by publications.creating(MavenPublication::class) {
            from(components["kotlin"])
            artifactId = "backend-infra"
        }
    }
}