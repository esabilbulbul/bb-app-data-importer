/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.app.dekonts;

import java.util.ArrayList;

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
            //String sInFilePath  = "/Users/esabil/Documents/files/64050199824_20190829_16393162_HesapOzeti.pdf";//isbank
            //String sInFilePath  = "/Users/esabil/Documents/files/64050199824_20190829_16393162_HesapOzeti.pdf";//isbank
            //String sInFilePath  = "/Users/esabil/Documents/files/KUVEYT_Musterino_6667543_Ekno_1_2019910152032_ekstre.pdf";
            String sInFilePath  = "/Users/esabil/Documents/files/KUVEYT_Musterino_6667543_Ekno_3900_201991015217_ekstre.pdf";
            String sOutFilePath = "/Users/esabil/Documents/files/dekont_summary.txt";//output file
            
            ArrayList<DekontFields> flds = DekontMethods.processFile(sInFilePath, sOutFilePath, false);
            
            
            //System.out.println(rc);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
