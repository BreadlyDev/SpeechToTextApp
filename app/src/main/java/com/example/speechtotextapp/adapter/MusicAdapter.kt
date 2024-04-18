import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.speechtotextapp.databinding.ListItemBinding
import com.example.speechtotextapp.responses.AudioResponse
import com.example.speechtotextapp.ui.song.SongDetailActivity

class MusicAdapter : ListAdapter<AudioResponse, MusicAdapter.Holder>(Comparator()) {

    class Holder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var mediaPlayer: MediaPlayer? = null
        private var isPlaying: Boolean = false

        fun bind(product: AudioResponse) {
            binding.title.text = product.title

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SongDetailActivity::class.java)
                intent.putExtra("id", product.id)
                itemView.context.startActivity(intent)
            }

        }

        fun bindMediaPlayer(mediaPlayer: MediaPlayer?) {
            this.mediaPlayer = mediaPlayer
        }

        fun stopMediaPlayer() {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            isPlaying = false
        }
    }

    class Comparator : DiffUtil.ItemCallback<AudioResponse>() {
        override fun areItemsTheSame(oldItem: AudioResponse, newItem: AudioResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AudioResponse, newItem: AudioResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.bindMediaPlayer(currentList[position].mediaPlayer)
    }

    override fun onViewRecycled(holder: Holder) {
        super.onViewRecycled(holder)
        holder.stopMediaPlayer()
    }
}
