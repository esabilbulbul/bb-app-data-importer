/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.app.dekonts;

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
            String sInFilePath  = "/Users/esabil/Documents/files/64050199824_20190829_16393162_HesapOzeti.pdf";//isbank
            String sOutFilePath = "/Users/esabil/Documents/files/dekont_summary.txt";
            
            boolean rc = DekontMethods.processFile(sInFilePath, sOutFilePath);
            
            System.out.println(rc);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
