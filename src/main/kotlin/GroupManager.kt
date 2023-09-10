package top.kotori

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
    override fun onEnable() {
        logger.info { "Group Manager loaded" }

        GlobalEventChannel.subscribeAlways<MemberJoinEvent.Active> {
            group.sendMessage(At(user) + " 欢迎新人帕！")
        }
        GlobalEventChannel.subscribeAlways<MemberLeaveEvent.Quit> {
            group.sendMessage("${user.nick}(${user.id}) 退群了帕～")
        }
    }
}