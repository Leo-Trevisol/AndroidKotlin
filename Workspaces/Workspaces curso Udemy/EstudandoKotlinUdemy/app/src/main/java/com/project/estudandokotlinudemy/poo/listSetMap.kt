package com.project.estudandokotlinudemy.poo

fun main(){

    val list = listOf(1,2,3,4,5)

    val list2 = listOf(1,"2",false,4,5)

    val list3 : MutableList<Int> = mutableListOf(1,2,3,4,5)

   // list.add(8)
    list3.add(8)

    list3.remove(1)
    list3.removeAt(0)
    list3.clear()

    //SET

    val set1 : Set<String> = setOf<String>("1","2","3", "3")
    val set2 : MutableSet<String> = mutableSetOf<String>("1","2","3")

    println(set1.toString())

    set2.add("5")
    set2.contains("1")

    //MAP

    val map1 : Map<String, String> = mapOf<String, String>(Pair("Franca", "Paris"), Pair("Alemanha", "Berlim"))
    val map2 : MutableMap<String, String> = mutableMapOf<String, String>()

    println(map1.entries)
    println(map1.values)

    map2["Brasil"] = "Brasilia"

    println(map2)

    map2.remove("Brasil")
    map2.contains("Brasil")
    map2.clear()


}