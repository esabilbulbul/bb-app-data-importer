/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.app.dekonts;

import java.io.File;
import jaxesa.util.Util;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author esabil
 */
public class BbAppDekonts 
{

    /**
     * This app reads receipts (dekont) from the file and generates / output a excel decont file
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        try
        {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            String sFilePath = "/Users/esabil/Documents/files/Hesap_Hareket_Detay_64265549_TL.pdf";

            PDDocument document = null; 
            document = PDDocument.load(new File(sFilePath));
            document.getClass();
            String st = "";
            if( !document.isEncrypted() )
            {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition( true );
                PDFTextStripper Tstripper = new PDFTextStripper();
                st = Tstripper.getText(document);
                //System.out.println("Text:"+st);

            }
            
            String[] Lines = st.split("\\n");
            
            // REFORMATTING LINE
            //------------------------------------------------------------------
            // - Title Words (YKB)
            //   - Şube
            //   - Müşteri Numarası
            //   - Hesap Şubesi
            //   - Bitiş Tarihi
            //   - Müşteri Bilgileri
            //   - Müşteri Tipi
            //   - SBU
            //   - Ad-Soyad/Unvan
            //   - Hesap Numarası
            //   - Başlangıç Tarihi
            //   - IBAN Numarası
            //
            // - Data Start (Columns Starting)
            //   - İşlem Tutarı
            //   
            // - Data Ending (Footnote)
            //   - YAPI VE KREDİ BANKASI A.Ş
            //
            // - Content Filters (The records will be saved)
            //   - Contains PEŞİNSATIŞ (txn type PESIN)
            //   - Contains TAKSİTSATIŞ (TXN type TAKSIT)
            //   - Contains PEŞSTKOM (Txn type = KOMISYON)
            //   - Contains TAKST KOM (Txn type = KOMISYON)

            String sPathFormattedFile = "/Users/esabil/Documents/files/dekont_summary.txt";
            boolean bNoFilter = false;
            boolean bDataStarted = false;
            for (String lineN: Lines)
            {
                
                int index = lineN.indexOf( "YAPI VE KREDİ BANKASI A.Ş");
                if (index ==0)
                    bDataStarted = false;
                    
                if (bDataStarted==true) 
                {
                    boolean bRecordYes = false;
                    
                    //FILTER HERE
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    String sTxnType = "";
                    bRecordYes = lineN.contains("PEŞİNSATIŞ");
                    if (bRecordYes==true)
                    {
                        //PESIN SATIS
                        sTxnType = "PESIN";
                    }
                    else
                    {
                        bRecordYes = lineN.contains("TAKSİTSATIŞ");
                        if (bRecordYes==true)
                        {
                            sTxnType = "TAKSIT";
                        }
                        else
                        {
                            bRecordYes = lineN.contains("PEŞSTKOM");
                            if (bRecordYes==true)
                            {
                                sTxnType = "KOMISYON";
                            }
                            else
                            {
                                bRecordYes = lineN.contains("TAKST KOM");
                                if (bRecordYes==true)
                                {
                                    sTxnType = "KOMISYON";
                                    lineN = lineN.replaceAll("TAKST KOM","TAKSTKOM");
                                }
                            }
                            
                        }
                    }
                    
                    //REFORMAT HERE
                    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    if (bRecordYes==true)
                    {
                        //lineN = sTxnType + "\t" + lineN;
                        
                        String sColTxnType = sTxnType;
                        String sColDate = "";
                        String sColRelease = "";
                        String sColDesc = "";
                        String sColAmount = "";
                        String sColTime = "";
                        String sColBalance = "";
                        String sColTraceNo = "";
                        String sColMonthNo = "";
                        
                        //String sNewLine = lineN.replaceAll(" ", "\t");
                        String[] sCols  = lineN.split(" ");
                        
                        for (int i=0;i<sCols.length;i++)
                        {
                            String sColData = sCols[i];
                            
                            switch(i)
                            {
                                case 0:
                                    //Txn Date
                                    sColDate = sColData;
                                    
                                    String[] sDateParts = sColDate.split("\\.");
                                    
                                    sColMonthNo = sDateParts[1];
                                    
                                    break;
                                case 6:
                                    //Time Date
                                    sColTime = sColData;
                                    
                                    break;
                                case 1:
                                    //Desc
                                    sColDesc = sColData;
                                    
                                    break;
                                case 2:
                                    //Amount
                                    sColAmount = sColData;
                                    
                                    break;
                                case 3:
                                    //Balance + Release (No space between) (To be parsed)
                                    
                                    String sBalNRelease = sColData;
                                    
                                    int index1stDot = sBalNRelease.indexOf(".");
                                    
                                    sColBalance = sBalNRelease.substring(0, index1stDot + 2 + 1);
                                    sColRelease = sBalNRelease.substring(index1stDot + 2 + 1);
                                    
                                    break;
                                case 5:
                                    // Trance No
                                    sColTraceNo = sColData;
                                    
                                    break;
                            }
                        }

                        String sNewLine =   sColTxnType + "\t" + 
                                            sColDate + "\t" + 
                                            sColRelease + "\t" + 
                                            sColDesc + "\t" + 
                                            sColAmount + "\t" + 
                                            sColTime + "\t" + 
                                            sColBalance + "\t" + 
                                            sColTraceNo + "\t" + 
                                            sColMonthNo;

                        System.out.println(lineN);
                        System.out.println(sNewLine);

                        //if (bNoFilter==false)
                            Util.Files.Write2File(sPathFormattedFile, sNewLine);
                        //else
                            
                        
                    }
                }
                
                if (bNoFilter==true)
                    Util.Files.Write2File(sPathFormattedFile, lineN);//no filter - write full data

                index = lineN.indexOf( "İşlem Tutarı");
                if (index == 0)
                {
                    bDataStarted = true;
                }
                
            }
            
            String sEnd = "end";
            
            // FULL-TEXT READ
            /*
            PDDocument document = PDDocument.load(new File(sFilePath));
            if (!document.isEncrypted()) 
            {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                System.out.println("Text:" + text);
            }
            document.close();
            */
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}
