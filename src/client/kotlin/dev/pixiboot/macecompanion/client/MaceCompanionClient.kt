package dev.pixiboot.macecompanion.client

import dev.pixiboot.macecompanion.client.config.Config
import dev.pixiboot.macecompanion.client.util.SendMessage
import net.fabricmc.api.ClientModInitializer

class MaceCompanionClient : ClientModInitializer {

    override fun onInitializeClient() {
        if (!Config.loadFromFile()) Config.saveToFile()
        StateManager.registerListeners()
        SendMessage.registerTickListener()
        HideGLGG.registerListener()
        RoundInfoHud.registerListener()
    }
}
