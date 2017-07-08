package model;

import java.util.List;

/**
 * Created by lenovo on 2016/10/9.
 */

public class Before {

    private String date;
    private List<Story> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
