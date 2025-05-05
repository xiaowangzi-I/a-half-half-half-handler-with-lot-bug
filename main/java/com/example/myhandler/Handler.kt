package com.example.myhandler

import android.os.SystemClock

/**
 * description ： TODO:类的作用
 * author : 王子旻
 * email : 2024210997@qq.com
 * date : 2025/5/4 23:33
 */
open class Handler() {
    private var mQueue: MessageQueue?=null
    private var mLooper: Looper?=null

    init{
        mLooper=Looper.getLooper()
        mQueue= mLooper?.mQueue
    }

    fun sendMessage(msg : Message) {
        sendMessageDelayed(msg,0)
    }

    private fun sendMessageDelayed(msg:Message, delayMillis:Long) {
        if(delayMillis<0){
            sendMessageAtTime(msg,SystemClock.uptimeMillis())
        }else{
            sendMessageAtTime(msg, SystemClock.uptimeMillis() + delayMillis)
        }
    }

    private fun sendMessageAtTime(msg: Message, uptimeMillis: Long) {
        val queue: MessageQueue? = mQueue
        enqueueMessage(queue, msg, uptimeMillis)
    }

    private fun enqueueMessage(queue: MessageQueue?, msg: Message, uptimeMillis:Long) {
        msg.target=this
        queue?.enqueueMessage(msg,uptimeMillis)
    }

    open fun handleMessage(msg: Message){

    }
}