package onJenkinsTestsAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class QAAutomation {
              
              public static void main(String[] args) {

                           try {
                                         
                               System.setProperty("http.proxyHost", "vsproxynp2");
                               System.setProperty("http.proxyPort", "8080");
                               System.setProperty("https.proxyHost", "vsproxynp2");
                               System.setProperty("https.proxyPort", "8080");
                               Authenticator.setDefault(new Authenticator() {
                                   protected PasswordAuthentication getPasswordAuthentication() {

                                       return new PasswordAuthentication("z_elnd_auto_proxy", "Fj0zWBGJ%23F9%7D2ur61%25dn".toCharArray());
                                   }
                               });
                               
                                         CloseableHttpClient httpClient = HttpClients.createDefault();
                                         
                                         httpClient = HttpClientBuilder.create()
                                             .useSystemProperties()
                                             .build();
                                         
                                         System.out.println(httpClient);

                           
                                         String url = "https://api.frbnp2.com/elending/los/healthcheck";
                                         HttpGet request = new HttpGet(url);
                                         request.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVF9DRVJUIiwicGkuYXRtIjoiNCJ9.eyJzY29wZSI6WyJlYWdsZV9sZW5kaW5nX2xvc19hcGlfY2xpZW50IiwibWRtX2xlbmRpbmdfY2xpZW50IiwicGFydG5lcl9reWNfbGVuZGluZyJdLCJjbGllbnRfaWQiOiJsb3NfZWFnbGVfbGVuZGluZyIsImV4cCI6MTYwMjM2OTc4OX0.c31E7QRcmBzbxXvTLxwyEPZ-eoFJTnezDO4y3AqrQr2QINMln-xocHRQcen6h8jE-sME3-zrvcTTcSMHRb2oesP5YYVI6ylsg1T36wutD8zIW4Smt9hQb6N1Bfwzn9YjRLFsIaIP-3ktLXH5CJYsEHPeQ6dCp8Pcawz49YKkUJb0gV9psMS1wIUpTRISg5fEwOa22Ms7ENv46b3wtTJONrD4X7tFkgFR3ZF0tDNLyid2slKpCFWxC44iJOA79xf5TfcAQA-_mStsfhgrLnaJa4zYubeWEiwARlJRmbqrktql3ZEfSk3XUZo-HeXU_nwqnahSV8KVAesUCFwthEC7NQ");
                                         
                                         HttpResponse response = httpClient.execute(request);
                                         
                           System.out.println("*****************************************");
                           System.out.println(response.getStatusLine().getStatusCode());

                                         BufferedReader br = new BufferedReader(
                                      new InputStreamReader((response.getEntity().getContent())));

                                         String output;
                                         System.out.println("Output from API .... \n");
                                         while ((output = br.readLine()) != null) {
                                                       System.out.println(output);
                                         }

                                         httpClient.getConnectionManager().shutdown();

                             } catch (MalformedURLException e) {

                                         e.printStackTrace();
                           
                             } catch (IOException e) {

                                         e.printStackTrace();

                             }

              }
              
}
