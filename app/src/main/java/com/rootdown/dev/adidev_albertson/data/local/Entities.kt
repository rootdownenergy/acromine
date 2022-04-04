package com.rootdown.dev.adidev_albertson.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "acromine_item")
data class AcrominDataItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var lfs: MutableList<String> = mutableListOf(),
    var sf: String? = ""
) : Parcelable

private const val SEPARATOR = ","
class ListStringConverter {
    @TypeConverter
    fun fromString(value: String?): MutableList<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromList(list: MutableList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}