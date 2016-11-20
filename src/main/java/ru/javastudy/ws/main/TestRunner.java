package ru.javastudy.ws.main;

import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.*;
import java.util.Random;

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
        int counter = 1;
        int k = (iter==0?5:iter);
        allResults.append("\n").append(counter++).append(" СНИЛС: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SNILS)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" ИНН для ИП: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.INN + DataHelper.INN_ip)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" ИНН для ФЗ: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.INN + DataHelper.INN_fl)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Номер карты MasterCard: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDNUMBER_MASTERCARD)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Номер карты Visa: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDNUMBER_VISA)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" КПП: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.KPP)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Кем выдан паспорт: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.ISSUER)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Серия-номер свидетельства о рождении: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.BIRTHSERIALNUMBER)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Случайное число: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RUNDOM_NUMBER)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Генерация времени: ").append(openSessionByURL(DataHelper.TIME)).append("\n").append("---------------").append("\n");
        allResults.append("\n").append(counter++).append(" Типы документов: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.DOCUMENTTYPE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Тим материла стен: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.WALLMATERIAL)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Состояние квартиры: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.APARTMENTSTATE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Религии: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RELIGION)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Металлы: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.METAL)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Тип банковской карты: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CARDTYPE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Цвета: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.COLOR)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Должности: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.POSITION)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Звания: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RANK)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" CVV: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CVV)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" PIN: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.PIN)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" ОКПО ИП: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OKPO_IP)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" ОКПО ЮР: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OKPO_UR)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Языки: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.LANGUAGE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Национальности: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.NATIONALITY)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Гражданство: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CITIZENSHIP)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Образование: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.EDUCATION)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Квалификация по образованию: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.EDUCATIONQUALIFICATION)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Регионы: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.REGION)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Города: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CITY)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Тип населённого пункта: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CITYTYPE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Тип улиц: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.STREETTYPE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Право на проживание: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RIGHTTOLIVE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Семейное положение: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.MARTIALSTATUS)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Степень родства: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.RELATIONDEGREE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" ОГРН: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OGRN)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Комментарий: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.COMMENT)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Вид недвижимости: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.REALESTATETYPE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Единицы измерения: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.UNIT)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Вид ТС: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.TSKIND)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Марка ТС: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SMARK)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Право собственности: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.OWNERSHIPRIGHT)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Организационно правовая форма: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.ORGANIZATIONFORM)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Тип контрагента: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.CONTRAGENTTYPE)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Название банка: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.BANKNAME)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Аббревиатура страны: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.COUNTRYABBR)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" IP: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.IP)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Валюта (ru): ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.VALUTA_RU)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Валюта (en): ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.VALUTA_EN)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Регистрационный номер транспортного средства РФ ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.TSREGNUMBER)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Серия и номер паспорта РФ ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.PASSPORT)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Настраиваемая строка ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            Random randomGenerator = new Random();
            Integer number = randomGenerator.nextInt(50) + 2;
            String s = new String(DataHelper.CUSTOMSTRING + number.toString());
            allResults.append(openSessionByURL(s)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Социальные категории: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SOCCAT)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Документы, подтверждающие соц.категорию: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.SUPDOC)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Группы льгот: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.LGOTGROUP)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Список льгот: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.LGOT)).append("\n");
        }
        allResults.append("\n").append(counter++).append(" Загран паспорта: ").append("\n").append("---------------").append("\n");
        for (int i = 0; i<k; i++){
            allResults.append(openSessionByURL(DataHelper.ZAGRPASSPORT)).append("\n");
        }
        return  allResults.toString();

    }

}
