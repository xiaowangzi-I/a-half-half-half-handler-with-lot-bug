package com.example.myhandler




/**
 * description ： TODO:类的作用
 * author : 王子旻
 * email : 2024210997@qq.com
 * date : 2025/5/4 23:32
 */
class Looper private constructor(){
   var mQueue: MessageQueue? = null
    init {
       mQueue= MessageQueue()
    }

   companion object {
      private val mThreadLocal: ThreadLocal<Looper> = ThreadLocal()

      fun getLooper(): Looper? {
         return mThreadLocal.get()
      }

      fun prepare(){
         if(mThreadLocal.get()!=null){
            throw RuntimeException(
                    "Only one Looper may be created per thread")
         }
         mThreadLocal.set(Looper())
      }

      fun loop(){
         val mLooper: Looper = getLooper()
            ?: throw RuntimeException(
                    "No Looper; Looper.prepare() wasn't called on this thread.")
            val queue: MessageQueue?=mLooper.mQueue
         while(true) {
             val msg: Message?= queue?.next()
            if (msg?.target == null)
               continue
            msg.target!!.handleMessage(msg)
         }
      }

   }

}


