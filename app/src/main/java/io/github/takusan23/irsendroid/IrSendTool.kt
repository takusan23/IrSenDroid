package io.github.takusan23.irsendroid

import android.content.Context
import android.hardware.ConsumerIrManager

object IrSendTool {

    /**
     * 赤外線送信
     * @param context Context
     * @param pattern 赤外線パターン
     * */
    fun sendIr(context: Context, pattern: List<Int>) {
        val consumerIrManager = context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager
        // 赤外線利用可能かどうか
        if (consumerIrManager.hasIrEmitter()) {
            // 利用可能なら送信
            consumerIrManager.transmit(38000, pattern.toIntArray())
        }
    }

}