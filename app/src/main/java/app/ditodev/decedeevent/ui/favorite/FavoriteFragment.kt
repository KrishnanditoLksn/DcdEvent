package app.ditodev.decedeevent.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.ditodev.decedeevent.data.remote.api.response.ListEventsItem
import app.ditodev.decedeevent.databinding.FragmentFavoriteBinding
import app.ditodev.decedeevent.ui.adapter.UpcomingEventAdapter
import app.ditodev.decedeevent.utils.Factory

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFavoriteEvent.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFavoriteEvent.addItemDecoration(itemDecoration)

        val factory = Factory.getInstance(requireActivity())
        val viewModel =ViewModelProvider(this,factory)[FavoriteEventViewModel::class.java]
        val adapter = UpcomingEventAdapter()


        viewModel.getListEvent().observe(viewLifecycleOwner){
            users ->
            val items = arrayListOf<ListEventsItem>()
            users.map {
                val item = ListEventsItem(id = it.id.toInt() , name = it.name , imageLogo = it.mediaCover)
                items.add(item)
                adapter.submitList(items)
            }
        }
        binding.rvFavoriteEvent.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
}