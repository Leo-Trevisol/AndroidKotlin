package com.project.estudandokotlinudemy.lambda



interface EventListener{
    fun click()
}

class Form{
    fun clickJava(e : EventListenerJava){}
    fun clickKotlin(e : EventListener){}
}

class Main{

    private val name = "Leo"

    fun main(){

        Form().clickJava {
            this.name
            println("hello")
        }

        Form().clickKotlin(object : EventListener{
            override fun click() {
            }
        })


    }
}
