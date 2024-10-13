package app.ditodev.decedeevent.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.ditodev.decedeevent.data.local.dao.EventDao
import app.ditodev.decedeevent.data.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: EventDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): EventDatabase {
            if (INSTANCE == null) {
                synchronized(EventDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EventDatabase::class.java,
                        "Event.db"
                    )
                        .build()
                }
            }
            return INSTANCE as EventDatabase
        }
    }
}