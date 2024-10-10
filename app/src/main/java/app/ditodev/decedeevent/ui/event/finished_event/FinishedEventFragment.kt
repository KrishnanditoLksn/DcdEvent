package app.ditodev.decedeevent.ui.event.finished_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.ditodev.decedeevent.data.remote.api.response.ListEventsItem
import app.ditodev.decedeevent.databinding.FragmentFinishedEventBinding
import app.ditodev.decedeevent.ui.adapter.UpcomingEventAdapter

class FinishedEventFragment : Fragment() {

    private lateinit var binding: FragmentFinishedEventBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayout = LinearLayoutManager(requireActivity())
        binding.rvFinishedEvent.layoutManager = linearLayout
        val itemDecoration = DividerItemDecoration(requireActivity(), linearLayout.orientation)
        binding.rvFinishedEvent.addItemDecoration(itemDecoration)

        val viewModel = ViewModelProvider(this)[FinishedEventViewModel::class.java]

        viewModel.finishedListEvents.observe(
            viewLifecycleOwner
        ) {
            setFinishedEvents(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishedEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setFinishedEvents(listEvents: List<ListEventsItem>) {
        val adapter = UpcomingEventAdapter()
        adapter.submitList(listEvents.take(5))
        binding.rvFinishedEvent.adapter = adapter
    }
}