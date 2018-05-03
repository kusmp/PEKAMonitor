
import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.*;

public class Receiver {

    URL url;

    public Receiver() {
        try{
        this.url = new URL("https://www.peka.poznan.pl/vm/method.vm?ts=1525359631644");
        } catch(MalformedURLException e){
            e.printStackTrace();
        }
       // try{
      //  readMessage();
       // } catch(IOException e){
       //     e.printStackTrace();
       // }
    }

    public static Vehicles sendRequest() throws IOException{
       String url = "https://www.peka.poznan.pl/vm/method.vm?ts=1525359631644";
       URL obj = new URL(url);
       HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

       conn.setRequestMethod("POST");
       conn.setRequestProperty("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
       conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

       String urlParameters = "method=getTimes&p0=%7B%22symbol%22%3A%22AWF41%22%7D";

       conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonBytes = String.valueOf(response).getBytes();
        Vehicles vehicles = objectMapper.readValue(jsonBytes, Vehicles.class);
       // System.out.println("Object: " + vehicles);
//        Success success = vehicles.getSuccess();
//       Times[] times = success.getTimes();
//       for(Times dest : times){
//           System.out.println(dest.getDirection());
//       }
        return vehicles;

    }

    public static String readMessage() throws IOException{
        String url = "https://www.peka.poznan.pl/vm/method.vm?ts=1525359631644";
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String urlParameters = "method=findMessagesForBollard&p0=%7B%22symbol%22%3A%22AWF41%22%7D";

        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        String returnMessage="";
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] jsonBytes = String.valueOf(response).getBytes();
        Message message = objectMapper.readValue(jsonBytes, Message.class);
        SuccessMessage[] sm = message.getSuccess();
        for(SuccessMessage osm : sm){
           returnMessage = osm.getContent();
           break;
        }
        System.out.println(returnMessage);
        returnMessage = new HtmlToPlainText().getPlainText(Jsoup.parse(returnMessage));
        returnMessage = returnMessage.replaceAll("[\r\n]+", " ");
        System.out.println(returnMessage);
        return returnMessage;
    }


}
