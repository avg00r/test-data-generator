package ru.javastudy.ws.webservices.rest;

import org.springframework.web.bind.annotation.*;
import ru.javastudy.ws.main.CardNumberGenerator;
import ru.javastudy.ws.main.GetRandomLine;
import ru.javastudy.ws.main.TestRunner;


import javax.ws.rs.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.*;


import java.text.DateFormat;

import java.text.SimpleDateFormat;


public class GeneratorRestComponent {

    @GET
    @Path("/start")
    @Produces("text/plain")
    public String start(@QueryParam("iter") int iter) throws MalformedURLException, IOException {
        return TestRunner.start(iter);
    }

    public GeneratorRestComponent() {}


    /**
     * Возвращает СНИЛС
     * Формат СНИЛС: «123-456-789 01», где цифры могут быть любыми, а последние две являются контрольной суммой, вычисляемой по особому алгоритму[4]
     * @return
     */
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

    /**
     * Возвращает ИНН
     * @param paramVal передаётся количество символов ИНН - 10 либло 12.
     * @return
     */
    @GET
    @Path("/getinn")
    @Produces("text/plain")
    public String getINN(@QueryParam("paramVal") int paramVal) {
        int[] factor1 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int[] factor2 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
        int[] factor3 = {2, 4, 10, 3, 5, 9, 4, 6, 8};
        int[] innArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        int[] innArray2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3};

        StringBuilder sb = new StringBuilder();

        if (paramVal == 10) {
            int sum = 0;
            List<Integer>  snils_array1 = getShuffleArray(innArray);
            for (int i = 0; i < 9; i++) {
                sum+= factor3[i]*snils_array1.get(i);
                sb.append(snils_array1.get(i));
            }
            int balance = sum % 11;
            if (balance == 10) {
                balance = 0;
            }
            sb.append(balance);
        }
        else if (paramVal == 12) {
            int sum1 = 0;
            int sum2 = 0;
            List<Integer>  snils_array2 = getShuffleArray(innArray2);
            for (int i = 0; i < 10; i++) {
                sum1+= factor1[i]*snils_array2.get(i);
                sum2+= factor2[i]*snils_array2.get(i);
                sb.append(snils_array2.get(i));
            }
            int balance1 = sum1 % 11;
            int balance2 = sum2 % 11;
            if (balance2 == 10) {
                balance2 = 0;
            }
            sb.append(balance1);
            sb.append(balance2);
        }
        else {
            return "Ошибка при вводе входных параметров!";
        }

        return sb.toString();
    }

    /**
     * Генерация номера кредитной карты
     * @param many код сс
     * @param cardType вид карты (MasterCard/Visa)
     * @return
     */
    @GET
    @Path("/getcardnumber")
    @Produces("text/plain")
    public String getCardNumber(@QueryParam("many") String many, @QueryParam("cardType") String cardType) {
        int howMany = 0;
        try {
            howMany = Integer.parseInt(many);
        } catch (Exception e) {
            System.err
                    .println("Неправильный СС номеру");
        }
        String[] creditcardnumbers;
        switch (cardType) {
            case "mastercard":
                creditcardnumbers = CardNumberGenerator.generateMasterCardNumbers(howMany);
                break;
            case "visa":
                creditcardnumbers = CardNumberGenerator.generateVisaCardNumbers(howMany);
                break;
            default:
                creditcardnumbers = CardNumberGenerator.generateMasterCardNumbers(howMany);
                break;
        }

        return creditcardnumbers.length == 0 ? "Неправильные параметры" : creditcardnumbers[0];
    }

    /**
     * Генерирует КПП
     * @return КПП ном
     */
    @GET
    @Path("/getkpp")
    @Produces("text/plain")
    public String getKpp() {
        String[] sample;
        File f = new File("D:\\data\\kpp.csv");
        String kppNumber = GetRandomLine.getRandomLine(f, "UTF-8");
        String[] arrNumber =  {"100","200","300","400","500","600","700","800","900"};
        for (int i = 0; i< arrNumber.length; i++) {
            if (arrNumber[i].equals(kppNumber)) {
                kppNumber = "0" + kppNumber;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(kppNumber);

        //sb.append(" ");
        int[] reasonArray  = new int[99];
        for (int i=0; i<reasonArray.length; i++) {
            reasonArray[i]=i+1;
        }
        List<Integer>  reason_arrayList = getShuffleArray(reasonArray);
        sb.append(reason_arrayList.get(1));

        //sb.append(" ");
        int[] reasonArray2  = new int[999];
        for (int i=1; i<999; i++) {
            reasonArray2[i]=i;
        }
        List<Integer>  position_arrayList2 = getShuffleArray(reasonArray2);
        sb.append(position_arrayList2.get(1));
        return sb.toString();
    }

    /**
     * Возвращает случайный комментарий
     * @return Случайный комментарий
     */
    @GET
    @Path("/getcomment")
    @Produces("text/plain")
    public String getComment() {
        File f = new File("D:\\data\\cityAction.csv");
        String cityAction = GetRandomLine.getRandomLine(f, "UTF-8");

        File f1 = new File("D:\\data\\city.csv");
        String city = GetRandomLine.getRandomLine(f1, "UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append(cityAction).append(" ").append(city);
        return sb.toString();
    }

    @GET
    @Path("/getcurrenncy")
    @Produces("text/plain")
    public String getCurrency() {

        return "blah-blah-blah";
    }

    //Дата
    @GET
    @Path("/getdate")
    @Produces("text/plain")
    public Date getDate() {

        return new Date();
    }

    /**
     * Метод возвращает имя организации, выдавшей документ
     * @return Регистрационный знак ТС
     */
    @GET
    @Path("/getissuer")
    @Produces("text/plain")
    public String getIssuer() {
        File fileIssuerShortNames = new File("D:\\data\\issuersShortName.csv");
        String issuerShortName = GetRandomLine.getRandomLine(fileIssuerShortNames, "UTF-8");

        final File fCity = new File("D:\\data\\city.csv");
        StringBuffer sb = new StringBuffer();
        sb.append("Отделением ");
        sb.append(GetRandomLine.getRandomLine(fileIssuerShortNames, "UTF-8")).append(" России в городе ").append(GetRandomLine.getRandomLine(fCity, "UTF-8"));
        return sb.toString();
    }


    /**
    /**
     * Генерирует серию и номер свидетельства о рождении
     * @return серия и номер свидетельства о рождении
     */
    @GET
    @Path("/getbirthserialnumber")
    @Produces("text/plain")
    public String getBirthSerialNumber() {
        Random randomGenerator = new Random();
        Integer number = randomGenerator.nextInt(100);
        final String latin = "IXVLMD";
        final String rus = "БВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        final String s = "1234567890";

        StringBuffer sb = new StringBuffer();
        int latinLenght = randomGenerator.nextInt(3) + 1;
        for (int i = 0; i < latinLenght; i++)
        {
            sb.append(latin.charAt(new Random().nextInt(latin.length())));
        }
        sb.append("-");
        for (int i = 0; i < 2; i++)
        {
            sb.append(rus.charAt(new Random().nextInt(rus.length())));
        }
        sb.append(" № ");

        for (int i = 0; i < 6; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

    /**
     * Вывод IP адреса
     * @return ip-адрес
     */
    @GET
    @Path("/getip")
    @Produces("text/plain")
    public String getip() {
        Random r = new Random();
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }

    /**
     * Вывод рандомного чилса
     * @return Случайное число
     */
    //Число
    @GET
    @Path("/getnumber")
    @Produces("text/plain")
    public String getNumber() {
        Random randomGenerator = new Random();
        Integer number = randomGenerator.nextInt(100);

        return number.toString();
    }

    /**
     * Генерация времени
     * @return время
     */
    @GET
    @Path("/gettime")
    @Produces("text/plain")
    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Выдаёт случайный тип документа
     * @return тип документа
     */
    @GET
    @Path("/getdocumenttype")
    @Produces("text/plain")
    public String getDocumentType() {
        File f = new File("D:\\data\\documenttype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Случайный тип контрагента
     * @return тип контрагента
     */
    @GET
    @Path("/getcontragenttype")
    @Produces("text/plain")
    public String getContrAgentType() {
        File f = new File("D:\\data\\contragenttype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получения материала стен
     * @return тип материала
     */
    @GET
    @Path("/getwallmaterial")
    @Produces("text/plain")
    public String getWallMaterial() {
        File f = new File("D:\\data\\wallmaterial.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получение случайного состояния квартиры
     * @return состояние квартиры
     */
    @GET
    @Path("/getapartmentstate")
    @Produces("text/plain")
    public String getApartmentState() {
        File f = new File("D:\\data\\apartmentstate.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получение случайного вероисповедания
     * @return религия
     */
    @GET
    @Path("/getreligion")
    @Produces("text/plain")
    public String getReligion() {
        File f = new File("D:\\data\\religion.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }
//
    /**
     * Получение случайного названия Банка
     * @return Наименование Банка
     */
    @GET
    @Path("/getbankname")
    @Produces("text/plain")
    public String getBankName() {
        File f = new File("D:\\data\\bankname.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает случайный металл
     * @return Металл
     */
    @GET
    @Path("/getmetal")
    @Produces("text/plain")
    public String getMetal() {
        File f = new File("D:\\data\\metal.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает случайный тип банковской карты
     * @return тип банковской карты
     */
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
        return dict[randomGenerator.nextInt(dict.length)];
    }

    /**
     * Получение случайного цвета
     * @return цвет
     */
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

    /**
     * Возвращает вид недвижимости
     * @return Вид недвижимости
     */
    @GET
    @Path("/getrealestatetype")
    @Produces("text/plain")
    public String getRealEstateType() {
        File f = new File("D:\\data\\realestatetype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает случайную единицу измерения
     * @return Единица измерения
     */
    @GET
    @Path("/getunit")
    @Produces("text/plain")
    public String getUnit() {
        File f = new File("D:\\data\\unit.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает вид ТС
     * @return Вид ТС
     */
    @GET
    @Path("/gettskind")
    @Produces("text/plain")
    public String getTSKind() {
        File f = new File("D:\\data\\tskind.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает марку ТС
     * @return Марка ТС
     */
    @GET
    @Path("/getsmark")
    @Produces("text/plain")
    public String getTSMark() {
        File f = new File("D:\\data\\tsmark.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает право собственности
     * @return Вид права собственности
     */
    @GET
    @Path("/getownershipright")
    @Produces("text/plain")
    public String getOwnershipRight() {
        File f = new File("D:\\data\\ownershipright.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает аббривиатуру организационно правовой формы организации
     * @return Аббривиатуру организационно правовой формы организации
     */
    @GET
    @Path("/getorganizationform")
    @Produces("text/plain")
    public String getOrganizationForm() {
        File f = new File("D:\\data\\organizationform.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает аббривиатуру страны
     * @return Аббривиатуру страны
     */
    @GET
    @Path("/getcountryabbr")
    @Produces("text/plain")
    public String getCountryAbbr() {
        File f = new File("D:\\data\\countryAbbr.csv");
        String countryAbbr = GetRandomLine.getRandomLine(f, "UTF-8");

        return countryAbbr;
    }
    /**
     * Возвращает должность из стандартного классификатора должностей
     * @return должность
    */
    //Должность
    @GET
    @Path("/getposition")
    @Produces("text/plain")
    public String getPosition() {
        File f = new File("D:\\data\\position.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возращает случайное звание
     * @return ранг
     */
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

    /**
     * Генерирует случайный cvv код
     * @return
     */
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

    /**
     * Генерация пин-кода по ISO 9564-1 длина 4-12.
     * @return пин-код
     */
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

    /**
     * Генерация ОКПО
     * @param okpoType тип лица (ЮР/ИП)
     * @return номер ОКПО
     */
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

    /**
     * Рассчёт контрольной суммы по методике расчёта контрольного числа
     * Метод рассчёта контрольной суммы по Методике расчета контрольного числа, приведенной в Правилах стандартизации
     * ПР 50.1.024-2005 "Основные положения и порядок проведения работ по разработке, ведению и применению общероссийских классификаторов"
     * @param okpoNumber номер ОКПО
     * @return контрольная сумма
     */
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

    /**
     * Возвращает случайный язык
     * @return язык
     */
    @GET
    @Path("/getlanguage")
    @Produces("text/plain")
    public String getLanguage() {
        File f = new File("D:\\data\\language.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает случайную национальность
     * @return национальность
     */
    @GET
    @Path("/getnationality")
    @Produces("text/plain")
    public String getNationality() {
        File f = new File("D:\\data\\nationality.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получание случайного гражданства
     * @return гразданство
     */
    @GET
    @Path("/getcitizenship")
    @Produces("text/plain")
    public String getCitizenship() {
        File f = new File("D:\\data\\citizenship.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получанет случайный тип образования
     * @return образование
     */
    @GET
    @Path("/geteducation")
    @Produces("text/plain")
    public String getEducation() {
        File f = new File("D:\\data\\education.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает квалификацию по образованию
     * @return квалификация
     */
    @GET
    @Path("/geteducationqualification")
    @Produces("text/plain")
    public String getEducationQualification() {
        File f = new File("D:\\data\\educationqualification.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получение случайного региона
     * @return регион
     */
    @GET
    @Path("/getregion")
    @Produces("text/plain")
    public String getRegion() {
        File f = new File("D:\\data\\region.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получение случайного города или населённого пункта
     * @return город/населённый пункт
     */
    @GET
    @Path("/getcity")
    @Produces("text/plain")
    public String getCity() {
        File f = new File("D:\\data\\city.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получение случайного населённого пункта
     * @return населённый пункт
     */
    @GET
    @Path("/getcitytype")
    @Produces("text/plain")
    public String getCityType() {
        File f = new File("D:\\data\\citytype.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Получение случайного типа улицы
     * @return тип улицы
     */
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

    /**
     *Возвращает право на проживание
     * @return право на проживание
     */
    @GET
    @Path("/getrighttolive")
    @Produces("text/plain")
    public String getRightToLive() {
        File f = new File("D:\\data\\righttolive.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает семейное положение
     * @return семейное положение
     */
    //Семейное положение (по документу Общероссийский классификатор информации о населении (ОКИН))
    @GET
    @Path("/getmartialstatus")
    @Produces("text/plain")
    public String getMartialStatus() {
        File f = new File("D:\\data\\martialstatus.csv");
        return GetRandomLine.getRandomLine(f, "UTF-8");
    }

    /**
     * Возвращает степень родства по ОКИН код 11 (http://classifikators.ru/okin/11)
     * @return степень родства
     */
    @GET
    @Path("/getrelationdegree")
    @Produces("text/plain")
    public String getRelationDegree() {
        Random randomGenerator = new Random();
        String stringArray[] = {"Муж (супруг)","Жена (супруга)",
                "Свёкор","Свекровь","Тесть","Разошелся (разошлась)"};
        return stringArray[randomGenerator.nextInt(stringArray.length)];
    }


    /**
     * Вычисление снилс
     * @param snilsArray массив снилс
     * @return
     */
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

    /**
     * Переставляет элементы в массиве
     * @param array массив
     * @return
     */
    private static List<Integer> getShuffleArray(int[] array) {
        List<Integer> solution = new ArrayList<Integer>();
        for (int i = 1; i < array.length; i++)
        {
            solution.add(array[i]);
        }
        Collections.shuffle(solution);
        return solution;

    }

    /**
     * Генерация ОГРН
     * @return ОГРН
     */
    @GET
    @Path("/getogrn")
    @Produces("text/plain")
    public String getOGRN() {
        StringBuilder sb = new StringBuilder();
        int[] firstArray  = {1, 5, 2, 6, 7, 8, 9, 3, 4};
        List<Integer>  first_arrayList = getShuffleArray(firstArray);

        sb.append(first_arrayList.get(1));
        int secondNumber  = 16;

        //sb.append(" ");
        sb.append(secondNumber);

        File f = new File("D:\\data\\subjectRF.csv");
        String ogrnThirdNumber = GetRandomLine.getRandomLine(f, "UTF-8");

        //sb.append(" ");
        sb.append(ogrnThirdNumber);
        String[] arrOgrnNumber =  {"1","2","3","4","5","6","7","8","9"};

/*
        for (int i = 0; i< arrOgrnNumber.length; i++) {
            if (arrOgrnNumber[i].equals(ogrnThirdNumber)) {
                ogrnThirdNumber = "0" + ogrnThirdNumber;
            }
        }
*/

        int[] forthArray  = new int[98];
        for (int i = 1; i < forthArray.length; i++){
            forthArray[i] = i;
        }
        List<Integer>  forth_arrayList = getShuffleArray(forthArray);
        Integer forthNumber = forth_arrayList.get(1);

        //sb.append(" ");
        sb.append(forthNumber);


        //sb.append(" ");
        int[] arrFive =  {1,2,3,4,5,6,7,8,9};
        List<Integer>  five_arrayList = getShuffleArray(arrFive);
        int[] fiveArray =  new int[5];
        for (int i=0; i<fiveArray.length; i++) {
            fiveArray[i] = five_arrayList.get(i);
            sb.append(fiveArray[i]);
        }

        //sb.append(" ");
        //Long resultData = ;
        BigInteger balance = BigInteger.valueOf(Long.parseLong(sb.toString()) % 11);
        String res = "0";
        if (balance.intValue() == 10) {
            res = "0";
            //sb.append(" ");
            sb.append(res);
        }
        else {
            res = balance.toString().substring(balance.toString().length()-1, balance.toString().length());

            //sb.append(" ");
            sb.append(res);
        }

        return sb.toString();
    }

    /**
     * Генерация ввалюты
     * @param lang на английском (en) / русском (ru)
     * @return валюта
     */
    @GET
    @Path("/getvaluta")
    @Produces("text/plain")
    public String getValuta(@QueryParam("lang") String lang) {
        String file_name = "";
        if ("RU".equals(lang.toUpperCase())) {
            file_name = "D:\\data\\valuta_ru.csv";
        }
        else if ("EN".equals(lang.toUpperCase())) {
            file_name = "D:\\data\\valuta_en.csv";
        }
        else {
            return "Ошибка при задании параметров";
        }
        File f = new File(file_name);
        String valuta = GetRandomLine.getRandomLine(f, "UTF-8");

        return valuta;
    }

    /**
     * Возвращает регистрационный знак ТС
     * @return Регистрационный знак ТС
     */
    @GET
    @Path("/gettsregnumber")
    @Produces("text/plain")
    public String getTSRegNumber() {
        String firstLetterPart = generateLetter(1).toString();
        String firstNumberPart = generateNum(3).toString();
        String secondLetterPart = generateLetter(2).toString();
        String secondNumberPart = generateTSCode(1).toString();
        return firstLetterPart + ' ' + firstNumberPart + ' ' + secondLetterPart + ' ' + secondNumberPart;
    }
    protected String generateLetter(int number)
    {
        char dict[] = {'А','В','Е','К','М','Н','О','Р','С','Т','У','Х'}; //строка содержит все доступные символы
        String result = "";
        Random rand = new Random();
        for(int i = 0; i < number; i++) {
            result = result + dict[rand.nextInt(dict.length)];
        }
        return result;
    }

    protected String generateNum(int number)
    {
        int NUMBER_LENGTH = number;
        String s = "1234567890";
        StringBuffer sbNumber = new StringBuffer();

        for (int i = 0; i < NUMBER_LENGTH; i++)
        {
            sbNumber.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sbNumber.toString();
    }

    protected String generateTSCode(int number)
    {
        String dict[] = {"102","116","118","121","125","121","138","150","152","154","159","161","164","173","174","177","197","199"}; //строка содержит все доступные символы
        List<String> dict2 = new ArrayList<>(Arrays.asList(dict));
        for(int i = 1; i<=99; i++)
        {
            if(i<10)
                dict2.add('0' + Integer.toString(i));
            else
                dict2.add(Integer.toString(i));
        }
        String result = "";
        Random rand = new Random();
        for(int i = 0; i < number; i++) {
            result = dict2.get(rand.nextInt(dict2.size()));
        }
        return result;
    }

    /**
     * Возвращает серию и номер паспорта РФ
     * @return серия и номер паспорта РФ
     */
    @GET
    @Path("/getpassport")
    @Produces("text/plain")
    public String getPassport() {
        Random rand = new Random();
        File fSub = new File("D:\\data\\subjectRF.csv");
        String result = GetRandomLine.getRandomLine(fSub, "UTF-8");
        List<String> dict = new ArrayList<>(Arrays.asList());
        for(int i = 0; i<=16; i++)
        {
            if(i<10) {
                dict.add('0' + Integer.toString(i));
            }
            else {
                dict.add(Integer.toString(i));
            }
        }
        result += " ";
        result +=  dict.get(rand.nextInt(dict.size()));
        result += " ";
        result += generateNum(6);

        return result;
    }
}
