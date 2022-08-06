package com.aneke.peter.pbmob.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertHit(hit : Hit)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertHits(hits : List<Hit>)

    @Query("SELECT * FROM hit")
    suspend fun fetchHits() : List<Hit>

    @Query("SELECT * FROM hit")
    fun observeHits() : LiveData<List<Hit>>

    @Query("SELECT * FROM hit")
    fun letHitsFlow() : Flow<List<Hit>>

    @Query("SELECT * FROM hit WHERE tags LIKE '%' || :search || '%'")
    fun searchHitsFlow(search: String) : Flow<List<Hit>>

    @Query("SELECT * FROM hit WHERE tags LIKE '%' || :search || '%'")
    fun searchAndObserveHits(search: String) : LiveData<List<Hit>>

    @Transaction
    @Query("SELECT * FROM hit WHERE searchQuery LIKE '%' || :search || '%'")
    suspend fun searchHits(search: String) : List<Hit>

    @Query("DELETE FROM hit")
    suspend fun clearHits()

    @Delete
    suspend fun deleteHit(hit: Hit)

    @Delete
    suspend fun deleteHits(hits : List<Hit>)

}