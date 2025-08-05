package ru.otus.otuskotlin.marketplace.e2e.be.scenarios.v2

import org.junit.jupiter.api.Nested
import ru.otus.otuskotlin.marketplace.e2e.be.base.client.Client

@Suppress("unused")
abstract class ScenariosV2(
    private val client: Client
) {
    @Nested
    internal inner class CreateDeleteV2: ScenarioCreateDeleteV2(client)
}