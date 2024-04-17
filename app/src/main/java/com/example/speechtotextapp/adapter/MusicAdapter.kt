import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.speechtotextapp.databinding.ListItemBinding
import com.example.speechtotextapp.responses.AudioResponse

class MusicAdapter : ListAdapter<AudioResponse, MusicAdapter.Holder>(Comparator()) {
    private var currentMediaPlayer: MediaPlayer? = null

    class Holder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var mediaPlayer: MediaPlayer
        var isPlaying: Boolean = false

        fun bind(product: AudioResponse, currentMediaPlayer: MediaPlayer?) {
            binding.title.text = product.title
            mediaPlayer = MediaPlayer()

            binding.idIBPlay.setOnClickListener{
                if (isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                } else {
                    currentMediaPlayer?.pause() // Pause the currently playing song
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                        isPlaying = false
                    } else {
                        mediaPlayer.apply {
                            setAudioStreamType(AudioManager.STREAM_MUSIC)
                            setDataSource(product.file)
                            prepare()
                            start()
                            this@Holder.isPlaying = true
                        }
                    }
                }
            }

        }

        fun stopMediaPlayer() {
            mediaPlayer.stop()
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
        holder.bind(product, currentMediaPlayer)
        currentMediaPlayer = holder.mediaPlayer
    }

    override fun onViewRecycled(holder: Holder) {
        super.onViewRecycled(holder)
        holder.stopMediaPlayer()
    }
}
