package uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes;

public class Restaurant_Food_data {

    private String food_description;
    private String food_pic;
    private Double food_fees;
    private int currency_in_Cart;
    private String food_title;

    public Restaurant_Food_data(String food_description, String food_pic, Double food_fees, int currency_in_Cart, String food_title) {
        this.food_description = food_description;
        this.food_pic = food_pic;
        this.food_fees = food_fees;
        this.currency_in_Cart = currency_in_Cart;
        this.food_title = food_title;
    }

    public Restaurant_Food_data(String food_description, String food_pic, Double food_fees, String food_title) {
        this.food_description = food_description;
        this.food_pic = food_pic;
        this.food_fees = food_fees;
        this.food_title = food_title;
    }

    public String getFood_description() {
        return food_description;
    }

    public void setFood_description(String food_description) {
        this.food_description = food_description;
    }

    public String getFood_pic() {
        return food_pic;
    }

    public void setFood_pic(String food_pic) {
        this.food_pic = food_pic;
    }

    public Double getFood_fees() {
        return food_fees;
    }

    public void setFood_fees(Double food_fees) {
        this.food_fees = food_fees;
    }

    public int getCurrency_in_Cart() {
        return currency_in_Cart;
    }

    public void setCurrency_in_Cart(int currency_in_Cart) {
        this.currency_in_Cart = currency_in_Cart;
    }

    public String getFood_title() {
        return food_title;
    }

    public void setFood_title(String food_title) {
        this.food_title = food_title;
    }
}
