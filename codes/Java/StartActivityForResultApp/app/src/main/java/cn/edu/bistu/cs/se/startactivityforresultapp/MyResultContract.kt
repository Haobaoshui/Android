package cn.edu.bistu.cs.se.startactivityforresultapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class MyResultContract :ActivityResultContract<String,String?>() {
    override fun createIntent(context: Context, input: String): Intent {
        val intent= Intent(context,AnotherActivity::class.java)
        intent.putExtra("name",input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if(resultCode==Activity.RESULT_OK){
            if(intent!=null)
                return intent.getStringExtra("result")
        }
        return null
    }
}