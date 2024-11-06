package com.example.pertemuan6november

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pertemuan6november.databinding.ActivityMainBinding
import com.example.pertemuan6november.model.Authors
import com.example.pertemuan6november.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val responseAuthors = client.getAllAuthors()
        val authorList = ArrayList<String>()

        responseAuthors.enqueue(object : Callback<List<Authors>> {
            override fun onResponse(
                p0: Call<List<Authors>>,
                p1: Response<List<Authors>>
            ) {
                Log.d("loaddata", "success: onResponse: ${p1.body()}")
                for (i in p1.body()!!) {
                    authorList.add(i.name)
                }
                val listAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, authorList)
                binding.lvNama.adapter = listAdapter
            }

            override fun onFailure(
                p0: Call<List<Authors>>,
                p1: Throwable
            ) {
                Log.d("loaddata", "error: onResponse: ${p1.message}")

                Toast.makeText(this@MainActivity, "koneksi error", Toast.LENGTH_SHORT).show()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}