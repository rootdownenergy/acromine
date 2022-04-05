package com.rootdown.dev.adidev_albertson.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AcromineDao {

    @Query("SELECT COUNT(*) FROM acromine_item")
    fun getCount(): Int

    @Query("select * from acromine_item")
    fun getAcromine(): Flow<List<AcrominDataItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(acromine: AcrominDataItem)

    @Query("SELECT * FROM acromine_item WHERE sf LIKE :queryString")
    fun acromineItemById(queryString: String?): LiveData<AcrominDataItem>

    @Query("DELETE FROM acromine_item WHERE id = :acroId")
    suspend fun deleteAcromineItem(acroId: Int)

    @Query("SELECT * FROM acromine_item WHERE id = :acroId")
    fun acromineById(acroId: Int): LiveData<AcrominDataItem>
}