/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.app.dekonts;

import bb.app.account.AccountMisc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.sun.tools.javac.util.StringUtils;
import jaxesa.redis.RedisAPI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import jaxesa.barcode.LabelItem;
import jaxesa.email.JEmail;
import jaxesa.util.RunTimeOutputs;
import jaxesa.util.Util;
import org.json.JSONArray;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author esabil
 * 
 * commit dec 5 2019 
 */
public class BbAppDekonts 
{
    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://graph.facebook.com/me?access_token=EAAlZBOHbF9ZCQBAB90GAH58IFUfpZA7zKOxdDJPF2VKuiXGZAZBs0HRtoAvJBaki0fzhHribPZCzgAXuIgbJVdzYs8ZBEgpyQ2XrZCLtsz8FtTSyEavXRc4yWTWSezdNaYCKqo5FUVExSW0XHSNLxcEgWROGI03bJzDdMNwPCJPWsfEXajA3EO3kZBAe1Q7TZBrueEZAsRu6QqQNwZDZD";

    private static final String POST_URL = "https://localhost:9090/SpringMVCExample/home";

    private static final String POST_PARAMS = "userName=Pankaj";
        
    public static void main(String[] args)
    {
        try
        {
            String sx = "esabil";
            String[] a = sx.split(",");
            
            String str = "a";
            str = str.repeat(1000000);
            long ilen = str.length();
            
            Util.JSON.isJSONArray("[]");
            
            String sRows = "[{\"a\":1},{\"b\":2}]";
            JsonArray jsRows = new JsonArray();
            jsRows = Util.JSON.toArray(sRows);
            
            JEmail email = new JEmail();

            //email.send(pFrom, pTo, getContent(pLangCode, ID_SUBJECT), pPWD, sContent, "text/html; charset=UTF-8");
            email.send("", "", "", "", "", "text/html;charset=UTF-8");
            /*
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GET_URL))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            */
            //String sRsp =Util.HTTP.sendHTTPRequest("GET", GET_URL, "");

            String sMultiArgs = "[" + 
                                    "{'title':{'name':'BULBULLER','x':370,'y':100,'w':0,'h':0,'size':0,'fsize':0},'dets':{'name':'MODIVA 2215 14020','x':370,'y':200,'w':0,'h':0,'size':0,'fsize':0},'price':{'name':'194,50','x':450,'y':300,'w':0,'h':0,'size':0,'fsize':0},'size':{'name':'M','x':150,'y':385,'w':0,'h':0,'size':0,'fsize':0},'font':{'x':65,'y':45,'w':0,'h':0,'size':0,'fsize':0},'qr':{'name':'www.shipshuk.com','x':0,'y':0,'w':375,'h':375,'size':0,'fsize':0}}" + 
                                    "," +
                                    "{'title':{'name':'BULBULLER','x':370,'y':100,'w':0,'h':0,'size':0,'fsize':0},'dets':{'name':'MODIVA 2215 14020','x':370,'y':200,'w':0,'h':0,'size':0,'fsize':0},'price':{'name':'194,50','x':450,'y':300,'w':0,'h':0,'size':0,'fsize':0},'size':{'name':'M','x':150,'y':385,'w':0,'h':0,'size':0,'fsize':0},'font':{'x':65,'y':45,'w':0,'h':0,'size':0,'fsize':0},'qr':{'name':'www.shipshuk.com','x':0,'y':0,'w':375,'h':375,'size':0,'fsize':0}}" +
                                "]";

            Gson gson = new Gson();

            test[] jsoArr = gson.fromJson(sMultiArgs, test[].class);
            
            
            /*
            test[] JSOTestArray = gson.fromJson(sMultiArgs, test[].class);

            ArrayList<test> jsoArr = new ArrayList<test>();
            jsoArr = Util.JSON.parseJSONArray(sMultiArgs);
            */
            //test[] jsoArr = Util.JSON.parseJSONArray(sMultiArgs, test[].class);

            for (Object jso: jsoArr)
            {
                test aTest = new test();
                
                aTest = (test)jso;
                
                //String s = "a";
            }
            
            //Runtime rt = Runtime.getRuntime();
            //Process proc = rt.exec("mkdir /Users/ashah/Desktop/new-folder");
            //printOutput errorReported, outputMessage;

            test qrSets = new test();
            
            qrSets.title.x = 370;
            qrSets.title.y = 100;
            qrSets.title.name = "BULBULLER";
            
            qrSets.font.x = 65;
            qrSets.font.y = 45;
            
            qrSets.dets.x = 370;
            qrSets.dets.y = 200;
            qrSets.dets.name = "MODIVA 2215 14020";
            
            qrSets.price.x = 450;
            qrSets.price.y = 300;
            qrSets.price.name = "194,50";
            
            qrSets.size.x = 150;
            qrSets.size.y = 385;
            qrSets.size.name = "M";
            
            qrSets.qr.x = 0;
            qrSets.qr.y = 0;
            qrSets.qr.w = 375;
            qrSets.qr.h = 375;
            qrSets.qr.name = "www.shipshuk.com";
            
            String sJSON = Util.JSON.Convert2JSON(qrSets);
            
            sJSON = "{'title':{'name':'BULBULLER','x':370,'y':100,'w':0,'h':0,'size':0,'fsize':0},'dets':{'name':'MODIVA 2215 14020','x':370,'y':200,'w':0,'h':0,'size':0,'fsize':0},'price':{'name':'194,50','x':450,'y':300,'w':0,'h':0,'size':0,'fsize':0},'size':{'name':'M','x':150,'y':385,'w':0,'h':0,'size':0,'fsize':0},'font':{'x':65,'y':45,'w':0,'h':0,'size':0,'fsize':0},'qr':{'name':'www.shipshuk.com','x':0,'y':0,'w':375,'h':375,'size':0,'fsize':0}}";

            test receiver = new test();
            receiver = (test) Util.JSON.Convert2Obj(sJSON, test.class);
            
            receiver = receiver;
            
            System.out.println("App is starting");

            try
            {
                //String OSName = System.getProperty("os.name");
                //boolean rc = Util.Sys.isOSMacOS();

                Runtime rt = Runtime.getRuntime();
                //Process proc = rt.exec("javac");
                //Process proc = rt.exec("mkdir /Users/test");

                /*
                Process proc = rt.exec("clamscan /Users/esabil/Documents/files/kasa2020_1.txt");
                InputStream stderr = proc.getErrorStream();
                InputStreamReader isr = new InputStreamReader(stderr);
                BufferedReader br = new BufferedReader(isr);
                
                InputStream stdin = proc.getInputStream();
                InputStreamReader iso = new InputStreamReader(stdin);
                BufferedReader ou = new BufferedReader(iso);
                
                String line = null;
                */
                
                RunTimeOutputs rtos = new RunTimeOutputs();
                
                rtos = Util.Sys.executeCommand("clamscan /Users/esabil/Documents/files/kasa2020_1.txt");
                
                System.out.println("<OUTPUT>");
                String line = null;
                
                while ( (line = rtos.out.readLine()) != null)
                    System.out.println(line);

                System.out.println("</OUTPUT>");

                System.out.println("<ERROR>");

                while ( (line = rtos.err.readLine()) != null)
                    System.out.println(line);

                System.out.println("</ERROR>");

                int exitVal = rtos.proc.waitFor();
                System.out.println("Process exitValue: " + exitVal);
                
                
                /*
                errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
                outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
                errorReported.start();
                outputMessage.start();
                */
            }
            catch(Exception e)
            {
                
            }
            //================================================================
            // 
            // REDIS TEST
            // 
            //================================================================
            /*
            Jedis jedis = new Jedis("localhost");
            System.out.println(jedis.ping());
            System.out.println("jedis ping successful");
            jedis.set("name", "esa");
            System.out.println("redis key" + " name" + ":" + jedis.get("name"));
            */
            /*
            try
            {
                //Jedis jedis = new Jedis("localhost");
                //System.out.println(jedis.ping());

                //poolConfig.setMaxTotal(128);
                //JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 10000, "");

                //JedisPool gJedisPool = new JedisPool("localhost", 6379);

                Util.Redis.connect("localhost", 6379);
                Jedis jedis = RedisAPI.getConnection();
                Util.Redis.JString.set(jedis, "esa", "bil");
                String s = Util.Redis.JString.get(jedis, "esa");

                long x = Util.Redis.JNumber.increase(jedis, "test", 2);

                x = Util.Redis.JNumber.decrease(jedis, "test", 1);

                s += "---";
            }
            catch(Exception e)
            {
                String s = e.getMessage();
            }
            */

            String TOKEN_ENCODE_SIGN = "\\*ESA\\*";


            String test = "adsfas asfsafasfa==";

            test = test.replaceAll("=", TOKEN_ENCODE_SIGN);
            test = test.replaceAll(TOKEN_ENCODE_SIGN, "=");

            //String sInFilePath  = "/Users/esabil/Documents/files/Hesap_Hareket_Detay_64265549_TL.pdf";//isbank
            //String sInFilePath  = "/Users/esabil/Documents/files/64050199824_20190829_16393162_HesapOzeti.pdf";//isbank
            String sInFilePath  = "/Users/esabil/Documents/files/YKB_Hesap_Hareket_Detay_64265549_TL.pdf";//isbank
            //String sInFilePath  = "/Users/esabil/Documents/files/KUVEYT_Musterino_6667543_Ekno_1_2019910152032_ekstre.pdf";
            //String sInFilePath  = "/Users/esabil/Documents/files/KUVEYT_Musterino_6667543_Ekno_3900_201991015217_ekstre.pdf";
            String sOutFilePath = "/Users/esabil/Documents/files/dekont_summary.txt";//output file


            String sURL = "http://localhost:8080/bb-rest-api/rest/bulbuller/dekont/api/neweodentry/?";

            //sURL += Util.url.addURLParam("userid"   , ShipShukUser.getUserId(true, null) , false, true);
            String sMsg = "";
//            sMsg = prepareMessage("1", "en", "tr", "141230", "1", "2019", "1", "12","1200.40");

            String sFileKasa = "/Users/esabil/Documents/files/kasa2020_1.txt";
            //String sFileKasa = "/Users/esabil/Documents/files/kasa2020_1.txt";
            BufferedReader reader = new BufferedReader(new FileReader(sFileKasa)); 
            String line = reader.readLine();
            int i = 0;
            while (line != null) 
            {
                String[] sDataParts = line.split("\t");
                String sYear  = sDataParts[0];
                String sMonth = sDataParts[1];
                sMonth = Util.Str.leftPad(sMonth, "0", 2);
                String sDay   = sDataParts[2];
                sDay = Util.Str.leftPad(sDay, "0", 2);
                String sAmount   = sDataParts[3];

                if (i!=0)
                {
                    //test merchant (online sales set 0 for now)
                    sMsg = prepareMessage("1", "en", "tr", "141230", "38482645", sYear, sMonth, sDay, sAmount, "0");

                    String sResp = Util.HTTP.sendHTTPRequest("POST", sURL, sMsg);
                    System.out.println(line);
                    // read next line
                }
                i++;
                line = reader.readLine();
            }
            reader.close();


            //esabil bulbul
            String sDate = AccountMisc.formatDate("13/08/2019");

            ArrayList<DekontFields> flds = DekontMethods.processFile(sInFilePath, sOutFilePath, false, "NECATİ BÜLBÜL");


            //System.out.println(rc);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    static private JedisPoolConfig buildPoolConfig() 
    {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
    
    public static String prepareMessage(String pUserId, 
                                        String pLang, 
                                        String pCountry, 
                                        String pSessionId, 
                                        String pMrcId, 
                                        String pYear,
                                        String pMonth,
                                        String pDay,
                                        String pTot,
                                        String pOnl)
    {
        String sMsg = "";

        sMsg += "userid="     + pUserId  + "&";
        sMsg += "lang="       + pLang + "&";;
        sMsg += "country="    + pCountry + "&";
        sMsg += "sessionid="  + pSessionId  + "&";
        sMsg += "mrcid="      + pMrcId  + "&";
        sMsg += "year="       + pYear   + "&";
        sMsg += "month="      + pMonth  + "&";
        sMsg += "day="        + pDay    + "&";
        sMsg += "tot="        + pTot + "&";
        sMsg += "onl="        + pOnl;

        return sMsg;
    }   
}
