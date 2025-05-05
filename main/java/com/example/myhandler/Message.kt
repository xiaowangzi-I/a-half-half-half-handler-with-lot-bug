package com.example.myhandler

import android.os.Bundle
import java.util.Objects

/**
 * description ： TODO:类的作用
 * author : 王子旻
 * email : 2024210997@qq.com
 * date : 2025/5/4 23:32
 */
class Message {
    var target:Handler?=null
    var time:Long=0
    var any:Any?=null
    var next:Message?=null

}