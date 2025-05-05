package com.example.myhandler

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import java.util.PriorityQueue
import java.util.Comparator

class MessageQueue {
    var messageNum:Int=0
    private val lock = ReentrantLock()
    private val mEmptyQueue: Condition = lock.newCondition()
    private val mFullQueue: Condition = lock.newCondition()
    private val mMessages = PriorityQueue<Message>(Comparator { a, b -> a.time.compareTo(b.time) })

    fun enqueueMessage(msg: Message, uptimeMillis: Long) {
        msg.time = uptimeMillis
        lock.lock()
        try {
            while (mMessages.size == 50) { // 假设队列大小为50
                try {
                    mFullQueue.await()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            mMessages.add(msg)
            mEmptyQueue.signalAll()
        } finally {
            lock.unlock()
        }
    }

    fun next(): Message? {
        var message: Message? = null
        lock.lock()
        try {
            while (mMessages.isEmpty()) {
                try {
                    mEmptyQueue.await()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            message = mMessages.poll() // 取出并移除最早的消息
            mFullQueue.signalAll()
        } finally {
            lock.unlock()
        }
        return message
    }
}