plugins {
    application
    id("build-jvm")
    alias(libs.plugins.shadowJar)
    id("build-docker")
}

application {
    mainClass.set("ru.otus.otuskotlin.marketplace.app.kafka.MainKt")
}

docker {
    imageName = "ok-marketplace-app-kafka"
}

dependencies {
    implementation(libs.kafka.client)
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.atomicfu)

    implementation("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-logback")

    implementation(project(":ok-marketplace-app-common"))

    // transport models
    implementation(project(":ok-marketplace-common"))
    implementation(project(":ok-marketplace-api-v1-jackson"))
    implementation(project(":ok-marketplace-api-v1-mappers"))
    implementation(project(":ok-marketplace-api-v2-kmp"))
    // logic
    implementation(project(":ok-marketplace-biz"))

    testImplementation(kotlin("test-junit"))
}

tasks {
    shadowJar {
        manifest {
            attributes(mapOf("Main-Class" to application.mainClass.get()))
        }
    }

    dockerBuild {
        dependsOn("shadowJar")
    }
}

