package com.example.myhandler

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(){
    val send1: TextView?=null
    val send2: TextView ?=null
    val send3: TextView ?=null
    val send4: TextView ?=null
    val send5: TextView?=null
    val get1: TextView ?=null
    val get2: TextView?=null
    val get3: TextView?=null
    val get4: TextView?=null
    val get5: TextView?=null
    val looper: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
        send()
    }


    private fun init(){
        Looper.prepare()
        val send1: TextView = findViewById(R.id.send1)
        val send2: TextView = findViewById(R.id.send2)
        val send3: TextView = findViewById(R.id.send3)
        val send4: TextView = findViewById(R.id.send4)
        val send5: TextView = findViewById(R.id.send5)
        val get1: TextView = findViewById(R.id.get1)
        val get2: TextView = findViewById(R.id.get2)
        val get3: TextView = findViewById(R.id.get3)
        val get4: TextView = findViewById(R.id.get4)
        val get5: TextView = findViewById(R.id.get5)
        val looper: TextView = findViewById(R.id.looper)
    }

    private fun send(){
        val msg:Message=Message()
        var handler:Handler=Handler()

        val t :Thread=Thread {

            for (i in 1..5) {
                msg.any = Thread.currentThread().name + "send:" + i.toString()
                handler.sendMessage(msg)

                Thread.sleep(1000)

            }
        }
        t.start()

         handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                getText(msg,msg.any)
                Log.d("ttttttt", "${Thread.currentThread().name} --receiver-- ${msg.any}")
            }
        }
        Looper.loop()

    }
    fun getText(msg:Message?, who: Any?){
        when(who){
            1->{
                val m: String = msg?.any.toString()
                get1?.text = m
                Thread.sleep(5000)
            }
            2->{
                val m: String = msg?.any.toString()
                get2?.text = m
                Thread.sleep(5000)
            }
            3->{
                val m: String = msg?.any.toString()
                get3?.text = m
                Thread.sleep(5000)
            }
            4->{
                val m: String = msg?.any.toString()
                get4?.text = m
                Thread.sleep(5000)
            }
            5->{
                val m: String = msg?.any.toString()
                get5?.text = m
                Thread.sleep(5000)
            }
        }
    }
}