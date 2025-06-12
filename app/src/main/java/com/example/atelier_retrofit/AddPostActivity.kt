package com.example.atelier_retrofit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddPostActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_post)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etBody = findViewById<EditText>(R.id.etBody)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val body = etBody.text.toString().trim()

            if (title.isNotEmpty() && body.isNotEmpty()) {
                val newPost = Post(userId = 1, id = 0, title = title, body = body)
                viewModel.addPost(newPost)
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.postAdded.observe(this) { isSuccess ->
            if (isSuccess == true) {
                Toast.makeText(this, "Post ajouté avec succès", Toast.LENGTH_SHORT).show()
                finish()
            } else if (isSuccess == false) {
                Toast.makeText(this, "Échec de l'ajout", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
