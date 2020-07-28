/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.tabs

import com.google.gson.JsonParser
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.utils.ClientUtils
import net.ccbluex.liquidbounce.utils.item.ItemUtils
import net.ccbluex.liquidbounce.utils.misc.HttpUtils
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import java.util.*

@SideOnly(Side.CLIENT)
class HeadsTab : CreativeTabs("头颅") {

    // List of heads
    private val heads = ArrayList<ItemStack>()

    /**
     * Constructor of heads tab
     */
    init {
        this.backgroundImageName = "item_search.png"

        loadHeads()
    }

    /**
     * Load all heads from the database
     */
    private fun loadHeads() {
        try {
            ClientUtils.getLogger().info("加载玩家の头...")

            val headsConfiguration = JsonParser().parse(HttpUtils.get("${LiquidBounce.CLIENT_CLOUD}/heads.json"))

            if (!headsConfiguration.isJsonObject) return

            val headsConf = headsConfiguration.asJsonObject

            if (headsConf.get("enabled").asBoolean) {
                val url = headsConf.get("url").asString

                ClientUtils.getLogger().info("已从 $url 加载玩家的头...")

                val headsElement = JsonParser().parse(HttpUtils.get(url))

                if (!headsElement.isJsonObject) {
                    ClientUtils.getLogger().error("发生错误， 头颅Json数据不是一个Json对象!")
                    return
                }

                val headsObject = headsElement.asJsonObject

                for ((_, value) in headsObject.entrySet()) {
                    val headElement = value.asJsonObject

                    heads.add(ItemUtils.createItem("skull 1 3 {display:{Name:\"${headElement.get("name").asString}\"},SkullOwner:{Id:\"${headElement.get("uuid").asString}\",Properties:{textures:[{Value:\"${headElement.get("value").asString}\"}]}}}"))
                }

                ClientUtils.getLogger().info("已从玩家头颅数据库加载 " + heads.size + " 个头颅.")
            } else
                ClientUtils.getLogger().info("头颅被禁用.")
        } catch (e: Exception) {
            ClientUtils.getLogger().error("读取头颅时发生错误.", e)
        }
    }

    /**
     * Add all items to tab
     *
     * @param itemList list of tab items
     */
    override fun displayAllReleventItems(itemList: MutableList<ItemStack>) {
        itemList.addAll(heads)
    }

    /**
     * Return icon item of tab
     *
     * @return icon item
     */
    override fun getTabIconItem(): Item = Items.skull

    /**
     * Return name of tab
     *
     * @return tab name
     */
    override fun getTranslatedTabLabel() = "头颅"

    /**
     * @return searchbar status
     */
    override fun hasSearchBar() = true
}