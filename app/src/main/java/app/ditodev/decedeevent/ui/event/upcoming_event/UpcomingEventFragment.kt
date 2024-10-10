package app.ditodev.decedeevent.ui.event.upcoming_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.ditodev.decedeevent.data.remote.api.response.ListEventsItem
import app.ditodev.decedeevent.databinding.FragmentUpcomingEventBinding
import app.ditodev.decedeevent.ui.adapter.UpcomingEventAdapter

class UpcomingEventFragment : Fragment() {
    private lateinit var binding: FragmentUpcomingEventBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvEvent.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvEvent.addItemDecoration(itemDecoration)

        val viewModel = ViewModelProvider(this)[UpcomingEventViewModel::class.java]
        viewModel.listEvent.observe(
            viewLifecycleOwner
        ) {
            setupRecyclerView(it)
        }

        viewModel.isLoading.observe(
            viewLifecycleOwner
        ) {
            binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpcomingEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView(listEventsItem: List<ListEventsItem>) {
        val adapter = UpcomingEventAdapter()
        adapter.submitList(listEventsItem.take(5))
        binding.rvEvent.adapter = adapter
    }
}