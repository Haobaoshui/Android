package cn.edu.bistu.cs.se.jnitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.edu.bistu.cs.se.jnitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'jnitest' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun myJNITest(): String

    companion object {
        // Used to load the 'jnitest' library on application startup.
        init {
            System.loadLibrary("jnitest")
        }
    }
}