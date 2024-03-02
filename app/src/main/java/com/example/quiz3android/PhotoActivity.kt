package com.example.quiz3android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class PhotoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter
    private val photoList = mutableListOf<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        photoAdapter = PhotoAdapter(photoList)
        recyclerView.adapter = photoAdapter

        // Make API request using Volley (you can use Retrofit or other libraries)
        val url = "https://jsonplaceholder.typicode.com/photos"

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val jsonObject = response.getJSONObject(i)
                    val photo = Photo(
                        jsonObject.getInt("albumId"),
                        jsonObject.getInt("id"),
                        jsonObject.getString("title"),
                        jsonObject.getString("url"),
                        jsonObject.getString("thumbnailUrl")
                    )
                    photoList.add(photo)
                }
                photoAdapter.notifyDataSetChanged()
            },
            { error ->
                // Handle error
            }
        )

        // Add the request to the RequestQueue
        Volley.newRequestQueue(this).add(jsonArrayRequest)
    }
}
