

package uk.ac.tees.mad.w9519946.cravingsnearby.Class_Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;


import uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes.Restaurant_Food_data;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class Helper_Class {

    private SharedPreferences preferences;
    private String DEFAULT_APP_IMAGEDATA_DIRECTORY;
    private String lastImagePath = "";

    public Helper_Class(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }


    public Bitmap getImage(String path) {
        Bitmap bitmapFromPath = null;
        try {
            bitmapFromPath = BitmapFactory.decodeFile(path);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmapFromPath;
    }

    public String getSavedImagePath() {
        return lastImagePath;
    }


    public String putImage(String theFolder, String theImageName, Bitmap theBitmap) {
        if (theFolder == null || theImageName == null || theBitmap == null)
            return null;

        this.DEFAULT_APP_IMAGEDATA_DIRECTORY = theFolder;
        String mFullPath = setupFullPath(theImageName);

        if (!mFullPath.equals("")) {
            lastImagePath = mFullPath;
            saveBitmap(mFullPath, theBitmap);
        }

        return mFullPath;
    }

    public boolean putImageWithFullPath(String fullPath, Bitmap theBitmap) {
        return !(fullPath == null || theBitmap == null) && saveBitmap(fullPath, theBitmap);
    }

    private String setupFullPath(String imageName) {
        File mFolder = new File(Environment.getExternalStorageDirectory(), DEFAULT_APP_IMAGEDATA_DIRECTORY);

        if (isExternalStorageReadable() && isExternalStorageWritable() && !mFolder.exists()) {
            if (!mFolder.mkdirs()) {
                Log.e("ERROR", "Failed to setup folder");
                return "";
            }
        }
        return mFolder.getPath() + '/' + imageName;
    }


    private boolean saveBitmap(String fullPath, Bitmap bitmap) {
        if (fullPath == null || bitmap == null)
            return false;

        boolean fileCreated = false;
        boolean bitmapCompressed = false;
        boolean streamClosed = false;

        File imageFile = new File(fullPath);

        if (imageFile.exists())
            if (!imageFile.delete())
                return false;

        try {
            fileCreated = imageFile.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageFile);
            bitmapCompressed = bitmap.compress(CompressFormat.PNG, 100, out);

        } catch (Exception e) {
            e.printStackTrace();
            bitmapCompressed = false;

        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    streamClosed = true;

                } catch (IOException e) {
                    e.printStackTrace();
                    streamClosed = false;
                }
            }
        }

        return (fileCreated && bitmapCompressed && streamClosed);
    }












    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }





    public ArrayList<Restaurant_Food_data> getListObject(String key){
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<Restaurant_Food_data> playerList =  new ArrayList<Restaurant_Food_data>();

        for(String jObjString : objStrings){
            Restaurant_Food_data player  = gson.fromJson(jObjString,  Restaurant_Food_data.class);
            playerList.add(player);
        }
        return playerList;
    }















    public void putString(String key, String value) {
        checkForNullKey(key); checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }



    public void putListObject(String key, ArrayList<Restaurant_Food_data> playerList){
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(Restaurant_Food_data player: playerList){
            objStrings.add(gson.toJson(player));
        }
        putListString(key, objStrings);
    }






    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }

    private void checkForNullValue(String value){
        if (value == null){
            throw new NullPointerException();
        }
    }
}