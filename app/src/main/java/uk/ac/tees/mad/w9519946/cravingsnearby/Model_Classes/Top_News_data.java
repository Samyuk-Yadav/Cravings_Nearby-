package uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes;

import java.util.ArrayList;

public class Top_News_data {

    //Variables
    private ArrayList<News_data> articles;
    private String status;
    private String totalResults;

    //Constructors
    public Top_News_data(String status, String totalResults, ArrayList<News_data> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    //Getter and Setter
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<News_data> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News_data> articles) {
        this.articles = articles;
    }
}
