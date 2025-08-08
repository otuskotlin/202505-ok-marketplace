pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ok-marketplace-202505"

//includeBuild("lessons")
includeBuild("ok-marketplace-be")
includeBuild("ok-marketplace-tests")
include("ok-marketplace-libs")
include("ok-marketplace-libs:ok-marketplace-lib-logging-common")
include("ok-marketplace-libs:ok-marketplace-lib-logging-kermit")
include("ok-marketplace-libs:ok-marketplace-lib-logging-logback")
include("ok-marketplace-libs:ok-marketplace-lib-logging-socket")