package app.ditodev.decedeevent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.ditodev.decedeevent.data.api.response.ListEventsItem
import app.ditodev.decedeevent.databinding.EventRowBinding
import app.ditodev.decedeevent.utils.Util
import com.bumptech.glide.Glide

class UpcomingEventAdapter :
    ListAdapter<ListEventsItem, UpcomingEventAdapter.ViewHolder>(Util.DIFF_CALLBACK) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = EventRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val events = getItem(position)
        holder.bind(events)
    }


    class ViewHolder(private val binding: EventRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(events: ListEventsItem) {
            Glide.with(binding.root)
                .load(events.imageLogo)
                .into(binding.ivEventImage)
            binding.tvDetailName.text = events.name
        }
    }
}