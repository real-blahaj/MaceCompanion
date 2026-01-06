package moe.pxe.macecompanion.client

import dev.isxander.yacl3.config.v3.value
import moe.pxe.macecompanion.client.config.Config
import moe.pxe.macecompanion.client.util.OnMaceRoulette
import moe.pxe.macecompanion.client.util.SendMessage
import net.fabricmc.api.ClientModInitializer

class MaceCompanionClient : ClientModInitializer {

    override fun onInitializeClient() {
        if (!Config.loadFromFile()) Config.saveToFile()
        OnMaceRoulette.fillPlotIds(Config.plotIds.value.toSet())
        OnMaceRoulette.registerFlintFeature()
        StateManager.registerListeners()
        SendMessage.registerTickListener()
        HideGLGG.registerListener()
        RoundInfoHud.registerListener()
    }
}
