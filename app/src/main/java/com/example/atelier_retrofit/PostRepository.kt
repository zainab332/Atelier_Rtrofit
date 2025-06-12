package com.example.atelier_retrofit

class PostRepository {
    private val apiService = RetrofitInstance.Api

    suspend fun getPosts(): List<Post>? {
        return try {
            apiService.getPosts()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            apiService.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }
    suspend fun createPost(post: Post): Post? {
        return try {
            apiService.createpost(post)
        } catch (e: Exception) {
            null
        }
    }

}
