package com.project.projetoconvidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Guest")
class GuestModel{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0

    @ColumnInfo(name = "name")
    val name : String = ""

    @ColumnInfo(name = "presence")
    val presence : Boolean = false

}