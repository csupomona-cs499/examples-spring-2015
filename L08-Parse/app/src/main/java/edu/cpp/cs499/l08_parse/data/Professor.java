package edu.cpp.cs499.l08_parse.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by yusun on 3/31/15.
 */
@ParseClassName("Professor")
public class Professor extends ParseObject {

    public Professor() {

    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getOffice() {
        return getString("office");
    }

    public void setOffice(String office) {
        put("office", office);
    }

    public String getFromTime() {
        return getString("fromTime");
    }

    public void setFromTime(String fromTime) {
        put("fromTime", fromTime);
    }

    public String getToTime() {
        return getString("toTime");
    }

    public void setToTime(String toTime) {
        put("toTime", toTime);
    }
}
