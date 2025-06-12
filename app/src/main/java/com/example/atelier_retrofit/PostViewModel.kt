package com.example.atelier_retrofit
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _post = MutableLiveData<Post?>()
    val post: LiveData<Post?> = _post

    private val _postAdded = MutableLiveData<Boolean>()
    val postAdded: LiveData<Boolean> get() = _postAdded




    fun fetchPosts() {
        viewModelScope.launch {
            val response = repository.getPosts()
            _posts.value = response ?: emptyList()
        }
    }

    fun fetchPostById(postId: Int) {
        viewModelScope.launch {
            val response = repository.getPostById(postId)
            _post.value = response
        }
    }


    fun addPost(post: Post) {
        viewModelScope.launch {
            val response = repository.createPost(post)
            _postAdded.value = response != null
        }
    }

}
