package edu.cpp.cs499.l07_http_network;

import edu.cpp.cs499.l07_http_network.data.WeatherData;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by yusun on 4/11/15.
 */
public interface WeatherService {

    @GET("/weather")
    WeatherData getWeatherByCityName(@Query("q")String cityName);

    @GET("/weather")
    void getWeatherByCityName(@Query("q")String cityName, Callback<WeatherData> callback);
}
