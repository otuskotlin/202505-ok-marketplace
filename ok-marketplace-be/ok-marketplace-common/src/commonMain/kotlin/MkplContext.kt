package ru.otus.otuskotlin.marketplace.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.marketplace.common.models.MkplAd
import ru.otus.otuskotlin.marketplace.common.models.MkplAdFilter
import ru.otus.otuskotlin.marketplace.common.models.MkplCommand
import ru.otus.otuskotlin.marketplace.common.models.MkplError
import ru.otus.otuskotlin.marketplace.common.models.MkplRequestId
import ru.otus.otuskotlin.marketplace.common.models.MkplState
import ru.otus.otuskotlin.marketplace.common.models.MkplWorkMode
import ru.otus.otuskotlin.marketplace.common.stubs.MkplStubs
import ru.otus.otuskotlin.marketplace.common.ws.IMkplWsSession

data class MkplContext(
    var command: MkplCommand = MkplCommand.NONE,
    var state: MkplState = MkplState.NONE,
    val errors: MutableList<MkplError> = mutableListOf(),

    var corSettings: MkplCorSettings = MkplCorSettings(),
    var workMode: MkplWorkMode = MkplWorkMode.PROD,
    var stubCase: MkplStubs = MkplStubs.NONE,
    var wsSession: IMkplWsSession = IMkplWsSession.NONE,

    var requestId: MkplRequestId = MkplRequestId.NONE,
    var timeStart: Instant = Instant.NONE,

    var adRequest: MkplAd = MkplAd(),
    var adFilterRequest: MkplAdFilter = MkplAdFilter(),

    var adValidating: MkplAd = MkplAd(),
    var adFilterValidating: MkplAdFilter = MkplAdFilter(),

    var adValidated: MkplAd = MkplAd(),
    var adFilterValidated: MkplAdFilter = MkplAdFilter(),

    var adResponse: MkplAd = MkplAd(),
    var adsResponse: MutableList<MkplAd> = mutableListOf(),
)
