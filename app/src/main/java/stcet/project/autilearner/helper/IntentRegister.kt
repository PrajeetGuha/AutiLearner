package stcet.project.autilearner.helper

import androidx.appcompat.app.AppCompatActivity
import stcet.project.autilearner.emotion.EmotionActivity
import stcet.project.autilearner.learnNPlay.LearnNPlayActivity
import stcet.project.autilearner.letsHaveFun.LetsHaveFunActivity
import stcet.project.autilearner.music.MusicActivity

class IntentRegister {

    var hashMap = hashMapOf(1 to LearnNPlayActivity::class.java,
                            2 to MusicActivity::class.java,
                            3 to EmotionActivity::class.java,
                            4 to LetsHaveFunActivity::class.java)

    public fun intentActivityClass(intentID : Int): Class<out AppCompatActivity>? {
        return hashMap[intentID]
    }
}