package com.example.win31

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.win31.api.Api
import com.example.win31.model.Country
import com.example.win31.model.Image
import com.onesignal.OneSignal
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
    var currentMistakes= 0
    var currentWordList: ArrayList<String> = ArrayList()
    var currentName: ArrayList<Char>? = null
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
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId("714b9f14-381d-4fc4-a93c-28d480557381")
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

        findViewById<Button>(R.id.button_again).setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }
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
                currentName = listWords[0].name.toList() as ArrayList<Char>
                for (i in currentName!!.indices){
                    currentWordList.add(currentName!![i].toString().lowercase())
                    currentName!![i]='*'
                }
                tvWord?.text = currentName!!.joinToString()
                initButton(currentWordList)
            }
        }
    }

    private fun load() {
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(listImages[currentMistakes].img)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    imageView.background = resource
                }
            })
    }

    fun initButton(currentWordList: ArrayList<String>) {
        Log.i("arrayList", currentWordList.toString())
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
            check('a', a!!)
        }
        b?.setOnClickListener {
            check('b', b!!)
        }
        c?.setOnClickListener {
            check('c', c!!)
        }
        d?.setOnClickListener {
            check('d', d!!)
        }
        e?.setOnClickListener {
            check('e', e!!)
        }
        f?.setOnClickListener {
            check('f', f!!)
        }
        g?.setOnClickListener {
            check('g', g!!)
        }
        h?.setOnClickListener {
            check('h', h!!)
        }
        i?.setOnClickListener {
            check('i', i!!)
        }
        j?.setOnClickListener {
            check('j', j!!)
        }
        k?.setOnClickListener {
            check('k', k!!)
        }
        l?.setOnClickListener {
            check('l', l!!)
        }
        m?.setOnClickListener {
            check('m', m!!)
        }
        n?.setOnClickListener {
            check('n', n!!)
        }
        o?.setOnClickListener {
            check('o', o!!)
        }
        p?.setOnClickListener {
            check('p', p!!)
        }
        q?.setOnClickListener {
            check('q', q!!)
        }
        r?.setOnClickListener {
            check('r', r!!)
        }
        s?.setOnClickListener {
            check('s', s!!)
        }
        t?.setOnClickListener {
            check('t', t!!)
        }
        u?.setOnClickListener {
            check('u', u!!)
        }
        v?.setOnClickListener {
            check('v', v!!)
        }
        w?.setOnClickListener {
            check('w', w!!)
        }
        x?.setOnClickListener {
            check('x', x!!)
        }
        y?.setOnClickListener {
            check('y', y!!)
        }
        z?.setOnClickListener {
            check('z', z!!)
        }
    }

    private fun check(value: Char, button: Button) {
        val textViewWinLos = findViewById<TextView>(R.id.textView_winlos)
        Log.i("currentMistakes", currentMistakes.toString())
        if (currentMistakes==7){
            textViewWinLos?.setTextColor(resources.getColor(R.color.red))
            textViewWinLos.text = resources.getText(R.string.loss)
            disableButtons()
        } else if(!currentName!!.contains('*')){
            textViewWinLos?.setTextColor(resources.getColor(R.color.green))
            textViewWinLos.text = resources.getText(R.string.win)
            disableButtons()
        } else {
            if (currentWordList.contains(value.toString())){
                for(i in currentWordList.indices){
                    if (currentWordList[i]==value.toString()){
                        currentName!![i]=value
                    }
                }
                if(!currentName!!.contains('*')){
                    textViewWinLos?.setTextColor(resources.getColor(R.color.green))
                    textViewWinLos.text = resources.getText(R.string.win)
                    disableButtons()
                }
            } else {
                currentMistakes+=1
                if (currentMistakes==7){
                    textViewWinLos?.setTextColor(resources.getColor(R.color.red))
                    textViewWinLos.text = resources.getText(R.string.loss)
                    disableButtons()
                }
                load()
            }
            button.visibility = View.INVISIBLE
            tvWord?.text = currentName?.joinToString()
        }
    }

    private fun disableButtons(){
        a?.isEnabled = false
        b?.isEnabled = false
        c?.isEnabled = false
        d?.isEnabled = false
        e?.isEnabled = false
        f?.isEnabled = false
        g?.isEnabled = false
        h?.isEnabled = false
        i?.isEnabled = false
        j?.isEnabled = false
        k?.isEnabled = false
        l?.isEnabled = false
        m?.isEnabled = false
        n?.isEnabled = false
        o?.isEnabled = false
        p?.isEnabled = false
        q?.isEnabled = false
        r?.isEnabled = false
        s?.isEnabled = false
        t?.isEnabled = false
        u?.isEnabled = false
        v?.isEnabled = false
        w?.isEnabled = false
        x?.isEnabled = false
        y?.isEnabled = false
        z?.isEnabled = false
    }
}