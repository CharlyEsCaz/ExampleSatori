package mx.com.satoritech.web

import android.util.Log
import com.google.gson.GsonBuilder
import mx.com.satoritech.web.api.serializers.BooleanDeserializer
import mx.com.satoritech.web.api.serializers.BooleanSerializer
import mx.com.satoritech.web.api.serializers.DateDeserializer
import mx.com.satoritech.web.api.serializers.DateSerializer
import mx.com.satoritech.web.models.CharacterWS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object APIExample {

    private const val TAG = "API_DATA_EXAMPLE"
    private val apiService: ExampleService

    init{

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        //Prepare http client
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        // Prepare Gson instance
        val gson = GsonBuilder()
            .registerTypeAdapter(Boolean::class.java, BooleanSerializer())
            .registerTypeAdapter(Boolean::class.java, BooleanDeserializer())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanSerializer())
            .registerTypeAdapter(Boolean::class.javaPrimitiveType, BooleanDeserializer())
            .registerTypeAdapter(Date::class.java, DateSerializer())
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()

        //Prepare retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(APIConstants.base_path)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClientBuilder)
            .build()

        apiService = retrofit.create(ExampleService::class.java)

    }

    private fun <T>doRequest(operation: String, call: Call<T>, cb: (success: Boolean, data: T?) -> Unit) {
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                handleFailure(operation,t,cb)
            }
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    cb(true, response.body())
                } else {
                    handleUnsuccessful(operation, cb)
                }
            }

        })
    }


    private fun <T>handleUnsuccessful(operation: String,callback: (success: Boolean,data: T?) -> Unit) {
        Log.w(TAG,"$operation was unsuccessful")
        callback(false, null)
    }

    private fun <T>handleFailure(operation: String, t: Throwable,callback: (success: Boolean,data: T?) -> Unit) {
        Log.e(TAG, "$operation has failed")
        Log.e(TAG, "Message is: " + t.message)
        callback(false, null)
    }

    fun getCharacters(cb: (success: Boolean, data: List<CharacterWS>?) -> Unit){
        doRequest(
            "Get All Battles",
            apiService.getCharacters(),
            cb
        )
    }

}