package edwylugo.woopsicredi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import edwylugo.woopsicredi.R
import edwylugo.woopsicredi.databinding.LayoutEventItemBinding
import edwylugo.woopsicredi.model.response.Event
import edwylugo.woopsicredi.utils.CurrencyFormatter
import edwylugo.woopsicredi.view.EventActivity

class EventsAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<LayoutEventItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_event_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: LayoutEventItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            binding.title = event.title
            binding.price = CurrencyFormatter.toFormat(event.price ?: 0.0)

            Glide.with(binding.root.context)
                .load(event.imageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivEvent)

            binding.root.setOnClickListener { EventActivity.launch(it.context, event.id) }
        }

    }
}