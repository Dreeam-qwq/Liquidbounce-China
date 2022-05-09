package net.ccbluex.liquidbounce.utils

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.modules.misc.NameProtect
import net.ccbluex.liquidbounce.features.module.modules.misc.Spammer
import net.ccbluex.liquidbounce.utils.misc.HttpUtils.get
import net.ccbluex.liquidbounce.utils.misc.StringUtils
import net.ccbluex.liquidbounce.utils.render.ColorUtils.translateAlternateColorCodes
import net.ccbluex.liquidbounce.value.*
import org.lwjgl.input.Keyboard
//TODO 提示空格统一规范
/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
object SettingsUtils {

    /**
     * Execute settings [script]
     */
    fun executeScript(script: String) {
        script.lines().filter { it.isNotEmpty() && !it.startsWith('#') }.forEachIndexed { index, s ->
            val args = s.split(" ").toTypedArray()

            if (args.size <= 1) {
                ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §c配置脚本 '$index' 行出现语法错误.\n§8§l行数: §7$s")
                return@forEachIndexed
            }

            when (args[0]) {
                "chat" -> ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §e${translateAlternateColorCodes(StringUtils.toCompleteString(args, 1))}")
                "unchat" -> ClientUtils.displayChatMessage(translateAlternateColorCodes(StringUtils.toCompleteString(args, 1)))

                "load" -> {
                    val urlRaw = StringUtils.toCompleteString(args, 1)
                    val url = if (urlRaw.startsWith("http"))
                        urlRaw
                    else
                        "${LiquidBounce.CLIENT_CLOUD}/settings/${urlRaw.toLowerCase()}"

                    try {
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §7正在从 §a§l$url§7加载配置...")
                        executeScript(get(url))
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §7成功从 §a§l$url§7加载配置.")
                    } catch (e: Exception) {
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §7无法从 §a§l$url§7加载配置.")
                    }
                }

                "targetPlayer", "targetPlayers" -> {
                    EntityUtils.targetPlayer = args[1].equals("true", ignoreCase = true)
                    ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将 §a§l${args[0]}§7 设置为 §c§l${EntityUtils.targetPlayer}§7.")
                }

                "targetMobs" -> {
                    EntityUtils.targetMobs = args[1].equals("true", ignoreCase = true)
                    ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将 §a§l${args[0]}§7 设置为 §c§l${EntityUtils.targetMobs}§7.")
                }

                "targetAnimals" -> {
                    EntityUtils.targetAnimals = args[1].equals("true", ignoreCase = true)
                    ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将  §a§l${args[0]}§7 设置为 §c§l${EntityUtils.targetAnimals}§7.")
                }

                "targetInvisible" -> {
                    EntityUtils.targetInvisible = args[1].equals("true", ignoreCase = true)
                    ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将 §a§l${args[0]}§7 设置为 §c§l${EntityUtils.targetInvisible}§7.")
                }

                "targetDead" -> {
                    EntityUtils.targetDead = args[1].equals("true", ignoreCase = true)
                    ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将 §a§l${args[0]}§7 设置为 §c§l${EntityUtils.targetDead}§7.")
                }

                else -> {
                    if (args.size != 3) {
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §c在配置脚本中出现语法错误 '$index' .\n§8§l行数: §7$s")
                        return@forEachIndexed
                    }

                    val moduleName = args[0]
                    val valueName = args[1]
                    val value = args[2]
                    val module = LiquidBounce.moduleManager.getModule(moduleName)

                    if (module == null) {
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §c无法找到 §a§l$moduleName§c 模块!")
                        return@forEachIndexed
                    }

                    if (valueName.equals("toggle", ignoreCase = true)) {
                        module.state = value.equals("true", ignoreCase = true)
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §a§l${module.name} §7was toggled §c§l${if (module.state) "on" else "off"}§7.")
                        return@forEachIndexed
                    }

                    if (valueName.equals("bind", ignoreCase = true)) {
                        module.keyBind = Keyboard.getKeyIndex(value)
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将 §a§l${module.name} §7绑定到 §c§l${Keyboard.getKeyName(module.keyBind)}§7键.")
                        return@forEachIndexed
                    }

                    val moduleValue = module.getValue(valueName)
                    if (moduleValue == null) {
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §c无法在 §a§l$moduleName §c模块中找到 §a§l$valueName §c值.")
                        return@forEachIndexed
                    }

                    try {
                        when (moduleValue) {
                            is BoolValue -> moduleValue.changeValue(value.toBoolean())
                            is FloatValue -> moduleValue.changeValue(value.toFloat())
                            is IntegerValue -> moduleValue.changeValue(value.toInt())
                            is TextValue -> moduleValue.changeValue(value)
                            is ListValue -> moduleValue.changeValue(value)
                        }

                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] 已将 §a§l${module.name}§7 模块 §8§l${moduleValue.name}§7值设置为 §c§l$value§7.")
                    } catch (e: Exception) {
                        ClientUtils.displayChatMessage("§7[§3§l自动配置§7] §a§l${e.javaClass.name}§7(${e.message}) §cAn Exception occurred while setting §a§l$value§c to §a§l${moduleValue.name}§c in §a§l${module.name}§c.")
                    }
                }
            }
        }

        LiquidBounce.fileManager.saveConfig(LiquidBounce.fileManager.valuesConfig)
    }

    /**
     * Generate settings script
     */
    fun generateScript(values: Boolean, binds: Boolean, states: Boolean): String {
        val stringBuilder = StringBuilder()

        LiquidBounce.moduleManager.modules.filter {
            it.category !== ModuleCategory.RENDER && it !is NameProtect && it !is Spammer
        }.forEach {
            if (values)
                it.values.forEach { value -> stringBuilder.append(it.name).append(" ").append(value.name).append(" ").append(value.get()).append("\n") }

            if (states)
                stringBuilder.append(it.name).append(" toggle ").append(it.state).append("\n")

            if (binds)
                stringBuilder.append(it.name).append(" bind ").append(Keyboard.getKeyName(it.keyBind)).append("\n")
        }

        return stringBuilder.toString()
    }
}
