/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.app.dekonts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import jaxesa.util.Util;

/**
 *
 * @author esabil
 */
public class BbAppDekonts 
{
    public static void main(String[] args)
    {
        try
        {
            //String sInFilePath  = "/Users/esabil/Documents/files/Hesap_Hareket_Detay_64265549_TL.pdf";//isbank
            //String sInFilePath  = "/Users/esabil/Documents/files/64050199824_20190829_16393162_HesapOzeti.pdf";//isbank
            String sInFilePath  = "/Users/esabil/Documents/files/YKB_Hesap_Hareket_Detay_64265549_TL.pdf";//isbank
            //String sInFilePath  = "/Users/esabil/Documents/files/KUVEYT_Musterino_6667543_Ekno_1_2019910152032_ekstre.pdf";
            //String sInFilePath  = "/Users/esabil/Documents/files/KUVEYT_Musterino_6667543_Ekno_3900_201991015217_ekstre.pdf";
            String sOutFilePath = "/Users/esabil/Documents/files/dekont_summary.txt";//output file


            String sURL = "http://localhost:8080/bb-wapi-dekont-converter/rest/bulbuller/dekont/api/neweodentry/?";

            //sURL += Util.url.addURLParam("userid"   , ShipShukUser.getUserId(true, null) , false, true);
            String sMsg = "";
//            sMsg = prepareMessage("1", "en", "tr", "141230", "1", "2019", "1", "12","1200.40");

            //String sFileKasa = "/Users/esabil/Documents/files/kasa2018_4.txt";
            String sFileKasa = "/Users/esabil/Documents/files/kasa2019_1.txt";
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
                    sMsg = prepareMessage("1", "en", "tr", "141230", "1", sYear, sMonth, sDay, sAmount);

                    String sResp = Util.HTTP.sendHTTPRequest("POST", sURL, sMsg);
                    System.out.println(line);
                    // read next line
                }
                i++;
                line = reader.readLine();
            }
            reader.close();

            
            
            String sDate = DekontMisc.formatDate("13/08/2019");
            
            ArrayList<DekontFields> flds = DekontMethods.processFile(sInFilePath, sOutFilePath, false, "NECATİ BÜLBÜL");
            
            
            //System.out.println(rc);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    
    public static String prepareMessage(String pUserId, 
                                        String pLang, 
                                        String pCountry, 
                                        String pSessionId, 
                                        String pMrcId, 
                                        String pYear,
                                        String pMonth,
                                        String pDay,
                                        String pTot)
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
        sMsg += "tot="        + pTot;

        return sMsg;
    }
}
