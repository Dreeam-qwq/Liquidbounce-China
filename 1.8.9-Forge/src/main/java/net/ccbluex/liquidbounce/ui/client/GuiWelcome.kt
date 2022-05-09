/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.ui.client

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.features.module.modules.render.ClickGUI
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import java.awt.Color

class GuiWelcome : GuiScreen() {

    override fun initGui() {
        this.buttonList.add(GuiButton(1, this.width / 2 - 100, height - 40, "Ok"))
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawBackground(0)

        val font = Fonts.font35

        font.drawCenteredString("感谢安装并使用汉化水影客户端!", width / 2F, height / 8F + 70, 0xffffff, true)
        font.drawCenteredString("如果你是第一次用水影, 这里有一些可能对你有帮助的信息.", width / 2F, height / 8F + 70 + font.FONT_HEIGHT, 0xffffff, true)

        font.drawCenteredString("§lClickGUI:", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 3, 0xffffff, true)
        font.drawCenteredString("点击 ${Keyboard.getKeyName(LiquidBounce.moduleManager[ClickGUI::class.java]!!.keyBind)} 来打开ClickGUI", width / 2F, height / 8 + 80F + font.FONT_HEIGHT * 4, 0xffffff, true)
        font.drawCenteredString("右键模块的 '+'号来配置他们.", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 5, 0xffffff, true)
        font.drawCenteredString("Hover a module to see it's description.", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 6, 0xffffff, true)

        font.drawCenteredString("§l基础指令:", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 8, 0xffffff, true)
        font.drawCenteredString(".bind <module> <key> / .bind <module> none", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 9, 0xffffff, true)
        font.drawCenteredString(".autosettings load <name> / .autosettings list", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 10, 0xffffff, true)

        font.drawCenteredString("§l需要帮助? 欢迎联系我们!", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 12, 0xffffff, true)
        font.drawCenteredString("油管: https://youtube.com/ccbluex", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 13, 0xffffff, true)
        font.drawCenteredString("推特: https://twitter.com/ccbluex", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 14, 0xffffff, true)
        font.drawCenteredString("论坛: https://forum.ccbluex.net/", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 15, 0xffffff, true)
        font.drawCenteredString("中文水影交流群: 群号", width / 2F, height / 8F + 80 + font.FONT_HEIGHT * 17, 0xffffff, true)

        super.drawScreen(mouseX, mouseY, partialTicks)

        // Title
        GL11.glScalef(2F, 2F, 2F)
        Fonts.font40.drawCenteredString("欢迎!", width / 2 / 2F, height / 8F / 2 + 20, Color(0, 140, 255).rgb, true)
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        if (Keyboard.KEY_ESCAPE == keyCode)
            return

        super.keyTyped(typedChar, keyCode)
    }

    override fun actionPerformed(button: GuiButton) {
        if (button.id == 1) {
            mc.displayGuiScreen(GuiMainMenu())
        }
    }
}
