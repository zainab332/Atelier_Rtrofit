package com.example.atelier_retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") postId: Int): Post

    @POST("posts")
    suspend fun createpost(@Body post: Post):Post
}
