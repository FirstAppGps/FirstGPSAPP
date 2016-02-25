package com.example.rucha.myapplication;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Rucha on 2/12/2016.
 */
public class NetAsync extends AsyncTask {

    public NetAsync(String email, String pwd) {

        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("email", email);
        hmap.put("password", pwd);
        JSONObject js = new JSONObject(hmap);


    }


    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }
}