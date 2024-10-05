package app.ditodev.decedeevent.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
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
        val eventId = intent.getIntExtra(Util.EXTRA_ID, 0)
        viewModel.fetchDetailEvents(eventId)


        viewModel.detailEvent.observe(this) { event ->
            if (event != null) {
                Glide.with(this)
                    .load(event.event?.imageLogo)
                    .placeholder(R.drawable.loading_indicator)
                    .error(R.drawable.error_image)
                    .into(binding.ivDetailImage)
                binding.tvDetailName.text = event.event?.name
                binding.tvOwnerName.text = event.event?.ownerName
                binding.tvBeginTime.text = event.event?.beginTime
                binding.tvQuota.text = (event.event?.quota?.minus(event.event.registrants!!)).toString()

                binding.tvDescription.text = event.event?.description?.let {
                    HtmlCompat.fromHtml(
                        it,
                        HtmlCompat.FROM_HTML_MODE_LEGACY
                    )
                }

                binding.btnShare.setOnClickListener {
                    val redirect = Intent.createChooser(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(event.event?.link)
                    }, null)

                    startActivity(redirect)
                }
            }
        }

        viewModel.isLoading.observe(
            this
        ) {
            binding.pbLoad.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}