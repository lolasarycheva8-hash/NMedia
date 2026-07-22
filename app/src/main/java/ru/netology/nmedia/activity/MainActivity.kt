package ru.netology.nmedia.activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLike = findViewById<ImageButton>(R.id.btnLike)
        val tvLikes = findViewById<TextView>(R.id.tvLikes)
        val btnShare = findViewById<ImageButton>(R.id.btnShare)
        val tvShares = findViewById<TextView>(R.id.tvShares)
        val tvViews = findViewById<TextView>(R.id.tvViews)

        viewModel.data.observe(this) { posts ->
            val post = posts.firstOrNull() ?: return@observe
            tvLikes.text = formatCount(post.likes)
            tvShares.text = formatCount(post.shares)
            tvViews.text = formatCount(post.views)
            btnLike.setImageResource(
                if (post.likedByMe) R.drawable.ic_like_filled else R.drawable.ic_like
            )
        }

        btnLike.setOnClickListener { viewModel.likeById(1L) }
        btnShare.setOnClickListener { viewModel.shareById(1L) }
    }

    fun formatCount(count: Int): String {
        return when {
            count < 1_000 -> count.toString()
            count < 10_000 -> "${count / 1_000}.${(count % 1_000) / 100}K"
            count < 1_000_000 -> "${count / 1_000}K"
            else -> "${count / 1_000_000}.${(count % 1_000_000) / 100_000}M"
        }
    }
}
