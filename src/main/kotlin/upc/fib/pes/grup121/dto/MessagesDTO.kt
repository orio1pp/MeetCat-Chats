package upc.fib.pes.grup121.dto

import javax.management.monitor.StringMonitor

data class MessagesDTO(
    val chatId: Long,
    val username:String,
    val page: Int,
    val size: Int
) {
}