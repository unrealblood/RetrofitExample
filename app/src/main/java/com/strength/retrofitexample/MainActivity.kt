package com.strength.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesAPI::class.java)

        mainTextView = findViewById(R.id.main_text_view)

        GlobalScope.launch {
            val result = quotesAPI.getQuotes(1)

            val quoteList = result.body()
            if(quoteList != null) {
                quoteList.results.forEach {
                    Log.i("MainDebug", "Quote: ${it.content}")
                }
            }
        }
    }
}