package chistotest.test.kotlinretrofitgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var listView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById (R.id.pagination_list) // <ListView> <View>

        val builder = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()

        val client = retrofit.create<GitHubClient>(GitHubClient::class.java) // !! ?
        val call = client.reposForUser("dedushkaded")

        call.enqueue(object : Callback<List<GitHubRepo>> {
            override fun onResponse(call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>>) {
                val repos = response.body()

                listView!!.adapter = GitHubRepoAdapter(this@MainActivity, repos!!)
            }

            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error :(", Toast.LENGTH_SHORT).show()
            }
        })



    }
}
