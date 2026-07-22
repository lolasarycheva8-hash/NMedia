package ru.netology.nmedia.repository

import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {

    private var posts = listOf(
        Post(
            id = 1L,
            author = "Нетология. Университет интернет-проф...",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов.\n\nhttp://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 10,
            shares = 5,
            views = 5
        )
    )

    override fun getAll(): List<Post> = posts

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) post.copy(
                likedByMe = !post.likedByMe,
                likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
            ) else post
        }
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) post.copy(shares = post.shares + 1) else post
        }
    }
}
