package model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */

public class Story {

    private String title;
    private List<String> images;
    private int id;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }
}
