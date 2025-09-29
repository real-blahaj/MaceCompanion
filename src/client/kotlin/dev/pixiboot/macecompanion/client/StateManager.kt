package dev.pixiboot.macecompanion.client

import com.mojang.authlib.GameProfile
import dev.pixiboot.macecompanion.client.enums.Modifiers
import dev.pixiboot.macecompanion.client.util.SubtitleCallback
import dev.pixiboot.macecompanion.client.util.TitleCallback
import dev.pixiboot.macecompanion.client.util.OnMaceRoulette
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.client.MinecraftClient
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket
import net.minecraft.network.packet.s2c.play.TitleS2CPacket
import net.minecraft.text.Style
import net.minecraft.util.ActionResult
import kotlin.time.TimeMark
import kotlin.time.TimeSource

object StateManager {
    var gameOngoing = false
    var round = -1
    var roundColor = Style.EMPTY.withColor(0x9ef6fc)
    var playersAlive = -1
    var playersTotal = -1
    var eliminations = -1
    var eliminated = true
    var playtime: TimeMark? = null
    var modifiers = mutableListOf<Modifiers>()
    var modifierBoosters = mutableMapOf<Modifiers, MutableList<GameProfile>>()

    private val chatRoundNumberRegex = """ +Round (\d+) +""".toRegex()

    private val chatModifierHeaderRegex = """⏵ .*ᴍᴏᴅɪꜰɪᴇʀ:""".toRegex()
    private val chatModifierItemRegex = """  ◇ (.+)""".toRegex()
    private val chatModifierBoostedRegex = """  ◇ (.+) \(☁ Boosted by (.+)\)""".toRegex()

    private val chatEliminationRegex = """⏵ .+ was eliminated by .+! \((\d+) remain\)""".toRegex()
    private val chatEarlyLeaveRegex = """⏵ .+ left while alive! \((\d+) remain\)""".toRegex()
    private val chatBlowUpRegex = """⏵ .+ blew up! \((\d+) remain\)""".toRegex()
    private val chatVoidEliminationRegex = """⏵ .+ fell off the map! \((\d+) remain\)""".toRegex()
    private val chatElimCounterRegex = """  ◇ \+\d+🪓, total (\d+)🪓""".toRegex()

    private val chatLeaderboardHeaderRegex = """ +‌*ɢᴀᴍᴇ ʟᴇᴀᴅᴇʀʙᴏᴀʀᴅ:""".toRegex()

    private val titleRoundNumberRegex = """ʀᴏᴜɴᴅ (\d+)""".toRegex()
    private val titlePlayersAliveRegex = """(\d+) ᴀʟɪᴠᴇ""".toRegex()
    private val titleEliminatedRegex = """☠☠☠""".toRegex()

    private var checkForModifiers = false

    private fun setRoundNumber(number: Int) {
        if (round != 1 && number == 1) {
            playersTotal = playersAlive
            playtime = TimeSource.Monotonic.markNow()
            eliminations = 0
            AutoGL.sendGlMessage()
        }
        round = number
        gameOngoing = true
        modifiers = mutableListOf()
        modifierBoosters = mutableMapOf()
//        MaceCompanion.LOGGER.info("Round: $round - Alive:$playersAlive/$playersTotal")
    }
    fun resetState() {
        gameOngoing = false
        round = -1
        roundColor = Style.EMPTY.withColor(0x9ef6fc)
        playersAlive = -1
        playersTotal = -1
        eliminations = -1
        eliminated = true
        playtime = null
        modifiers = mutableListOf()
        modifierBoosters = mutableMapOf()
    }
    fun registerListeners() {
        // Chat Listener
        ClientReceiveMessageEvents.ALLOW_GAME.register { message, overlay ->
            if (overlay) return@register true
            if (!OnMaceRoulette.onMace) return@register true

            // Round Number Header
            chatRoundNumberRegex.matchEntire(message.string)?.groups[1]?.let { setRoundNumber(it.value.toIntOrNull() ?: -1) }
            // Elimination Messages (slain by, left the game, blew up, fell off the map)
            val eliminationMatch = chatEliminationRegex.matchEntire(message.string) ?: chatEarlyLeaveRegex.matchEntire(message.string) ?: chatBlowUpRegex.matchEntire(message.string) ?: chatVoidEliminationRegex.matchEntire(message.string)
            eliminationMatch?.groups[1]?.let { playersAlive = it.value.toIntOrNull() ?: -1 }
            // Elimination Counter
            chatElimCounterRegex.matchEntire(message.string)?.groups[1]?.let { eliminations = it.value.toIntOrNull() ?: 0 }
            // Game Leaderboard Header
            chatLeaderboardHeaderRegex.matchEntire(message.string)?.let {
                gameOngoing = false
                AutoGG.sendGGMessage()
            }
            // Modifier Entry
            if (checkForModifiers) (chatModifierBoostedRegex.matchEntire(message.string) ?: chatModifierItemRegex.matchEntire(message.string))?.also {
                var enum = Modifiers.UNKNOWN
                it.groups[1]?.let { modifier ->
                    enum = Modifiers.entries.find { enum -> enum.matchName == modifier.value } ?: Modifiers.UNKNOWN
                    modifiers.add(enum)
                    modifierBoosters[enum] = mutableListOf()
                }

                if (it.groups.size >= 3) it.groups[2]?.let { playerList ->
                    val playerNames = playerList.value.split(", ")
                    val networkHandler = MinecraftClient.getInstance().networkHandler ?: return@let
                    for (player in playerNames) {
                        val profile = networkHandler.getPlayerListEntry(player)?.profile ?: continue
                        modifierBoosters[enum]?.add(profile)
                    }
                }
            } ?: run {
                checkForModifiers = false
            }
            // Modifier Header
            chatModifierHeaderRegex.matchEntire(message.string)?.let { checkForModifiers = true }

            return@register true
        }

        // Title Listener
        TitleCallback.EVENT.register(
            object : TitleCallback {
                override fun onTitle(packet: TitleS2CPacket): ActionResult {
                    if (!OnMaceRoulette.onMace) return ActionResult.PASS
                    titleRoundNumberRegex.matchEntire(packet.text.string)?.let { roundNumberMatch ->
                        roundNumberMatch.groups[1]?.let { setRoundNumber(it.value.toIntOrNull() ?: -1) }
                        roundColor = packet.text.siblings[0].style ?: roundColor
                    }
                    titleEliminatedRegex.matchEntire(packet.text.string)?.let { eliminated = true }
                    return ActionResult.PASS
                }
            }
        )

        // Subtitle Listener
        SubtitleCallback.EVENT.register(
            object : SubtitleCallback {
                override fun onSubtitle(packet: SubtitleS2CPacket): ActionResult {
                    if (!OnMaceRoulette.onMace) return ActionResult.PASS
                    titlePlayersAliveRegex.matchEntire(packet.text.string)?.let { playersAliveMatch -> playersAliveMatch.groups[1]?.let { playersAlive = it.value.toIntOrNull() ?: -1 } }
                    return ActionResult.PASS
                }
            }
        )

        ClientPlayConnectionEvents.DISCONNECT.register { _, _ -> resetState() }
    }
}