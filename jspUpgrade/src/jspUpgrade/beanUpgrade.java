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

public class beanUpgrade {

	  {

	  }
	  
	  public static void main(String[] args)
	  {

	    String foldertoUpdate = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\WEB-INF\\src\\com\\cargomanager\\cms\\ewh\\tps\\";
	    String outFilename = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\WEB-INF\\src\\com\\cargomanager\\cms\\ewh\\tps\\temp.jsp";
	    boolean finallyCatch = false;
	    try
	      {
	    	
	    	
	    	
//PASS 1
	      //String foldertoUpdate = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\fmm\\";
	      
	      File folder = new File(foldertoUpdate);
	      File[] listOfFiles = folder.listFiles();
	      String lFilename = null;
	      folder = new File(foldertoUpdate);
	      listOfFiles = folder.listFiles();
	      lFilename = null;
	      String temptoken = null;
	      int instances = 0;      
	      for (int i = 0; i < listOfFiles.length; i++) {
	        if (listOfFiles[i].isFile()) {
	          lFilename = listOfFiles[i].getName();
	          if (lFilename.indexOf(".java") >= 0) {
	            System.out.println(lFilename);
	            
	            String inFilename = foldertoUpdate + lFilename;
	            //String outFilename = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\fmm\\temp.jsp";
	            File file = new File(inFilename);
	            File fileO = new File(outFilename);
	            FileOutputStream fileOut = new FileOutputStream(outFilename);
	            FileInputStream fis = null;
	            BufferedInputStream bin = null;
	            String strFileContents = "";
	            boolean alreadyWritten = false;
	            int counter = 1;
	            int lineCounter = 1;
	            boolean skipOneLine = true;
	            String stringToInsert = "conClose && con != null";
	            String stringToSearch = "con != null";
	            		
	            
	            try {
	            	
	              //PASS 4	
	              fis = new FileInputStream(file);
	              
	              bin = new BufferedInputStream(fis);
	              byte[] contents = new byte[5000000];
	              int bytesRead = 0;
	              StringBuffer currentline = new StringBuffer();
	              StringTokenizer tokenizer = null;

	              // pass 1
	              while ((bytesRead = bin.read(contents)) != -1) {
	                strFileContents = new String(contents, 0, bytesRead);
	                tokenizer = new StringTokenizer(strFileContents, "\n", true);
	                while (tokenizer.hasMoreTokens()) {
	                  int lastPartCounter = 0;
	                  temptoken = tokenizer.nextToken();
	                  
	                  if(temptoken.indexOf("catch")>=0) {
	                	  finallyCatch = false;
	                  } else if(temptoken.indexOf("finally")>=0) {
	                	  finallyCatch = true;  
	                  }
	                  skipOneLine = false;
	                  alreadyWritten = false;
	                  counter = temptoken.indexOf(stringToSearch);
	                  if ( counter>= 0) {
	                	  if(temptoken.indexOf("conClose")<0) {
	                		  if(finallyCatch) {
			                	  temptoken = temptoken.substring(0, counter)+stringToInsert+temptoken.substring(counter+11, temptoken.length());
			                	  instances++;
			                	  System.out.println("Changed..."+instances);
	                		  }	  
	                	  }
	                	  
	                  }
	                  fileOut.write(temptoken.getBytes());
	                  lineCounter++;
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

}
