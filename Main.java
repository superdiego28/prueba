package com.company;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author Claudia Barrios
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String salida ="";
        try {

            URL url = new URL("https://simplifiedcoding.net/demos/marvel/");//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                salida+=output;
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        JsonParser jsonParser = new JsonParser();
        JsonArray array = jsonParser.parse(salida).getAsJsonArray();
        //System.out.println(array.toString());
        for(int i=0;i<array.size();i++){
            JsonObject item = array.get(i).getAsJsonObject();
            System.out.println("Alias: "+item.get("name")+", Nombre: "+item.get("realname")+", Equipo: "+item.get("team")+", Año: "+item.get("firstappearance")+", Creada Por: "+item.get("createdby")+", Publicada Por: "+item.get("publisher")+", Url Imagen: "+item.get("imageurl")+", Biografia: "+item.get("bio"));
        }
    }

}