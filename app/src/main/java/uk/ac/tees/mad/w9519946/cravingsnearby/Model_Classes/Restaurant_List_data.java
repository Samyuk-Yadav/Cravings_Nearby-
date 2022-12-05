package uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes;

public class Restaurant_List_data {

    private String titles;
    private String pics;

    public Restaurant_List_data(String titles, String pics) {
        this.titles = titles;
        this.pics = pics;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }
}
