package edwylugo.woopsicredi.core

import edwylugo.woopsicredi.model.request.CheckIn
import edwylugo.woopsicredi.model.response.Event
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WoopSicrediService {
    @GET("events")
    fun getEvents(): Single<List<Event>>

    @GET("events/{id}")
    fun getEventById(@Path("id") id: Int): Single<Event>

    @POST("checkin")
    fun checkIn(@Body form: CheckIn): Completable
}