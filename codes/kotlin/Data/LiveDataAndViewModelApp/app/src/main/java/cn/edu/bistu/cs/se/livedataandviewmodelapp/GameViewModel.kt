package cn.edu.bistu.cs.se.livedataandviewmodelapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

const val SCORE_INCREASE=10
class GameViewModel: ViewModel() {
   // val score: MutableLiveData<Int> by lazy {
   //     MutableLiveData<Int>()
   // }

    private val _score = MutableLiveData(0)
    val score: LiveData<Int>
        get() = _score

    private lateinit var currentChar: String

    private fun getNextChar() {
        val charset = "ABCDE"
        currentChar = charset.random().toString();
    }
    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    fun isUserCharCorrect(userChar: String): Boolean {
        if (userChar.equals(currentChar, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun nextChar(): Boolean {
        getNextChar()
        return true
    }

    fun reinitializeData() {
        _score.value = 0
        getNextChar()
    }

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextChar()
    }


}