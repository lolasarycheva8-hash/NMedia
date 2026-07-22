package ru.netology.nmedia
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private var likes = 10
    private var isLiked = false
    private var shares = 5
    private val views = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLike = findViewById<ImageButton>(R.id.btnLike)
        val tvLikes = findViewById<TextView>(R.id.tvLikes)
        val btnShare = findViewById<ImageButton>(R.id.btnShare)
        val tvShares = findViewById<TextView>(R.id.tvShares)
        val tvViews = findViewById<TextView>(R.id.tvViews)
        tvLikes.text = formatCount(likes)
        tvShares.text = formatCount(shares)
        tvViews.text = formatCount(views)
        btnLike.setOnClickListener {
            if (isLiked) {
                likes--
                isLiked = false
                btnLike.setImageResource(R.drawable.ic_like)
            } else {
                likes++
                isLiked = true
                btnLike.setImageResource(R.drawable.ic_like_filled)
            }
            tvLikes.text = formatCount(likes)
        }
        btnShare.setOnClickListener {
            shares++
            tvShares.text = formatCount(shares)
        }
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