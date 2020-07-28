/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.script

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.ClientUtils
import java.io.File
import java.io.FileFilter

class ScriptManager {

    val scripts = mutableListOf<Script>()

    val scriptsFolder = File(LiquidBounce.fileManager.dir, "scripts")
    private val scriptFileExtension = ".js"

    /**
     * Loads all scripts inside the scripts folder.
     */
    fun loadScripts() {
        if(!scriptsFolder.exists())
            scriptsFolder.mkdir()

        scriptsFolder.listFiles(FileFilter { it.name.endsWith(scriptFileExtension) }).forEach { loadScript(it) }
    }

    /**
     * Unloads all scripts.
     */
    fun unloadScripts() {
        scripts.clear()
    }

    /**
     * Loads a script from a file.
     */
    fun loadScript(scriptFile : File) {
        try {
            scripts.add(Script(scriptFile))
            ClientUtils.getLogger().info("[脚本接口] 成功加载脚本 '${scriptFile.name}'.")
        } catch(t : Throwable) {
            ClientUtils.getLogger().error("[脚本接口] 加载脚本 '${scriptFile.name}'失败.", t)
        }
    }

    /**
     * Enables all scripts.
     */
    fun enableScripts() {
        scripts.forEach { it.onEnable() }
    }

    /**
     * Disables all scripts.
     */
    fun disableScripts() {
        scripts.forEach { it.onDisable() }
    }

    /**
     * Imports a script.
     * @param file JavaScript file to be imported.
     */
    fun importScript(file : File) {
        val scriptFile = File(scriptsFolder, file.name)
        file.copyTo(scriptFile)

        loadScript(scriptFile)
        ClientUtils.getLogger().info("[脚本接口]  成功导入脚本 '${scriptFile.name}'.")
    }

    /**
     * Deletes a script.
     * @param script Script to be deleted.
     */
    fun deleteScript(script : Script) {
        script.onDisable()
        scripts.remove(script)
        script.scriptFile.delete()

        ClientUtils.getLogger().info("[脚本接口]  成功删除脚本 '${script.scriptFile.name}'.")
    }

    /**
     * Reloads all scripts.
     */
    fun reloadScripts() {
        disableScripts()
        unloadScripts()
        loadScripts()
        enableScripts()

        ClientUtils.getLogger().info("[脚本接口]  成功重载所有脚本.")
    }
}