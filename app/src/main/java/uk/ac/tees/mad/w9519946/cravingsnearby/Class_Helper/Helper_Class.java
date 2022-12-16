package uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class Helper_Class {

    private SharedPreferences sharedPreferences12;

    public Helper_Class(Context bfjlkszappContext) {
        sharedPreferences12 = PreferenceManager.getDefaultSharedPreferences(bfjlkszappContext);
    }


    public ArrayList<String> getstring_List(String rockry) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(sharedPreferences12.getString(rockry, ""), "‚‗‚")));
    }


    public ArrayList<Restaurant_Food_data> getListObject12(String rockry) {
        Gson son_g12 = new Gson();

        ArrayList<String> strings_objs = getstring_List(rockry);
        ArrayList<Restaurant_Food_data> list_of_players = new ArrayList<Restaurant_Food_data>();

        for (String string_objes : strings_objs) {
            Restaurant_Food_data data_food = son_g12.fromJson(string_objes, Restaurant_Food_data.class);
            list_of_players.add(data_food);
        }
        return list_of_players;
    }


    public void putLtring_Strin(String sam_keuy, ArrayList<String> list_of_strings) {
        checkForKeyZero(sam_keuy);
        String[] mySist_String = list_of_strings.toArray(new String[list_of_strings.size()]);
        sharedPreferences12.edit().putString(sam_keuy, TextUtils.join("‚‗‚", mySist_String)).apply();
    }

    public void putLbject_listing(String sam_keuy, ArrayList<Restaurant_Food_data> list_of_players) {
        checkForKeyZero(sam_keuy);
        Gson son_gon = new Gson();
        ArrayList<String> strings_obj = new ArrayList<String>();
        for (Restaurant_Food_data name_play : list_of_players) {
            strings_obj.add(son_gon.toJson(name_play));
        }
        putLtring_Strin(sam_keuy, strings_obj);
    }

    private void checkForKeyZero(String strings_obj12) {
        if (strings_obj12 == null) {
            throw new NullPointerException();
        }
    }
}