package com.company;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String salida="";
        for(int i=0;i<16;i++){
            try {
                URL url = new URL("https://swapi.co/api/people/"+(i+1));//your url i.e fetch data from .
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : "
                            + conn.getResponseCode());
                }
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                while ((output = br.readLine()) != null) {
                    //System.out.println(output);
                    salida+=output;
                }
                conn.disconnect();
            } catch (Exception e) {
                System.out.println("Exception in NetClientGet:- " + e);
            }
            JsonParser jsonParser = new JsonParser();
            JsonObject obj = jsonParser.parse(salida).getAsJsonObject();
            //System.out.println(obj);
            System.out.println("Nombre: "+obj.get("name")+", Altura: "+obj.get("height")+", Peso: "+obj.get("mass")+", Color Cabello: "+obj.get("hair_color")+", Color Piel: "+obj.get("skin_color"));
            System.out.println("Color de Ojos: "+obj.get("eye_color")+", Cumpleaños: "+obj.get("birth_year")+", Genero: "+obj.get("gender")+", Tarea: "+obj.get("homeworld"));
            System.out.println();
            salida="";
        }
    }

}

