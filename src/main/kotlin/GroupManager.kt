package top.kotori

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.utils.info

object GroupManager : KotlinPlugin(
    JvmPluginDescription(
        id = "top.kotori.GroupManager",
        name = "GroupManager",
        version = "1.0.0",
    )
) {
    object GroupManager : AutoSavePluginConfig("GroupManager") {
        val groupJoinMessage by value("欢迎新人帕！")
        val groupLeaveMessage by value("退群了帕～")
    }

    override fun onEnable() {
        GroupManager.reload()
        logger.info { "Group Manager loaded" }

        GlobalEventChannel.subscribeAlways<MemberJoinEvent.Active> {
            group.sendMessage(At(user) + " ${GroupManager.groupJoinMessage}")
        }
        GlobalEventChannel.subscribeAlways<MemberLeaveEvent.Quit> {
            group.sendMessage("${user.nick}(${user.id}) ${GroupManager.groupLeaveMessage}")
        }
    }
}