package com.yelp.yelpapp.api

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.yelp.yelpapp.model.response.ErrorResponse
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    suspend fun<T : Any> apiRequest(call : suspend () -> Response<T>) :T?{
        val response = call.invoke()
        if(response.isSuccessful) {
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                if(response.code() != 200){

                    try {
                        message.append(JSONObject(error).getString("message"))
                        val responseerror : ErrorResponse? = Gson().fromJson(error, ErrorResponse::class.java)
                        if(response.code() == 401)
                            responseerror?.message?.let { exception(responseerror.message, true) }
                        else
                            responseerror?.message?.let { exception(responseerror.message, false) }
                    }catch (e : JSONException){
                        exception("We are sorry something went wrong, please try later.", false)
                    }catch (e : JsonParseException){
                        exception("We are sorry something went wrong, please try later.", false)
                    }
                }
            }

            if(message.isEmpty())
                message.append("We are sorry something went wrong, please try later.")
        }
        return null
    }
    abstract fun exception(errorMsg: String, isUnauthorized : Boolean)
}