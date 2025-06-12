package com.example.atelier_retrofit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnFetch = findViewById<Button>(R.id.btnFetch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val etPostId = findViewById<EditText>(R.id.etPostId)
        val tvPostDetails = findViewById<TextView>(R.id.tvPostDetails)
        val btnAddPost = findViewById<Button>(R.id.btnAddPost)


        recyclerView.layoutManager = LinearLayoutManager(this)

        btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }

        btnSearch.setOnClickListener {
            val postId = etPostId.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)
            } else {
                tvPostDetails.text = "Veuillez entrer un ID valide."
            }
        }

        btnAddPost.setOnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        }

        // Observer liste des posts
        viewModel.posts.observe(this) { posts ->
            recyclerView.adapter = PostAdapter(posts)
        }

        // Observer un seul post
        viewModel.post.observe(this) { post ->
            if (post != null) {
                tvPostDetails.text = "ID: ${post.id}\nTitre: ${post.title}\nContenu: ${post.body}"
            } else {
                tvPostDetails.text = "Aucun post trouvé."
            }
        }
    }
}
