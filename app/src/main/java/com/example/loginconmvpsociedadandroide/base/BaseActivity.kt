package com.example.loginconmvpsociedadandroide.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(getLayout())
    }

    @LayoutRes
    abstract fun getLayout():Int

    fun Context.toast(context : Context = applicationContext, message : String?, duration : Int = Toast.LENGTH_LONG){
        Toast.makeText(context, message, duration).show()
    }
}