package com.extremesolution.marvelapp.data.network

import com.extremesolution.marvelapp.data.models.StoriesModels.CharacterStoriesResponse
import com.extremesolution.marvelapp.data.responses.CharacterResponse
import com.extremesolution.marvelapp.data.responses.ComicsResponse
import com.extremesolution.marvelapp.data.responses.EventsResponse
import com.extremesolution.marvelapp.data.responses.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("characters")
    suspend fun characterList(
        @Query("offset") page: String, @Query("limit") limit: String
    ): CharacterResponse

    @GET("characters/{id}/comics")
    suspend fun characterComics(
        @Path("id") characterId: String
    ): ComicsResponse

    @GET("characters/{id}/events")
    suspend fun characterEvents(
        @Path("id") characterId: String
    ): EventsResponse

    @GET("characters/{id}/series")
    suspend fun characterSeries(
        @Path("id") characterId: String
    ): SeriesResponse

    @GET("characters/{id}/stories")
    suspend fun characterStories(
        @Path("id") characterId: String
    ): CharacterStoriesResponse
}
