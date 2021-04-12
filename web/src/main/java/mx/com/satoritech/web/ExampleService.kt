package mx.com.satoritech.web

import mx.com.satoritech.web.models.CharacterWS
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleService {

    //Get Character
    @GET(APIConstants.api_base + "show/characters")
    fun getCharacters(): Call<List<CharacterWS>>

    // Get Character by name
    @GET(APIConstants.api_base + "show/characters/{name}")
    fun getCharacter(@Path("name") name: String): Call<CharacterWS>


}