import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        //Skapa url
        URL url = new URL("https://api.wheretheiss.at/v1/satellites/25544") ;

        //Skapa connection til API
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //Set get request
        conn.setRequestMethod("GET");
        //Utföra kopplingen till APIn
        conn.connect();
        //Om statuskoden är 200, testa kopplingen
        if (conn.getResponseCode() == 200) {
            System.out.println("Koppling lyckades");
            System.out.println();
        } else {
            System.out.println("Koppling misslyckades");
        }
        System.out.println("-------");
        System.out.println();


        StringBuilder strData = new StringBuilder();   //Hämtar data från api som ett objekt, spara som en string
        Scanner scanner = new Scanner(url.openStream());   //Lyssna på data från url
        while (scanner.hasNext()) {
            strData.append(scanner.nextLine()); //Bygger upp data med ISS data

        }
            scanner.close();   //Stänger kopplingen
        JSONObject issData = (JSONObject) new JSONParser().parse(String.valueOf(strData));

        double longitude = (double) issData.get("longitude");
        double latitude = (double) issData.get("latitude");
        double velocity =  (double) issData.get("velocity");
        //System.out.println(issData);

        System.out.println("Info:");
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Velocity: " + velocity + " km/h");




    }
}