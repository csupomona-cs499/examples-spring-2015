package edu.cpp.cs499.l07_http_network.data;

/**
 * Created by yusun on 4/11/15.
 */
public class WeatherData {

    private long id;
    private String name;

    private WeatherDataMain main;
    private WeatherDataSys sys;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherDataMain getMain() {
        return main;
    }

    public void setMain(WeatherDataMain main) {
        this.main = main;
    }

    public WeatherDataSys getSys() {
        return sys;
    }

    public void setSys(WeatherDataSys sys) {
        this.sys = sys;
    }
}
