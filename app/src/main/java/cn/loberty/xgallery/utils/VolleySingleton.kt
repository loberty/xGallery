package cn.loberty.xgallery.utils

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Create by WangChen on 2020/9/8
 *
 */
class VolleySingleton private constructor(context: Context){
    companion object{
        private var INSTANCE:VolleySingleton ?= null
        fun getInstance(context: Context) = synchronized(this){
                VolleySingleton(context).also { INSTANCE=it }
        }
    }

    val requestQueue:RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
}