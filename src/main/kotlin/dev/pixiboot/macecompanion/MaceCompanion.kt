package dev.pixiboot.macecompanion

import net.fabricmc.api.EnvType
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory

class MaceCompanion : ModInitializer {
    companion object {
        const val MOD_ID = "macecompanion"
        val LOGGER = LoggerFactory.getLogger(MOD_ID)
        val DEBUG_MODE = false
    }
    override fun onInitialize() {
        LOGGER.info("Initializing Mace Roulette Companion...")
        if (FabricLoader.getInstance().environmentType == EnvType.SERVER) {
            LOGGER.error("Mace Roulette Companion is not a server-side mod!")
        }

    }

}