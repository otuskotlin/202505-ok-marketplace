package ru.otus.otuskotlin.marketplace.e2e.be

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import ru.otus.otuskotlin.marketplace.e2e.be.base.BaseContainerTest
import ru.otus.otuskotlin.marketplace.e2e.be.docker.WiremockDockerCompose
import ru.otus.otuskotlin.marketplace.e2e.be.base.client.RestClient
import ru.otus.otuskotlin.marketplace.e2e.be.scenarios.v1.ScenariosV1
import ru.otus.otuskotlin.marketplace.e2e.be.scenarios.v2.ScenariosV2

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestWireMock: BaseContainerTest(WiremockDockerCompose) {
    private val client = RestClient(compose)
    @Test
    fun info() {
        println("${this::class.simpleName}")
    }

    @Nested
    internal inner class V1: ScenariosV1(client)
    @Nested
    internal inner class V2: ScenariosV2(client)

}