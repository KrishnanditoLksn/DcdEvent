package app.ditodev.decedeevent.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
    @field:ColumnInfo(name = "id")
    @field:PrimaryKey(autoGenerate = false)
    var id: String = "",

    @field:ColumnInfo("name")
    var name: String = "",

    @field:ColumnInfo("description")
    var description: String = "",

    @field:ColumnInfo("mediaCover")
    var mediaCover: String = "",
)