package com.example.bikeproject.modele.webServices;

import com.example.bikeproject.Station;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Response;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

class WSUtils {
    private static final String KEY = "2f70b355156ec1c0550352c1f44b8ac6ce78393e";
    private static final String CONTRAT = "Lyon";
    private static final String URL = "https://api.jcdecaux.com/vls/v1/stations?contract=" + CONTRAT + "&apiKey=" + KEY;
    private static final Gson gson = new Gson();

    public static ArrayList<Station> getStation() throws Exception{

        Response response = okHttpUtils.sendGetOkHttpRequest(URL);

        if(response.code() != HttpURLConnection.HTTP_OK){
            throw new Exception("Réponse du serveur incorrecte " + response.code());
        } else {
            // On lit le flux avec InputStreamReader
            InputStreamReader isr = new InputStreamReader(response.body().byteStream());
            // On converti les fichiers gson du serveur en ArrayList java
            ArrayList<Station> station = gson.fromJson(isr, new TypeToken<ArrayList<com.example.bikeproject.Station>>(){}.getType());
            // On ferme le flux
            isr.close();
            return station;
        }

    }

   // private final static String URL_WS_GOOGLE_JSON = "https://maps.googleapis.com/maps/api/directions/json?mode=walking";

    /*public static DirectionResult getDirection(LatLng depart, LatLng arrivee) throws Exception {
        String url = URL_WS_GOOGLE_JSON + "&origin=" + depart.latitude + "," + depart.longitude + "&destination=" + arrivee.latitude + "," + arrivee.longitude;
        Response response = OkHttpUtils.sendGetOkHttpRequest(url);

        //Analyse du code retour
        if (response.code() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Réponse du serveur incorrecte : " + response.code());
        }
        else {
            InputStreamReader inputStreamReader = new InputStreamReader(response.body().byteStream());
            DirectionResult directionResult = gson.fromJson(inputStreamReader, DirectionResult.class);

            inputStreamReader.close();

            return directionResult;
        }
    }*/
}