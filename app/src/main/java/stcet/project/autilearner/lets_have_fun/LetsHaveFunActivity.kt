package stcet.project.autilearner.lets_have_fun

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.R

class LetsHaveFunActivity : AppCompatActivity()  {
    private var gameActive = true
    private var flag = false

    private lateinit var status: TextView
    private lateinit var buttonNextRound: Button


    // player representation
    // 0 - X
    // 1 - O

    private var activePlayer = 0 // - x (By Default)
    private var gameState = arrayOf(2,2,2,2,2,2,2,2,2)

    // state meanings --
    // 0 - X
    // 1 - O
    // 2 - Null

    private var winPosition = arrayOf(
        arrayOf(0,1,2), arrayOf(3,4,5), arrayOf(6,7,8),
        arrayOf(0,3,6), arrayOf(1,4,7), arrayOf(2,5,8),
        arrayOf(0,4,8), arrayOf(2,4,6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_have_fun)
        buttonNextRound = findViewById(R.id.buttonNextRound)

        buttonNextRound.setOnClickListener {
            gameReset()
        }
    }

    @SuppressLint("SetTextI18n")
    fun playerTap(view: View) {
        if(!flag){
            val img: ImageView = view as ImageView
            val tappedImage: Int = Integer.parseInt(img.tag.toString())
            if(!gameActive){
                gameReset()

            }
            if(gameState[tappedImage] == 2 && gameActive){
                gameState[tappedImage] = activePlayer
                img.translationY = -1000f
                if(activePlayer == 0){
                    img.setImageResource(R.drawable.x)
                    activePlayer = 1
                    status = findViewById(R.id.status)
                    status.text = "O's Turn - Tap to play"
                }
                else{
                    img.setImageResource(R.drawable.o)
                    activePlayer = 0
                    status = findViewById(R.id.status)
                    status.text = "X's Turn - Tap to play"
                }

                img.animate().translationYBy(1000f).duration = 300
            }
            // Check if any player has won
            for (i in winPosition){
                if(gameState[i[0]] == gameState[i[1]] &&
                    gameState[i[1]] == gameState[i[2]] &&
                    gameState[i[0]]!=2)
                {
                    gameActive = false
                    val winnerStr: String = if(gameState[i[0]]== 0)
                    {
                        "X has won"
                    } else{
                        "O has won"
                    }
                    //update the status bar for winner announcement
                    status = findViewById(R.id.status)
                    status.text = winnerStr
                    flag = true
                }
                else if(arrayFull(gameState))
                {
                    status = findViewById(R.id.status)
                    status.text = "Game Draw"

                }
            }
        }

    }

    private fun arrayFull(array: Array<Int>): Boolean {
        return array.all { it != 2 }
    }



    @SuppressLint("SetTextI18n")
    private fun gameReset() {
        gameActive = true
        flag = false
        activePlayer = 0

        for(i in gameState.indices){
            gameState[i] = 2
        }
        findViewById<ImageView>(R.id.t1)?.setImageResource(0)
        findViewById<ImageView>(R.id.t2)?.setImageResource(0)
        findViewById<ImageView>(R.id.t3)?.setImageResource(0)
        findViewById<ImageView>(R.id.t4)?.setImageResource(0)
        findViewById<ImageView>(R.id.t5)?.setImageResource(0)
        findViewById<ImageView>(R.id.t6)?.setImageResource(0)
        findViewById<ImageView>(R.id.t7)?.setImageResource(0)
        findViewById<ImageView>(R.id.t8)?.setImageResource(0)
        findViewById<ImageView>(R.id.t9)?.setImageResource(0)

        status = findViewById(R.id.status)
        status.text = "X's Turn - Tap to play"

    }
}