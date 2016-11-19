package ru.javastudy.ws.main;

import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.*;

public class TestRunner {
    //public StringBuilder allResults;
    private static String openSessionByURL(String url) throws MalformedURLException {
        URL urlName = new URL(url);
        BufferedReader rd;
        String line;
        String result = "";
        try{
            HttpURLConnection urlConnection = (HttpURLConnection) urlName.openConnection();
            urlConnection.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line;
            }
            rd.close();
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String start() throws MalformedURLException {
        StringBuilder allResults = new StringBuilder();
        int k = 5;
        allResults.append("СНИЛС: ").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SNILS)).append("\n");
        }
        allResults.append("ИНН для ИП: ").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.INN + DataHelper.INN_ip)).append("\n");
        }
        allResults.append("ИНН для ФЗ: ").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.INN + DataHelper.INN_fl)).append("\n");
        }
        allResults.append("Номер карты MasterCart: ").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDNUMBER_MASTERCARD));
        }
        allResults.append("Номер карты Visa: ").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDNUMBER_VISA));
        }

        return  allResults.toString();
    }

}
