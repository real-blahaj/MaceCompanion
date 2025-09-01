package dev.pixiboot.macecompanion.client.config

import dev.isxander.yacl3.api.*
import dev.isxander.yacl3.api.controller.EnumControllerBuilder
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder
import dev.isxander.yacl3.api.controller.StringControllerBuilder
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder
import dev.pixiboot.macecompanion.client.enums.HudElements
import dev.pixiboot.macecompanion.client.enums.HudLocation
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

object ConfigMenu {
    fun generateScreen(parent: Screen): Screen? {
        return YetAnotherConfigLib.createBuilder()
            .title(Text.translatable("mrc.config.title"))
            .category(ConfigCategory.createBuilder()
                .name(Text.translatable("mrc.config.category.automsg"))
                .group(OptionGroup.createBuilder()
                    .name(Text.translatable("mrc.config.category.automsg.group.autogg"))
                    .description(OptionDescription.of(Text.of("mrc.config.category.automsg.group.autogg.description")))
                    .option(Option.createBuilder<Boolean>()
                        .name(Text.translatable("mrc.config.category.automsg.group.autogg.option.useAutoGG"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.autogg.option.useAutoGG.description")))
                        .binding(
                            Config.useAutoGG.asBinding()
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                    .option(Option.createBuilder<Int>()
                        .name(Text.translatable("mrc.config.category.automsg.group.autogg.option.ggDelayTicks"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.autogg.option.ggDelayTicks.description")))
                        .binding(
                            Config.ggDelayTicks.asBinding()
                        )
                        .controller {
                            IntegerSliderControllerBuilder.create(it)
                                .range(0, 100)
                                .step(1)
                                .formatValue { i -> Text.of("${String.format("%.2f", i.toFloat()/20f)} seconds") }
                        }
                        .build())
                    .build())
                .group(ListOption.createBuilder<String>()
                    .name(Text.translatable("mrc.config.category.automsg.group.autoGGStrings"))
                    .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.autoGGStrings.description")))
                    .binding(
                        Config.autoGGStrings.asBinding()
                    )
                    .controller(StringControllerBuilder::create)
                    .initial("")
                    .build())
                .group(OptionGroup.createBuilder()
                    .name(Text.translatable("mrc.config.category.automsg.group.autogl"))
                    .description(OptionDescription.of(Text.of("mrc.config.category.automsg.group.autogl.description")))
                    .option(Option.createBuilder<Boolean>()
                        .name(Text.translatable("mrc.config.category.automsg.group.autogl.option.useAutoGL"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.autogl.option.useAutoGL.description")))
                        .binding(
                            Config.useAutoGL.asBinding()
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                    .option(Option.createBuilder<Int>()
                        .name(Text.translatable("mrc.config.category.automsg.group.autogl.option.glDelayTicks"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.autogl.option.glDelayTicks.description")))
                        .binding(
                            Config.glDelayTicks.asBinding()
                        )
                        .controller {
                            IntegerSliderControllerBuilder.create(it)
                                .range(0, 100)
                                .step(1)
                                .formatValue { i -> Text.of("${String.format("%.2f", i.toFloat()/20f)} seconds") }
                        }
                        .build())
                    .build())
                .group(ListOption.createBuilder<String>()
                    .name(Text.translatable("mrc.config.category.automsg.group.autoGLStrings"))
                    .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.autoGLStrings.description")))
                    .binding(
                        Config.autoGLStrings.asBinding()
                    )
                    .controller(StringControllerBuilder::create)
                    .initial("")
                    .build())
                .group(OptionGroup.createBuilder()
                    .name(Text.translatable("mrc.config.category.automsg.group.hidemessages"))
                    .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.hidemessages.description")))
                    .option(Option.createBuilder<Boolean>()
                        .name(Text.translatable("mrc.config.category.automsg.group.hidemessages.option.hideGGMessages"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.hidemessages.option.hideGGMessages.description")))
                        .binding(
                            Config.hideGGMessages.asBinding()
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                    .option(Option.createBuilder<Boolean>()
                        .name(Text.translatable("mrc.config.category.automsg.group.hidemessages.option.hideGLMessages"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.automsg.group.hidemessages.option.hideGLMessages.description")))
                        .binding(
                            Config.hideGLMessages.asBinding()
                        )
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                    .build())
                .build())
            .category(ConfigCategory.createBuilder()
                .name(Text.translatable("mrc.config.category.roundhud"))
                .group(OptionGroup.createBuilder()
                    .name(Text.translatable("mrc.config.category.roundhud.group.hudoptions"))
                    .option(Option.createBuilder<Boolean>()
                        .name(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.displayHud"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.displayHud.description")))
                        .binding(Config.displayHud.asBinding())
                        .controller(TickBoxControllerBuilder::create)
                        .build())
                    .option(Option.createBuilder<HudLocation>()
                        .name(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.hudLocation"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.hudLocation.description")))
                        .binding(Config.hudLocation.asBinding())
                        .controller { EnumControllerBuilder.create(it).enumClass(HudLocation::class.java) }
                        .build())
                    .option(Option.createBuilder<Int>()
                        .name(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.hudXMargin"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.hudXMargin.description")))
                        .binding(Config.hudXMargin.asBinding())
                        .controller(IntegerFieldControllerBuilder::create)
                        .build())
                    .option(Option.createBuilder<Int>()
                        .name(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.hudYMargin"))
                        .description(OptionDescription.of(Text.translatable("mrc.config.category.roundhud.group.hudoptions.option.hudYMargin.description")))
                        .binding(Config.hudYMargin.asBinding())
                        .controller(IntegerFieldControllerBuilder::create)
                        .build())
                    .build())
                .group(ListOption.createBuilder<HudElements>()
                    .name(Text.translatable("mrc.config.category.roundhud.group.hudElements"))
                    .description(OptionDescription.of(Text.translatable("mrc.config.category.roundhud.group.hudElements.description")))
                    .binding(Config.hudElements.asBinding())
                    .controller { EnumControllerBuilder.create(it).enumClass(HudElements::class.java) }
                    .initial(HudElements.ROUND_NUMBER)
                    .build()
                )
                .build())
            .save(Config::saveToFile)
            .build()
            .generateScreen(parent)
    }
}