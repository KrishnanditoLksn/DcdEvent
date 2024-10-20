package app.ditodev.decedeevent.utils

import androidx.recyclerview.widget.DiffUtil
import app.ditodev.decedeevent.data.remote.api.response.ListEventsItem

object Util {
    const val EXTRA_ID = "extra_id"
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
        override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}