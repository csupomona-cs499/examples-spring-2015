package edu.cpp.cs499.parsedemoclass;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by yusun on 4/22/15.
 */
@ParseClassName("TeamProject")
public class TeamProject extends ParseObject {

    private String name;
    private String member;
    private String description;
    private ParseFile teamImage;

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public String getMember() {
        return getString("member");
    }

    public void setMember(String member) {
        put("member", member);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public ParseFile getTeamImage() {
        return getParseFile("teamImage");
    }

    public void setTeamImage(ParseFile teamImage) {
        put("teamImage", teamImage);
    }
}
