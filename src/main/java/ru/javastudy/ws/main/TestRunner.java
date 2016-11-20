package ru.javastudy.ws.main;

import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.*;

public class TestRunner {
    //public StringBuilder allResults;
    private static String openSessionByURL(String url) throws MalformedURLException, IOException {
        URL urlName = new URL(new String(url.getBytes(), "UTF-8"));
        BufferedReader rd;
        String line;
        String result = "";
        try{
            HttpURLConnection urlConnection = (HttpURLConnection) urlName.openConnection();
            urlConnection.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
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

    public static String start(int iter) throws MalformedURLException, IOException {
        StringBuilder allResults = new StringBuilder();
        int k = (iter==0?5:iter);
        allResults.append("\n").append("СНИЛС: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SNILS)).append("\n");
        }
        allResults.append("\n").append("ИНН для ИП: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.INN + DataHelper.INN_ip)).append("\n");
        }
        allResults.append("\n").append("ИНН для ФЗ: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.INN + DataHelper.INN_fl)).append("\n");
        }
        allResults.append("\n").append("Номер карты MasterCard: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDNUMBER_MASTERCARD)).append("\n");
        }
        allResults.append("\n").append("Номер карты Visa: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDNUMBER_VISA)).append("\n");
        }
        allResults.append("\n").append("КПП: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.KPP)).append("\n");
        }
        allResults.append("\n").append("Кем выдан паспорт: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.ISSUER)).append("\n");
        }
        allResults.append("\n").append("Серия-номер свидетельства о рождении: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.BIRTHSERIALNUMBER)).append("\n");
        }
        allResults.append("\n").append("Случайное число: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RUNDOM_NUMBER)).append("\n");
        }
        allResults.append("\n").append("Генерация времени: ").append(openSessionByURL(DataHelper.TIME)).append("\n").append("---------------").append("\n");
        allResults.append("\n").append("Типы документов: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.DOCUMENTTYPE)).append("\n");
        }
        allResults.append("\n").append("Тим материла стен: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.WALLMATERIAL)).append("\n");
        }
        allResults.append("\n").append("Состояние квартиры: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.APARTMENTSTATE)).append("\n");
        }
        allResults.append("\n").append("Религии: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RELIGION)).append("\n");
        }
        allResults.append("\n").append("Металлы: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.METAL)).append("\n");
        }
        allResults.append("\n").append("Тип банковской карты: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDTYPE)).append("\n");
        }
        allResults.append("\n").append("Цвета: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.COLOR)).append("\n");
        }
        allResults.append("\n").append("Должности: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.POSITION)).append("\n");
        }
        allResults.append("\n").append("Звания: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RANK)).append("\n");
        }
        allResults.append("\n").append("CVV: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CVV)).append("\n");
        }
        allResults.append("\n").append("PIN: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.PIN)).append("\n");
        }
        allResults.append("\n").append("ОКПО ИП: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OKPO_IP)).append("\n");
        }
        allResults.append("\n").append("ОКПО ЮР: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OKPO_UR)).append("\n");
        }
        allResults.append("\n").append("Языки: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.LANGUAGE)).append("\n");
        }
        allResults.append("\n").append("Национальности: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.NATIONALITY)).append("\n");
        }
        allResults.append("\n").append("Гражданство: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CITIZENSHIP)).append("\n");
        }
        allResults.append("\n").append("Образование: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.EDUCATION)).append("\n");
        }
        allResults.append("\n").append("Квалификация по образованию: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.EDUCATIONQUALIFICATION)).append("\n");
        }
        allResults.append("\n").append("Регионы: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.REGION)).append("\n");
        }
        allResults.append("\n").append("Города: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CITY)).append("\n");
        }
        allResults.append("\n").append("Тип населённого пункта: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CITYTYPE)).append("\n");
        }
        allResults.append("\n").append("Тип улиц: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.STREETTYPE)).append("\n");
        }
        allResults.append("\n").append("Право на проживание: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RIGHTTOLIVE)).append("\n");
        }
        allResults.append("\n").append("Семейное положение: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.MARTIALSTATUS)).append("\n");
        }
        allResults.append("\n").append("Степень родства: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RELATIONDEGREE)).append("\n");
        }
        allResults.append("\n").append("ОГРН: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OGRN)).append("\n");
        }
        allResults.append("\n").append("Комментарий: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.COMMENT)).append("\n");
        }
        allResults.append("\n").append("Вид недвижимости: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.REALESTATETYPE)).append("\n");
        }
        allResults.append("\n").append("Единицы измерения: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.UNIT)).append("\n");
        }
        allResults.append("\n").append("Вид ТС: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.TSKIND)).append("\n");
        }
        allResults.append("\n").append("Марка ТС: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SMARK)).append("\n");
        }
        allResults.append("\n").append("Право собственности: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OWNERSHIPRIGHT)).append("\n");
        }
        allResults.append("\n").append("Организационно правовая форма: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.ORGANIZATIONFORM)).append("\n");
        }
        allResults.append("\n").append("Тип контрагента: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CONTRAGENTTYPE)).append("\n");
        }
        allResults.append("\n").append("Название банка: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.BANKNAME)).append("\n");
        }
        allResults.append("\n").append("Аббревиатура страны: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.COUNTRYABBR)).append("\n");
        }
        allResults.append("\n").append("IP: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.IP)).append("\n");
        }
        allResults.append("\n").append("Валюта (ru): ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.VALUTA_RU)).append("\n");
        }
        allResults.append("\n").append("Валюта (en): ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.VALUTA_EN)).append("\n");
        }
        allResults.append("\n").append("Регистрационный номер транспортного средства РФ ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.TSREGNUMBER)).append("\n");
        }
        allResults.append("\n").append("Серия и номер паспорта РФ ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.PASSPORT)).append("\n");
        }
        allResults.append("\n").append("Настраиваемая строка ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CUSTOMSTRING)).append("\n");
        }
        return  allResults.toString();

    }

}
