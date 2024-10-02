package app.ditodev.decedeevent.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import app.ditodev.decedeevent.R
import app.ditodev.decedeevent.databinding.ActivityDetailEventBinding
import app.ditodev.decedeevent.utils.Util
import com.bumptech.glide.Glide

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailEventBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this)[DetailEventViewModel::class.java]
        val eventId = intent.getIntExtra(Util.EXTRA_ID , 0)
        Log.d("IniHalamanDetail", "onCreate: $eventId")
        viewModel.fetchDetailEvents(eventId)

        viewModel.listEvent.observe(this) { event ->
            if (event != null) {
                Glide.with(this)
                    .load(event)
                    .into(binding.ivDetailImage)
                Log.d("IniRespondariApi", "onCreate: ${event.name}")
                binding.tvDetailName.text = event.name
                binding.tvOwnerName.text = event.ownerName
            }
        }

//        viewModel.isLoading.observe(
//            this
//        ) {
//            binding.pbLoad.visibility = if (it) View.VISIBLE else View.GONE
//        }
    }
}