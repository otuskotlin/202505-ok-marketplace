plugins {
    id("build-jvm")
    application
    alias(libs.plugins.shadowJar)
    id("build-docker")
}

application {
    mainClass.set("ru.otus.otuskotlin.marketplace.app.rabbit.ApplicationKt")
}

docker {
    imageName = "ok-marketplace-rabbit"
}

dependencies {

    implementation(kotlin("stdlib"))

    implementation(libs.rabbitmq.client)
    implementation(libs.jackson.databind)
    implementation(libs.logback)
    implementation(libs.coroutines.core)

    implementation(project(":ok-marketplace-common"))
    implementation(project(":ok-marketplace-app-common"))
    implementation("ru.otus.otuskotlin.marketplace.libs:ok-marketplace-lib-logging-logback")

    // v1 api
    implementation(project(":ok-marketplace-api-v1-jackson"))
    implementation(project(":ok-marketplace-api-v1-mappers"))

    // v2 api
    implementation(project(":ok-marketplace-api-v2-kmp"))

    implementation(project(":ok-marketplace-biz"))
    implementation(project(":ok-marketplace-stubs"))

    testImplementation(libs.testcontainers.rabbitmq)
    testImplementation(kotlin("test"))
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
