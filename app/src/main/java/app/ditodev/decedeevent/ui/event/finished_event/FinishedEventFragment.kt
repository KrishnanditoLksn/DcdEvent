package app.ditodev.decedeevent.ui.event.finished_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.ditodev.decedeevent.databinding.FragmentFinishedEventBinding

class FinishedEventFragment : Fragment() {

    private lateinit var binding: FragmentFinishedEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]
        binding = FragmentFinishedEventBinding.inflate(inflater, container, false)
        return binding.root
    }
}