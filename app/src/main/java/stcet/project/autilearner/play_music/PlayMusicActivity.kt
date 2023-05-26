package stcet.project.autilearner.play_music
import stcet.project.autilearner.R
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class PlayMusicActivity : AppCompatActivity() {
    private lateinit var d1: Button
    private lateinit var d2: Button
    private lateinit var d3: Button
    private lateinit var d4: Button
    private lateinit var d5: Button
    private lateinit var d6: Button
    private lateinit var d7: Button
    private lateinit var d8: Button
    private lateinit var d9: Button
    private lateinit var d10: Button
    private lateinit var d11: Button
    private lateinit var d12: Button
    private lateinit var d13: Button
    private lateinit var d14: Button
    private lateinit var d15: Button
    private lateinit var d16: Button

    private lateinit var t1: Button
    private lateinit var t2: Button
    private lateinit var t3: Button
    private lateinit var t4: Button
    private lateinit var t5: Button
    private lateinit var t6: Button
    private lateinit var t7: Button
    private lateinit var t8: Button
    private lateinit var t9: Button
    private lateinit var t10: Button

    private lateinit var editText: EditText

    private lateinit var playButton: Button

    private lateinit var switch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_music)

        d1 = findViewById(R.id.d1)
        d2 = findViewById(R.id.d2)
        d3 = findViewById(R.id.d3)
        d4 = findViewById(R.id.d4)
        d5 = findViewById(R.id.d5)
        d6 = findViewById(R.id.d6)
        d7 = findViewById(R.id.d7)
        d8 = findViewById(R.id.d8)
        d9 = findViewById(R.id.d9)
        d10 = findViewById(R.id.d10)
        d11 = findViewById(R.id.d11)
        d12 = findViewById(R.id.d12)
        d13 = findViewById(R.id.d13)
        d14 = findViewById(R.id.d14)
        d15 = findViewById(R.id.d15)
        d16 = findViewById(R.id.d16)

        t1 = findViewById(R.id.t1)
        t2 = findViewById(R.id.t2)
        t3 = findViewById(R.id.t3)
        t4 = findViewById(R.id.t4)
        t5 = findViewById(R.id.t5)
        t6 = findViewById(R.id.t6)
        t7 = findViewById(R.id.t7)
        t8 = findViewById(R.id.t8)
        t9 = findViewById(R.id.t9)
        t10 = findViewById(R.id.t10)

        editText = findViewById(R.id.editText)
        playButton = findViewById(R.id.playButton)
        switch = findViewById(R.id.switchCompat)


        val soundPool = SoundPool.Builder().setMaxStreams(6).build()

        switch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {

                    val sd1 = soundPool.load(this@PlayMusicActivity, R.raw.a, 1)
                    val sd2 = soundPool.load(this@PlayMusicActivity, R.raw.b, 1)
                    val sd3 = soundPool.load(this@PlayMusicActivity, R.raw.c, 1)
                    val sd4 = soundPool.load(this@PlayMusicActivity, R.raw.d, 1)
                    val sd5 = soundPool.load(this@PlayMusicActivity, R.raw.e, 1)
                    val sd6 = soundPool.load(this@PlayMusicActivity, R.raw.f, 1)
                    val sd7 = soundPool.load(this@PlayMusicActivity, R.raw.g, 1)
                    val sd8 = soundPool.load(this@PlayMusicActivity, R.raw.h, 1)
                    val sd9 = soundPool.load(this@PlayMusicActivity, R.raw.i, 1)
                    val sd10 = soundPool.load(this@PlayMusicActivity, R.raw.j, 1)
                    val sd11 = soundPool.load(this@PlayMusicActivity, R.raw.k, 1)
                    val sd12 = soundPool.load(this@PlayMusicActivity, R.raw.l, 1)
                    val sd13 = soundPool.load(this@PlayMusicActivity, R.raw.m, 1)
                    val sd14 = soundPool.load(this@PlayMusicActivity, R.raw.n, 1)
                    val sd15 = soundPool.load(this@PlayMusicActivity, R.raw.o, 1)
                    val sd16 = soundPool.load(this@PlayMusicActivity, R.raw.p, 1)

                    val st1 = soundPool.load(this@PlayMusicActivity, R.raw.q, 1)
                    val st2 = soundPool.load(this@PlayMusicActivity, R.raw.r, 1)
                    val st3 = soundPool.load(this@PlayMusicActivity, R.raw.s, 1)
                    val st4 = soundPool.load(this@PlayMusicActivity, R.raw.t, 1)
                    val st5 = soundPool.load(this@PlayMusicActivity, R.raw.u, 1)
                    val st6 = soundPool.load(this@PlayMusicActivity, R.raw.v, 1)
                    val st7 = soundPool.load(this@PlayMusicActivity, R.raw.w, 1)
                    val st8 = soundPool.load(this@PlayMusicActivity, R.raw.x, 1)
                    val st9 = soundPool.load(this@PlayMusicActivity, R.raw.y, 1)
                    val st10 = soundPool.load(this@PlayMusicActivity, R.raw.z, 1)

                    fun playMusic(alphabet: Char) {
                        when (alphabet) {

                            'a', 'A' -> {
                                soundPool.play(sd1, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'b', 'B' -> {
                                soundPool.play(sd2, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'c', 'C' -> {
                                soundPool.play(sd3, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'd', 'D' -> {
                                soundPool.play(sd4, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'e', 'E' -> {
                                soundPool.play(sd5, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'f', 'F' -> {
                                soundPool.play(sd6, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'g', 'G' -> {
                                soundPool.play(sd7, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'h', 'H' -> {
                                soundPool.play(sd8, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'i', 'I' -> {
                                soundPool.play(sd9, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'j', 'J' -> {
                                soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'k', 'K' -> {
                                soundPool.play(sd11, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'l', 'L' -> {
                                soundPool.play(sd12, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'm', 'M' -> {
                                soundPool.play(sd13, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'n', 'N' -> {
                                soundPool.play(sd14, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'o', 'O' -> {
                                soundPool.play(sd15, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'p', 'P' -> {
                                soundPool.play(sd16, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'q', 'Q' -> {
                                soundPool.play(st1, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'r', 'R' -> {
                                soundPool.play(st2, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            's', 'S' -> {
                                soundPool.play(st3, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            't', 'T' -> {
                                soundPool.play(st4, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'u', 'U' -> {
                                soundPool.play(st5, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'v', 'V' -> {
                                soundPool.play(st6, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'w', 'W' -> {
                                soundPool.play(st7, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'x', 'X' -> {
                                soundPool.play(st8, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'y', 'Y' -> {
                                soundPool.play(st9, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'z', 'Z' -> {
                                soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                            }


                        }
                        Thread.sleep(/* millis = */ 1000)
                    }


                    d1.setOnClickListener {
                        soundPool.play(sd1, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d2.setOnClickListener {
                        soundPool.play(sd2, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d3.setOnClickListener {
                        soundPool.play(sd3, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d4.setOnClickListener {
                        soundPool.play(sd4, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d5.setOnClickListener {
                        soundPool.play(sd5, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d6.setOnClickListener {
                        soundPool.play(sd6, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d7.setOnClickListener {
                        soundPool.play(sd7, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d8.setOnClickListener {
                        soundPool.play(sd8, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d9.setOnClickListener {
                        soundPool.play(sd9, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d10.setOnClickListener {
                        soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d11.setOnClickListener {
                        soundPool.play(sd11, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d12.setOnClickListener {
                        soundPool.play(sd12, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d13.setOnClickListener {
                        soundPool.play(sd13, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d14.setOnClickListener {
                        soundPool.play(sd14, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d15.setOnClickListener {
                        soundPool.play(sd15, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d16.setOnClickListener {
                        soundPool.play(sd16, 1.0f, 1.0f, 0, 0, 1.0f)
                    }

                    //t1 piano button
                    t1.setOnClickListener {
                        soundPool.play(st1, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t2.setOnClickListener {
                        soundPool.play(st2, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t3.setOnClickListener {
                        soundPool.play(st3, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t4.setOnClickListener {
                        soundPool.play(st4, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t5.setOnClickListener {
                        soundPool.play(st5, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t6.setOnClickListener {
                        soundPool.play(st6, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t7.setOnClickListener {
                        soundPool.play(st7, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t8.setOnClickListener {
                        soundPool.play(st8, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t9.setOnClickListener {
                        soundPool.play(st9, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t10.setOnClickListener {
                        soundPool.play(st10, 1.0f, 1.0f, 0, 0, 1.0f)
                    }

                    fun playByWord(input: String) {
                        for (i in input) {
                            playMusic(i)
                        }
                    }

                    fun isAlphabetic(input: String): Boolean {
                        val regex = Regex("^[a-zA-Z]+$")
                        return regex.matches(input)
                    }

                    playButton.setOnClickListener {
                        val input = editText.text.toString().trim()
                        val alphabet = isAlphabetic(input)
                        if (alphabet) {
                            playByWord(input)
                        } else {
                            editText.error = "Invalid"
                        }
                    }
                }
                else{
                    val sd1 = soundPool.load(this@PlayMusicActivity, R.raw.d1, 1)
                    val sd2 = soundPool.load(this@PlayMusicActivity, R.raw.d2, 1)
                    val sd3 = soundPool.load(this@PlayMusicActivity, R.raw.d3, 1)
                    val sd4 = soundPool.load(this@PlayMusicActivity, R.raw.d4, 1)
                    val sd5 = soundPool.load(this@PlayMusicActivity, R.raw.d5, 1)
                    val sd6 = soundPool.load(this@PlayMusicActivity, R.raw.d6, 1)
                    val sd7 = soundPool.load(this@PlayMusicActivity, R.raw.d7, 1)
                    val sd8 = soundPool.load(this@PlayMusicActivity, R.raw.d8, 1)
                    val sd9 = soundPool.load(this@PlayMusicActivity, R.raw.d9, 1)
                    val sd10 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val sd11 = soundPool.load(this@PlayMusicActivity, R.raw.d11, 1)
                    val sd12 = soundPool.load(this@PlayMusicActivity, R.raw.d12, 1)
                    val sd13 = soundPool.load(this@PlayMusicActivity, R.raw.d13, 1)
                    val sd14 = soundPool.load(this@PlayMusicActivity, R.raw.d14, 1)
                    val sd15 = soundPool.load(this@PlayMusicActivity, R.raw.d15, 1)
                    val sd16 = soundPool.load(this@PlayMusicActivity, R.raw.d16, 1)

                    val st1 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st2 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st3 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st4 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st5 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st6 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st7 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st8 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st9 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
                    val st10 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)

                    fun playMusic(alphabet: Char) {
                        when (alphabet) {

                            'a', 'A' -> {
                                soundPool.play(sd1, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'b', 'B' -> {
                                soundPool.play(sd2, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'c', 'C' -> {
                                soundPool.play(sd3, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'd', 'D' -> {
                                soundPool.play(sd4, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'e', 'E' -> {
                                soundPool.play(sd5, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'f', 'F' -> {
                                soundPool.play(sd6, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'g', 'G' -> {
                                soundPool.play(sd7, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'h', 'H' -> {
                                soundPool.play(sd8, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'i', 'I' -> {
                                soundPool.play(sd9, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'j', 'J' -> {
                                soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'k', 'K' -> {
                                soundPool.play(sd11, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'l', 'L' -> {
                                soundPool.play(sd12, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'm', 'M' -> {
                                soundPool.play(sd13, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'n', 'N' -> {
                                soundPool.play(sd14, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'o', 'O' -> {
                                soundPool.play(sd15, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'p', 'P' -> {
                                soundPool.play(sd16, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'q', 'Q' -> {
                                soundPool.play(st1, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'r', 'R' -> {
                                soundPool.play(st2, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            's', 'S' -> {
                                soundPool.play(st3, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            't', 'T' -> {
                                soundPool.play(st4, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'u', 'U' -> {
                                soundPool.play(st5, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'v', 'V' -> {
                                soundPool.play(st6, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'w', 'W' -> {
                                soundPool.play(st7, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'x', 'X' -> {
                                soundPool.play(st8, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'y', 'Y' -> {
                                soundPool.play(st9, 1.0f, 1.0f, 0, 0, 1.0f)
                            }
                            'z', 'Z' -> {
                                soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                            }


                        }
                        Thread.sleep(/* millis = */ 2000)
                    }


                    d1.setOnClickListener {
                        soundPool.play(sd1, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d2.setOnClickListener {
                        soundPool.play(sd2, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d3.setOnClickListener {
                        soundPool.play(sd3, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d4.setOnClickListener {
                        soundPool.play(sd4, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d5.setOnClickListener {
                        soundPool.play(sd5, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d6.setOnClickListener {
                        soundPool.play(sd6, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d7.setOnClickListener {
                        soundPool.play(sd7, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d8.setOnClickListener {
                        soundPool.play(sd8, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d9.setOnClickListener {
                        soundPool.play(sd9, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d10.setOnClickListener {
                        soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d11.setOnClickListener {
                        soundPool.play(sd11, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d12.setOnClickListener {
                        soundPool.play(sd12, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d13.setOnClickListener {
                        soundPool.play(sd13, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d14.setOnClickListener {
                        soundPool.play(sd14, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d15.setOnClickListener {
                        soundPool.play(sd15, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    d16.setOnClickListener {
                        soundPool.play(sd16, 1.0f, 1.0f, 0, 0, 1.0f)
                    }

                    //t1 piano button
                    t1.setOnClickListener {
                        soundPool.play(st1, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t2.setOnClickListener {
                        soundPool.play(st2, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t3.setOnClickListener {
                        soundPool.play(st3, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t4.setOnClickListener {
                        soundPool.play(st4, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t5.setOnClickListener {
                        soundPool.play(st5, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t6.setOnClickListener {
                        soundPool.play(st6, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t7.setOnClickListener {
                        soundPool.play(st7, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t8.setOnClickListener {
                        soundPool.play(st8, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t9.setOnClickListener {
                        soundPool.play(st9, 1.0f, 1.0f, 0, 0, 1.0f)
                    }
                    t10.setOnClickListener {
                        soundPool.play(st10, 1.0f, 1.0f, 0, 0, 1.0f)
                    }

                    fun playByWord(input: String) {
                        for (i in input) {
                            playMusic(i)
                        }
                    }

                    fun isAlphabetic(input: String): Boolean {
                        val regex = Regex("^[a-zA-Z]+$")
                        return regex.matches(input)
                    }

                    playButton.setOnClickListener {
                        val input = editText.text.toString().trim()
                        val alphabet = isAlphabetic(input)
                        if (alphabet) {
                            playByWord(input)
                        } else {
                            editText.error = "Invalid"
                        }
                    }
                }
            }
        })

        val sd1 = soundPool.load(this@PlayMusicActivity, R.raw.d1, 1)
        val sd2 = soundPool.load(this@PlayMusicActivity, R.raw.d2, 1)
        val sd3 = soundPool.load(this@PlayMusicActivity, R.raw.d3, 1)
        val sd4 = soundPool.load(this@PlayMusicActivity, R.raw.d4, 1)
        val sd5 = soundPool.load(this@PlayMusicActivity, R.raw.d5, 1)
        val sd6 = soundPool.load(this@PlayMusicActivity, R.raw.d6, 1)
        val sd7 = soundPool.load(this@PlayMusicActivity, R.raw.d7, 1)
        val sd8 = soundPool.load(this@PlayMusicActivity, R.raw.d8, 1)
        val sd9 = soundPool.load(this@PlayMusicActivity, R.raw.d9, 1)
        val sd10 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val sd11 = soundPool.load(this@PlayMusicActivity, R.raw.d11, 1)
        val sd12 = soundPool.load(this@PlayMusicActivity, R.raw.d12, 1)
        val sd13 = soundPool.load(this@PlayMusicActivity, R.raw.d13, 1)
        val sd14 = soundPool.load(this@PlayMusicActivity, R.raw.d14, 1)
        val sd15 = soundPool.load(this@PlayMusicActivity, R.raw.d15, 1)
        val sd16 = soundPool.load(this@PlayMusicActivity, R.raw.d16, 1)

        val st1 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st2 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st3 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st4 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st5 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st6 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st7 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st8 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st9 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)
        val st10 = soundPool.load(this@PlayMusicActivity, R.raw.d10, 1)

        fun playMusic(alphabet: Char) {
            when (alphabet) {

                'a', 'A' -> {
                    soundPool.play(sd1, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'b', 'B' -> {
                    soundPool.play(sd2, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'c', 'C' -> {
                    soundPool.play(sd3, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'd', 'D' -> {
                    soundPool.play(sd4, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'e', 'E' -> {
                    soundPool.play(sd5, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'f', 'F' -> {
                    soundPool.play(sd6, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'g', 'G' -> {
                    soundPool.play(sd7, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'h', 'H' -> {
                    soundPool.play(sd8, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'i', 'I' -> {
                    soundPool.play(sd9, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'j', 'J' -> {
                    soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'k', 'K' -> {
                    soundPool.play(sd11, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'l', 'L' -> {
                    soundPool.play(sd12, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'm', 'M' -> {
                    soundPool.play(sd13, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'n', 'N' -> {
                    soundPool.play(sd14, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'o', 'O' -> {
                    soundPool.play(sd15, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'p', 'P' -> {
                    soundPool.play(sd16, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'q', 'Q' -> {
                    soundPool.play(st1, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'r', 'R' -> {
                    soundPool.play(st2, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                's', 'S' -> {
                    soundPool.play(st3, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                't', 'T' -> {
                    soundPool.play(st4, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'u', 'U' -> {
                    soundPool.play(st5, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'v', 'V' -> {
                    soundPool.play(st6, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'w', 'W' -> {
                    soundPool.play(st7, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'x', 'X' -> {
                    soundPool.play(st8, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'y', 'Y' -> {
                    soundPool.play(st9, 1.0f, 1.0f, 0, 0, 1.0f)
                }
                'z', 'Z' -> {
                    soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
                }


            }
            Thread.sleep(/* millis = */ 2000)
        }


        d1.setOnClickListener {
            soundPool.play(sd1, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d2.setOnClickListener {
            soundPool.play(sd2, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d3.setOnClickListener {
            soundPool.play(sd3, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d4.setOnClickListener {
            soundPool.play(sd4, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d5.setOnClickListener {
            soundPool.play(sd5, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d6.setOnClickListener {
            soundPool.play(sd6, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d7.setOnClickListener {
            soundPool.play(sd7, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d8.setOnClickListener {
            soundPool.play(sd8, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d9.setOnClickListener {
            soundPool.play(sd9, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d10.setOnClickListener {
            soundPool.play(sd10, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d11.setOnClickListener {
            soundPool.play(sd11, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d12.setOnClickListener {
            soundPool.play(sd12, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d13.setOnClickListener {
            soundPool.play(sd13, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d14.setOnClickListener {
            soundPool.play(sd14, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d15.setOnClickListener {
            soundPool.play(sd15, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        d16.setOnClickListener {
            soundPool.play(sd16, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        //t1 piano button
        t1.setOnClickListener {
            soundPool.play(st1, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t2.setOnClickListener {
            soundPool.play(st2, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t3.setOnClickListener {
            soundPool.play(st3, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t4.setOnClickListener {
            soundPool.play(st4, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t5.setOnClickListener {
            soundPool.play(st5, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t6.setOnClickListener {
            soundPool.play(st6, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t7.setOnClickListener {
            soundPool.play(st7, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t8.setOnClickListener {
            soundPool.play(st8, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t9.setOnClickListener {
            soundPool.play(st9, 1.0f, 1.0f, 0, 0, 1.0f)
        }
        t10.setOnClickListener {
            soundPool.play(st10, 1.0f, 1.0f, 0, 0, 1.0f)
        }

        fun playByWord(input: String) {
            for (i in input) {
                playMusic(i)
            }
        }

        fun isAlphabetic(input: String): Boolean {
            val regex = Regex("^[a-zA-Z]+$")
            return regex.matches(input)
        }

        playButton.setOnClickListener {
            val input = editText.text.toString().trim()
            val alphabet = isAlphabetic(input)
            if (alphabet) {
                playByWord(input)
            } else {
                editText.error = "Invalid"
            }
        }
    }
}









