package com.ningwenqiang.glory.mygitdemoapp.data_processing.gson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ningwenqiang.glory.mygitdemoapp.R
import com.ningwenqiang.glory.mygitdemoapp.data_processing.gson.bean.BaseGenericParadigm
import com.ningwenqiang.glory.mygitdemoapp.data_processing.gson.bean.BaseGenericParadigm1
import com.ningwenqiang.glory.mygitdemoapp.data_processing.gson.bean.GenData3
import com.ningwenqiang.glory.mygitdemoapp.data_processing.gson.bean.GenericParadigmData1
import java.lang.reflect.Type


class GsonTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson_test)

    }

    override fun onResume() {
        super.onResume()
        fanXing()
    }

    //亲测泛型可以用，成功
    fun  fanXing(){
        val gson = Gson()
        val bas = BaseGenericParadigm<GenericParadigmData1>();
        bas.code=200
        bas.text="success"
        val genericParadigmData1=GenericParadigmData1()

        genericParadigmData1.value1=100
        genericParadigmData1.value2="GenericParadigmData1"
        genericParadigmData1.value3=123456789L

        bas.data=genericParadigmData1

        val json= gson.toJson(bas)
        System.out.println(json)
        val objectType: Type? = object : TypeToken<BaseGenericParadigm<GenericParadigmData1?>?>() {}.type
       //    val bas2:BaseGenericParadigm<GenericParadigmData1> =  gson.fromJson(json, objectType)
        val data : BaseGenericParadigm1<GenData3> = json.toJsonObject()
        System.out.println(" ::;   "+data.data.value2)


        val list = listOf("123","456")
        list.forEach {
            println(it)
        }

        list.forEachIndexed{
            i,v ->
        }


     }


    inline fun <reified T > String.toJsonObject(): T{
        val token = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(this,token)
    }

}