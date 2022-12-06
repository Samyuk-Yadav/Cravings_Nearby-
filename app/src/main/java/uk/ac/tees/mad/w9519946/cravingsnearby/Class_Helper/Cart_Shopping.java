package uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;

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

    public ArrayList<Restaurant_Food_data> cart_Shopping_List(){
        return helper_class.getListObject("Shopping_List_Cart");
    }


}
