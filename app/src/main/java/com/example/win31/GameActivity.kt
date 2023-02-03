package com.example.win31

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.win31.api.Api
import com.example.win31.model.Country
import com.example.win31.model.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    var listImages = ArrayList<Image>()
    var listWords = ArrayList<Country>()
    var currentScore = 0
    var currentWord: CharArray? = null
    var arrayList: ArrayList<String> = ArrayList()
    var a: Button? = null
    var b: Button? = null
    var c: Button? = null
    var d: Button? = null
    var e: Button? = null
    var f: Button? = null
    var g: Button? = null
    var h: Button? = null
    var i: Button? = null
    var j: Button? = null
    var k: Button? = null
    var l: Button? = null
    var m: Button? = null
    var n: Button? = null
    var o: Button? = null
    var p: Button? = null
    var q: Button? = null
    var r: Button? = null
    var s: Button? = null
    var t: Button? = null
    var u: Button? = null
    var v: Button? = null
    var w: Button? = null
    var x: Button? = null
    var y: Button? = null
    var z: Button? = null
    var tvWord: TextView? = null
    var stars: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val layout = findViewById<ConstraintLayout>(R.id.activity_game_layout)
        Glide.with(this).load("http://49.12.202.175/win31/phoneimg.png")
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    layout.background = resource
                }
            })
        tvWord = findViewById(R.id.text_view_word)
        getImages()
    }

    private fun getImages() {
        CoroutineScope(Dispatchers.IO).launch {
            val api = Api::class.java
            val apiInterface = Retrofit.Builder()
                .baseUrl("http://49.12.202.175/win31/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
            val call = apiInterface.getImages().awaitResponse()
            if (call.isSuccessful) {
                listImages.addAll(call.body()!!.images)
            }
            launch(Dispatchers.Main) {
                getWords()
                load()
            }
        }
    }

    private fun getWords() {
        CoroutineScope(Dispatchers.IO).launch {
            val api = Api::class.java
            val apiInterface = Retrofit.Builder()
                .baseUrl("http://49.12.202.175/win31/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
            val call = apiInterface.getWords().awaitResponse()
            if (call.isSuccessful) {
                listWords.addAll(call.body()!!.countries)
            }
            launch(Dispatchers.Main) {
                listWords.shuffle()
                val name = listWords[0].name
                currentWord = name.toCharArray()
                for (i in currentWord!!){
                    arrayList.add(i.toString())
                }
                stars = arrayList[0].padEnd(name.length, '*')
                tvWord?.text = stars
                initButton(stars!!)
            }
        }
    }

    private fun load() {
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(listImages[currentScore].img)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    imageView.background = resource
                }
            })
    }

    fun initButton(stars: String) {
        Log.i("arrayList", arrayList.toString())
        a = findViewById(R.id.a)
        b = findViewById(R.id.b)
        c = findViewById(R.id.c)
        d = findViewById(R.id.d)
        e = findViewById(R.id.e)
        f = findViewById(R.id.f)
        g = findViewById(R.id.g)
        h = findViewById(R.id.h)
        i = findViewById(R.id.i)
        j = findViewById(R.id.j)
        k = findViewById(R.id.k)
        l = findViewById(R.id.l)
        m = findViewById(R.id.m)
        n = findViewById(R.id.n)
        o = findViewById(R.id.o)
        p = findViewById(R.id.p)
        q = findViewById(R.id.q)
        r = findViewById(R.id.r)
        s = findViewById(R.id.s)
        t = findViewById(R.id.t)
        u = findViewById(R.id.u)
        v = findViewById(R.id.v)
        w = findViewById(R.id.w)
        x = findViewById(R.id.x)
        y = findViewById(R.id.y)
        z = findViewById(R.id.z)
        a?.setOnClickListener {
            tvWord = findViewById(R.id.text_view_word)
            val str = stars.replace(stars[1], 'a')
            for (i in arrayList.indices){
                Log.i("for", stars)
                if(arrayList[i]=="a"){
                    Log.i("stars", str)
                    tvWord?.text = str
                }
            }
        }
    }
}