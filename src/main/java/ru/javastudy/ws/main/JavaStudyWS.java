package ru.javastudy.ws.main;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created for JavaStudy.ru on 09.06.2016.
 */
public class JavaStudyWS {


    public static void main(String[] args) throws IOException {
        String restServiceUrl = "http://localhost:8181/rest/helloRestService/getdoc";

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(restServiceUrl);

        Header mtHeader = new Header();
        mtHeader.setName("content-type");
        mtHeader.setValue("application/x-www-form-urlencoded");
        getMethod.addRequestHeader(mtHeader);

        mtHeader = new Header();
        mtHeader.setName("accept");
        mtHeader.setValue("application/xml");
        getMethod.addRequestHeader(mtHeader);

        httpClient.executeMethod(getMethod);
        String output = getMethod.getResponseBodyAsString();

        System.out.println(output);
    }

}
