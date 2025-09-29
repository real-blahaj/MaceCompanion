package dev.pixiboot.macecompanion.client.util

import dev.dfonline.flint.FlintAPI
import dev.dfonline.flint.feature.trait.PlotSwitchListeningFeature
import dev.dfonline.flint.hypercube.Plot
import dev.pixiboot.macecompanion.client.StateManager

object OnMaceRoulette {
    var onMace = false
    fun registerFlintFeature() {
        FlintAPI.confirmLocationWithLocate()
        FlintAPI.registerFeature(
            object : PlotSwitchListeningFeature {
                override fun onSwitchPlot(oldPlot: Plot, plot: Plot) {
                    StateManager.resetState()
                    onMace = setOf(14000002 /* Mace Roulette */, 14000004 /* MaceDev */, 14000007 /* MaceStatless (old) */, 70525 /* MaceStatless (new) */).contains(plot.id)
                }
            }
        )
    }
}