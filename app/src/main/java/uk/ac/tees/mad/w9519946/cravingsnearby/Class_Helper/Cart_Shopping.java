package uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;
import uk.ac.tees.mad.w9519946.cravingsnearby.News_Utilities.Cart_Number_Of_Items;

public class Cart_Shopping {
    private Context content;
    private Helper_Class helper_class;

    public Cart_Shopping(Context content) {
        this.content = content;
        this.helper_class = new Helper_Class(content);
    }

    public void food_applying(Restaurant_Food_data food_data){
        ArrayList<Restaurant_Food_data> foodData_List = cart_Shopping_List();
        boolean there_already_Food = false;
        int m = 0;
        for (int x=0; x<foodData_List.size(); x++){
            if (foodData_List.get(x).getFood_title().equals(food_data.getFood_title())){
                there_already_Food = true;
                m = x;
                break;
            }
        }

        if (there_already_Food){
            foodData_List.get(m).setCurrency_in_Cart(food_data.getCurrency_in_Cart());
        }else {
            foodData_List.add(food_data);
        }helper_class.putListObject("Shopping_List_Cart", foodData_List);
        Toast.makeText(content, "Food Added to Cart", Toast.LENGTH_SHORT).show();
    }

    public Double getcompleteCost(){
        ArrayList<Restaurant_Food_data> restaurantFoodData55 = cart_Shopping_List();
        double cost = 0;
        for (int g = 0; g< restaurantFoodData55.size(); g++){
            cost = cost + (restaurantFoodData55.get(g).getFood_fees()*restaurantFoodData55.get(g).getCurrency_in_Cart());
        }
        return cost;
    }

    public void food_minus_btn(ArrayList<Restaurant_Food_data> restaurantFoodData696, int position696, Cart_Number_Of_Items cart_number_of_items69){
        if (restaurantFoodData696.get(position696).getCurrency_in_Cart()==1){
            restaurantFoodData696.remove(position696);
        }
        else{
            restaurantFoodData696.get(position696).setCurrency_in_Cart(restaurantFoodData696.get(position696).getCurrency_in_Cart()-1);
        }
        helper_class.putListObject("Shopping_List_Cart", restaurantFoodData696);
        cart_number_of_items69.swaping();
    }

    public void food_number_plus(ArrayList<Restaurant_Food_data> restaurantFoodData69, int position69, Cart_Number_Of_Items cart_number_of_items){
        restaurantFoodData69.get(position69).setCurrency_in_Cart(restaurantFoodData69.get(position69).getCurrency_in_Cart()+1);
        helper_class.putListObject("Shopping_List_Cart", restaurantFoodData69);
        cart_number_of_items.swaping();
    }

    public ArrayList<Restaurant_Food_data> cart_Shopping_List(){
        return helper_class.getListObject("Shopping_List_Cart");
    }


}
