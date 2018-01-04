package jspUpgrade;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class JspUpgrade
{
  public JspUpgrade()
  {

  }
  
  public static void main(String[] args)
  {
    JspUpgrade runner = new JspUpgrade();
    try
    {
      String foldertoUpdate = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\air\\";
      File folder = new File(foldertoUpdate);
      File[] listOfFiles = folder.listFiles();
      String lFilename = null;
      String firstPart = "";
      String lastPart = "";
      String thisPart = "";
      int lastPartCounter = 0;
      String temptoken = null;
      int instances = 0;      
      for (int i = 0; i < listOfFiles.length; i++) {
        if (listOfFiles[i].isFile()) {
          lFilename = listOfFiles[i].getName();
          if (lFilename.indexOf(".jsp") >= 0) {
            System.out.println(lFilename);
            
            String inFilename = foldertoUpdate + lFilename;
            String outFilename = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\air\\temp.jsp";
            File file = new File(inFilename);
            File fileO = new File(outFilename);
            FileOutputStream fileOut = new FileOutputStream(outFilename);
            FileInputStream fis = null;
            BufferedInputStream bin = null;
            String strFileContents = "";
            boolean alreadyWritten = false;
            int counter = 1;
            int lineCounter = 1;

            String stringToInsert = "<%@page import=\"com.cargomanager.cms.util.StringUtils\"%>\n";
            
            try {
              fis = new FileInputStream(file);
              
              bin = new BufferedInputStream(fis);
              byte[] contents = new byte[5000000];
              int bytesRead = 0;
              StringBuffer currentline = new StringBuffer();
              StringTokenizer tokenizer = null;
              while ((bytesRead = bin.read(contents)) != -1) {
                strFileContents = new String(contents, 0, bytesRead);
                tokenizer = new StringTokenizer(strFileContents, "\n", true);
                
                while (tokenizer.hasMoreTokens()) {
                    temptoken = tokenizer.nextToken();
                    counter = temptoken.indexOf("com.cargomanager.cms.util.StringUtils");
                    if(counter>=0){
                    	alreadyWritten = true;
                    }
                }

                strFileContents = new String(contents, 0, bytesRead);
                tokenizer = new StringTokenizer(strFileContents, "\n", true);
                while (tokenizer.hasMoreTokens()) {
                	
                  firstPart = "";
                  lastPart = "";
                  thisPart = "";
                  lastPartCounter = 0;
                  temptoken = tokenizer.nextToken();
                  counter = temptoken.indexOf("<jsp:getProperty");
                  temptoken.indexOf("<jsp:getProperty");
                  
                  if (((temptoken.indexOf("<%@ page") >= 0) || (temptoken.indexOf("<%@page") >= 0)) && 
                    (!alreadyWritten)) {
                    fileOut.write(stringToInsert.getBytes());
                    alreadyWritten = true;
                  }
                  lineCounter++;
                  
                  if(counter>=0){
                	  firstPart = temptoken.substring(0, counter);
                	  int counter1 = (temptoken.substring(counter)).indexOf("/>");
                	  thisPart = temptoken.substring(counter, counter+counter1+2);
                	  lastPart = temptoken.substring(counter1+counter+2);
                	  fixThisPart(thisPart);
                	  temptoken=(firstPart+fixThisPart(thisPart)+lastPart);
                	  instances++;
                  }
                  
                  fileOut.write(temptoken.getBytes());
                  
                }
                counter++;
              }
              bin.close();
              fileOut.close();
              Files.copy(fileO.toPath(), file.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
              Files.delete(fileO.toPath());
              System.out.println(instances+" found");
              
              //break;
            } catch (IOException e) {
              e.printStackTrace();
              try
              {
                fis.close();
                bin.close();
              } catch (IOException ex) {
                ex.printStackTrace();
              }
            }
            finally
            {
              try
              {
                fis.close();
                bin.close();
              } catch (IOException ex) {
                ex.printStackTrace();
              }
            }
            try
            {
              fis.close();
              bin.close();
            } catch (IOException ex) {
              ex.printStackTrace();
            }
          }
        }
        else {
          listOfFiles[i].isDirectory();
        }
        
      }
      System.out.println("Done...");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private static String fixThisPart(String thisPart) {
	  String returnString = "";
	  String autoString = "<%=StringUtils.returnBlank(";
	  String tempString = "";
	  String name = "";
	  String singleChar = "";
	  String property = "";
	  int counter = 0;
	  tempString = thisPart.substring(23);
	  for(int x=0; x<tempString.length(); x++) {
		  singleChar = tempString.substring(x, x+1);

		  if(singleChar.equals("\"")){
			  break;
		  }
		  name=name+singleChar;
	  }
	  counter = tempString.indexOf("property=");
	  if(counter>=0) {
		  tempString = tempString.substring(counter+10);
		  for(int x=0; x<tempString.length(); x++) {
			  if(x==0){
				  singleChar = tempString.substring(x, x+1).toUpperCase();
			  } else {
				  singleChar = tempString.substring(x, x+1);
			  }	  

			  if(singleChar.equals("\"")){
				  break;
			  }
			  property=property+singleChar;
		  }
	  }
	  autoString = autoString+name+".get"+property+"())%>";
	  
	  return autoString;
  }
}
