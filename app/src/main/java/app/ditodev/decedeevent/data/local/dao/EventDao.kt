package app.ditodev.decedeevent.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import app.ditodev.decedeevent.data.local.entity.EventEntity

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getListEvents(): LiveData<List<EventEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFinishedEvent(event: List<EventEntity>)


    @Query("SELECT * FROM event WHERE id = :id")
    fun getFavoriteEventById(id: String): LiveData<EventEntity>

    @Update
    suspend fun updateEvent(event: EventEntity)

    @Query("DELETE FROM event WHERE  id = :id")
    suspend fun deleteAll(id: String)


    @Query("SELECT EXISTS(SELECT * FROM event WHERE id = :eventId)")
    fun isEventFavorite(eventId: String): LiveData<Boolean>
}