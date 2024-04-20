import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.speechtotextapp.R
import com.example.speechtotextapp.databinding.SongCardBinding
import com.example.speechtotextapp.responses.AudioResponse

class MusicAdapter : ListAdapter<AudioResponse, MusicAdapter.Holder>(Comparator()) {
    private var currentMediaPlayer: MediaPlayer? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = SongCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    class Holder(private val binding: SongCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var mediaPlayer: MediaPlayer
        var isPlaying: Boolean = false

        init {
            mediaPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
            }
        }

        fun bind(song: AudioResponse, currentMediaPlayer: MediaPlayer?) {
            binding.txtTitle.text = song.title
            binding.btnMore.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val bundle = Bundle().apply {
                    putString("songTitle", song.title)
                    putString("songSubtitles", song.subtitles)
                    putString("songAudio", song.file)
                }
                navController.navigate(R.id.action_SongFragment_toSongDescriptionFragment, bundle)

            }
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
                            reset()
                            setDataSource(song.file)
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
            mediaPlayer.release()
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
}
