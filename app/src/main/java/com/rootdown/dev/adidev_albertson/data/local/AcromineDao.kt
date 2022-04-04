package com.rootdown.dev.adidev_albertson.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AcromineDao {

    @Query("SELECT COUNT(*) FROM acromine_item")
    fun getCount(): Int

    @Query("select * from acromine_item")
    fun getAcromine(): LiveData<List<AcrominDataItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(acromine: AcrominDataItem)

    @Query("select * from acromine_item")
    fun getStrainLs(): LiveData<List<AcrominDataItem>>

    @Query("SELECT * FROM acromine_item WHERE sf LIKE :queryString")
    fun acromineItemById(queryString: String?): LiveData<AcrominDataItem>
}