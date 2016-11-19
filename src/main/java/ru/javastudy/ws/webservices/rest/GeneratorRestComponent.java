package ru.javastudy.ws.webservices.rest;

import org.springframework.web.bind.annotation.*;
import ru.javastudy.ws.main.GetRandomLine;


import javax.ws.rs.*;
import java.io.File;
import java.util.*;


import java.text.DateFormat;

import java.text.SimpleDateFormat;


public class GeneratorRestComponent {

    public GeneratorRestComponent() {}

//    @GET
//    @Path("/getgoods")
//    @Produces("application/xml")
//    public Goods getGoods() {
//        Goods goods = new Goods();
//        goods.setName("Some Goods name");
//        goods.setId(1);
//        return goods;
//    }

//    @GET
//    @Path("/getdoc")
//    @Produces("application/xml")
//    public Document getDocument() {
//        List<Goods> goodsList = new ArrayList<Goods>();
//        goodsList.add(new Goods(1, "goods1"));
//        goodsList.add(new Goods(2, "goods2"));
//        goodsList.add(new Goods(3, "goods3"));
//        return new Document(777, "firstDocument", goodsList);
//    }

    @GET
    @Path("/getcomments")
   // @Produces("application/xml")
    @Produces("application/JSON")
    public String getComments() {
//        Goods goods = new Goods();
//        goods.setName("Some Goods name");
//        goods.setId(1);
        return "blah-blah-blah";
    }

//    @GET
//    @Path("/getnumber")
//    @Produces("application/JSON")
//    public Integer getNumber(int bound) {
//        Random randomGenerator = new Random();
//        Integer number = randomGenerator.nextInt(bound);
//        return number;
//    }

    //Число
    @GET
    @Path("/getnumber")
    @Produces("application/JSON")
    public String getNumber() {
        Random randomGenerator = new Random();
        Integer number = randomGenerator.nextInt(100);

        return number.toString();
    }

    //Время (текущее)
    @GET
    @Path("/gettime")
    @Produces("application/JSON")
    public String getTime() {
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //Тип документа
    @GET
    @Path("/getdocumenttype")
    @Produces("text/plain")
    public String getDocumentType() {
        File f = new File("D:\\data\\documenttype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }
//
    //Металл
    @GET
    @Path("/getmetal")
    @Produces("text/plain")
    public String getMetal() {
        File f = new File("D:\\data\\metal.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Тип банковской карты
    @GET
    @Path("/getcardtype")
    @Produces("text/plain")
    public String getCardType() {
        Random randomGenerator = new Random();
        String dict[] = {"Cirrus","Maestro","MasterCard Electronic","MasterCard Unembossed",
                "MasterСard Standard","MasterСard Gold","MasterCard World","MasterСard Platinum",
                "MasterCard World Signia","MasterCard Virtual","MasterCard Workplace Solutions",
                "Visa Electron","Visa Virtual","Visa Classic","Visa Gold","Visa Platinum",
                "Visa Signature","Visa Infinite","Visa Black Card"};
//        List<String> documentsList= new ArrayList<String>();
//        documentsList.addAll(new Collection<String>(){"паспорт", "загранпаспорт", "пенсионное удостоверение"});
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dict[randomGenerator.nextInt(dict.length)];
    }

    //Цвет
    @GET
    @Path("/getcolor")
    //@Produces("application/JSON")
    @Produces("text/plain")
    public String getColor() {
        Random randomGenerator = new Random();
        String hexChars = "0123456789ABCDEF";
        StringBuffer sbColor = new StringBuffer();
        sbColor.append("#");
        for (int i = 0; i < 6; i++)
        {
            sbColor.append(hexChars.charAt(new Random().nextInt(hexChars.length())));
        }
        return sbColor.toString();
    }

    //Должность
    @GET
    @Path("/getposition")
    @Produces("text/plain")
    public String getPosition() {
        //TODO Positions.csv
        Random randomGenerator = new Random();
        String stringArray[] = {"Атташе","Аудитор","Аэрохимик","Букмекер",
                "Ведущий дискотеки","Генеральный директор","Дежурный оперативны","Доцент",
                "Заведующий архивом","Инженер","Кассир",
                "Лаборант","Массажист","Мастер","Научный сотрудник","Оператор диспетчерской",
                "Первый помощник Президента Российской Федерации","Ревизор","Секретарь комитета",
        "Техник","Уполномоченный фонда","Фрахтовщик","Хранитель фондов","Частный детектив",
        "Шапитмейстер","Эксперт","Юрисконсульт"};
        return stringArray[randomGenerator.nextInt(stringArray.length)];
    }

    //Звание
    @GET
    @Path("/getrank")
    @Produces("text/plain")
    public String getRank() {
        Random randomGenerator = new Random();
        String stringArray[] = {"Курсант","Рядовой","Ефрейтор","Младший сержант",
                "Сержант","Старший сержант","Старшина","Прапорщик",
                "Старший прапорщик","Младший лейтенант","Лейтенант",
                "Старший лейтенант","Капитан","Майор","Подполковник","Полковник",
                "Генерал-майор","Генерал-лейтенант","Генерал-полковник",
                "Генерал армии","Маршал Российской Федерации"};
        return stringArray[randomGenerator.nextInt(stringArray.length)];
    }

    //CVV
    @GET
    @Path("/getcvv")
    @Produces("text/plain")
    public String getCVV() {
        String s = "1234567890";
        StringBuffer sbCVV = new StringBuffer();

        for (int i = 0; i < 3; i++) {
            sbCVV.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sbCVV.toString();
    }

    //PIN. ISO 9564-1 length 4-12.
    @GET
    @Path("/getpin")
    @Produces("text/plain")
    public String getPIN() {
        String s = "1234567890";
        Random randomGenerator = new Random();
        Integer pinLength = randomGenerator.nextInt(9)+4;
        StringBuffer sbPIN = new StringBuffer();

        for (int i = 0; i < pinLength; i++) {
            sbPIN.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sbPIN.toString();
    }

    @GET
    @Path("/getokpo")
    @Produces("text/plain")
    @RequestMapping(value = "/{okpoType}", method = RequestMethod.GET)
    public @ResponseBody String getOKPO(@QueryParam("okpoType") String okpoType) {
        //TODO параметризовать ЮР - 8, для ИП длина 10. По умолчанию только 8 (последний символ - контрольная сумма).
        Integer okpoLength = 7;
        String IND_P = "ИП";
        if (IND_P.equals(okpoType)) {
             okpoLength = 9;
        }

        String s = "1234567890";
        Random randomGenerator = new Random();

        StringBuffer sbOKPO = new StringBuffer();

        for (int i = 0; i < okpoLength; i++) {
            sbOKPO.append(s.charAt(new Random().nextInt(s.length())));
        }

        sbOKPO.append(calculateCRC(sbOKPO.toString()));

        return sbOKPO.toString();
    }

    //Метод рассчёта контрольной суммы по Методике расчета контрольного числа, приведенной в Правилах стандартизации
    //ПР 50.1.024-2005 "Основные положения и порядок проведения работ по разработке, ведению и применению общероссийских классификаторов"
    private String calculateCRC(String okpoNumber){
        String crc = "0";
        int sum = 0;
        //TODO http://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=EXP&n=369186&rnd=228224.3208731668&dst=100447&fld=134#0
        for (int i=0; i<okpoNumber.length(); i++) {
            sum += Integer.parseInt(okpoNumber.substring(i,i+1)) * i;
        }
        int balance = sum % 11;
        crc = String.valueOf(balance);

        return crc;
    }

    //Язык
    @GET
    @Path("/getlanguage")
    @Produces("text/plain")
    public String getLanguage() {
        File f = new File("D:\\data\\language.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Национальность
    @GET
    @Path("/getnationality")
    @Produces("text/plain")
    public String getNationality() {
        File f = new File("D:\\data\\nationality.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Гражданство
    @GET
    @Path("/getcitizenship")
    @Produces("text/plain")
    public String getCitizenship() {
        File f = new File("D:\\data\\citizenship.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Образование
    @GET
    @Path("/geteducation")
    @Produces("text/plain")
    public String getEducation() {
        File f = new File("D:\\data\\education.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Квалификация по образованию
    @GET
    @Path("/geteducationqualification")
    @Produces("text/plain")
    public String getEducationQualification() {
        File f = new File("D:\\data\\educationqualification.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }
    //Регион
    @GET
    @Path("/getregion")
    @Produces("text/plain")
    public String getRegion() {
        File f = new File("D:\\data\\region.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }
    //Город/Населенный пункт
    @GET
    @Path("/getcity")
    @Produces("text/plain")
    public String getCity() {
        File f = new File("D:\\data\\city.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Тип населенного пункта
    @GET
    @Path("/getcitytype")
    @Produces("text/plain")
    public String getCityType() {
        File f = new File("D:\\data\\citytype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Тип улицы
    @GET
    @Path("/getstreettype")
    @Produces("text/plain")
    public String getStreetType() {
        File f = new File("D:\\data\\streettype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Улица
//    @GET
//    @Path("/getstreet")
//    @Produces("text/plain")
//    public String getStreet() {
//        Random randomGenerator = new Random();
//        String stringArray[] = {"Ленина","Центральная","Главная"};
//        //TODO to file
//        return stringArray[randomGenerator.nextInt(stringArray.length)];
//    }

    //Право на проживание
    @GET
    @Path("/getrighttolive")
    @Produces("text/plain")
    public String getRightToLive() {
        File f = new File("D:\\data\\righttolive.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Семейное положение (по документу Общероссийский классификатор информации о населении (ОКИН))
    @GET
    @Path("/getmartialstatus")
    @Produces("text/plain")
    public String getMartialStatus() {
        File f = new File("D:\\data\\martialstatus.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    //Степень родства по ОКИН код 11 (http://classifikators.ru/okin/11)
    @GET
    @Path("/getrelationdegree")
    @Produces("text/plain")
    public String getRelationDegree() {
        Random randomGenerator = new Random();
        String stringArray[] = {"Муж (супруг)","Жена (супруга)",
                "Свёкор","Свекровь","Тесть","Разошелся (разошлась)"};
        //TODO to file
        return stringArray[randomGenerator.nextInt(stringArray.length)];
    }

    //Формат СНИЛС: «123-456-789 01», где цифры могут быть любыми, а последние две являются контрольной суммой, вычисляемой по особому алгоритму[4]
    @GET
    @Path("/getsnils")
    @Produces("text/plain")
    public String getSNILS() {
        int[] snilsArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};

        List<Integer>  snils_array = getShuffleArray(snilsArray);
        StringBuilder sb = new StringBuilder();
        sb.delete(0, sb.length());

        for (int i =0; i<3; i++) {
            sb.append(snils_array.get(i));
        }
        sb.append("-");
        for (int i =3; i<6; i++) {
            sb.append(snils_array.get(i));
        }
        sb.append("-");
        for (int i =6; i<9; i++) {
            sb.append(snils_array.get(i));
        }
        sb.append(" ");
        sb.append(calculateSnils(snils_array));

        return sb.toString();
    }
    private static String calculateSnils(List<Integer> snilsArray){
        String snils = "0";
        int sum = 0;
        for (int i=0; i<9; i++) {
            sum += snilsArray.get(i) * i;
        }
        int balance = sum % 101;
        snils = String.valueOf(balance);

        return snils;
    }
    public static List<Integer> getShuffleArray(int[] array) {
        List<Integer> solution = new ArrayList<Integer>();
        for (int i = 1; i < array.length; i++)
        {
            solution.add(array[i]);
        }
        Collections.shuffle(solution);
        return solution;

    }

}
