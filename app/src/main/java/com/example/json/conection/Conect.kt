package com.example.json.conection

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class Conect (var context: Context){

    init {
        this.context
    }



    fun parser( text :TextView)
    {

        val queue = Volley.newRequestQueue(context)
        val url: String = "https://api.github.com/search/users?q=eyehunt"
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                val jsonObj: JSONObject = JSONObject(strResp)
                var status : Int = jsonObj.optInt("total_count")
                var jsArray : JSONArray? = null
                if (status == 1)
                {
                    jsArray = jsonObj.getJSONArray("items")
                }else
                {
                    Toast.makeText(context,"", Toast.LENGTH_LONG).show()
                }
                var str_user: String = ""
                for (i in 0 until jsArray!!.length()) {
                    var jsonInner: JSONObject = jsArray.getJSONObject(i)
                    str_user = str_user + "\n" + jsonInner
                    text.text = str_user
                    Log.i("pejman",str_user.count().toString())
                }
            },
            Response.ErrorListener { text!!.text = "moshel pish amad" })
        queue.add(stringReq)
    }
}