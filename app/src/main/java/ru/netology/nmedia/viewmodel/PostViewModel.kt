package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {

    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    private val _data = MutableLiveData(repository.getAll())
    val data: LiveData<List<Post>> get() = _data

    fun likeById(id: Long) {
        repository.likeById(id)
        _data.value = repository.getAll()
    }

    fun shareById(id: Long) {
        repository.shareById(id)
        _data.value = repository.getAll()
    }
}
