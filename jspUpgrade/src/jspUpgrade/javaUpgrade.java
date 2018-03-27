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

public class javaUpgrade {

	  public javaUpgrade()
	  {

	  }
	  
	  public static void main(String[] args)
	  {
	    JspUpgrade runner = new JspUpgrade();
	    String foldertoUpdate = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\WEB-INF\\src\\com\\cargomanager\\cms\\acc\\ar\\event\\";
	    String outFilename = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\WEB-INF\\src\\com\\cargomanager\\cms\\acc\\ar\\event\\temp.jsp";
	    
	    try
	      {
	    	
	    	
	    	
//PASS 1
	      //String foldertoUpdate = "c:\\Program Files (x86)\\Apache Tomcat 4.0\\webapps\\cmsLPK\\fmm\\";
	      
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
	            String stringToInsert = "Connection con = (Connection)session.getAttribute(Param.DATABASE_CONNECTION);\n";
	            String stringToInsert1 = "import java.sql.Connection;";
	            String stringToInsert2 = "import com.cargomanager.cms.util.Param;";
	            		
	            
	            try {
	            	
	              //PASS 1	
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
	                    temptoken = tokenizer.nextToken();
	                    counter = temptoken.indexOf(stringToInsert1);
	                    if(counter>=0){
	                    	alreadyWritten = true;
	                    }
	                }

	                strFileContents = new String(contents, 0, bytesRead);
	                tokenizer = new StringTokenizer(strFileContents, "\n", true);
	                while (tokenizer.hasMoreTokens()) {
	                	
	                  lastPartCounter = 0;
	                  temptoken = tokenizer.nextToken();
	                  skipOneLine = false;
	                  
	                  if (((temptoken.indexOf("import java") >= 0) || (temptoken.indexOf("import java") >= 0))) { 
	  	                    if(!alreadyWritten) {
		  	                    fileOut.write(stringToInsert1.getBytes());
		  	                    fileOut.write("\n".getBytes());
		  	                    instances++;
		  	                    alreadyWritten = true;
		  	                    skipOneLine = true;
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
	      
	    
//PASS 2	      
	      //PASS 2
	      folder = new File(foldertoUpdate);
	      listOfFiles = folder.listFiles();
	      lFilename = null;
	      lastPartCounter = 0;
	      temptoken = null;
	      instances = 0;      
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
	            String stringToInsert = "Connection con = (Connection)session.getAttribute(Param.DATABASE_CONNECTION);\n";
	            String stringToInsert1 = "import java.sql.Connection;";
	            String stringToInsert2 = "import com.cargomanager.cms.util.Param;";
	            		
	            
	            try {
	            	
	              //PASS 2	
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
	                    temptoken = tokenizer.nextToken();
	                    counter = temptoken.indexOf(stringToInsert2);
	                    if(counter>=0){
	                    	alreadyWritten = true;
	                    }
	                }

	                strFileContents = new String(contents, 0, bytesRead);
	                tokenizer = new StringTokenizer(strFileContents, "\n", true);
	                while (tokenizer.hasMoreTokens()) {
	                	
	                  lastPartCounter = 0;
	                  temptoken = tokenizer.nextToken();
	                  skipOneLine = false;
	                  
	                  if (((temptoken.indexOf("import com") >= 0) || (temptoken.indexOf("import com") >= 0))) { 
	  	                    if(!alreadyWritten) {
		  	                    fileOut.write(stringToInsert2.getBytes());
		  	                    fileOut.write("\n".getBytes());
		  	                    instances++;
		  	                    alreadyWritten = true;
		  	                    skipOneLine = true;
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
	      

//PASS 3	      
	      //PASS 3
	      folder = new File(foldertoUpdate);
	      listOfFiles = folder.listFiles();
	      lFilename = null;
	      lastPartCounter = 0;
	      temptoken = null;
	      instances = 0;      
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
	            String stringToInsert = "Connection con = (Connection)session.getAttribute(Param.DATABASE_CONNECTION);";
	            		
	            
	            try {
	            	
	              //PASS 2	
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
	                    temptoken = tokenizer.nextToken();
	                    counter = temptoken.indexOf(stringToInsert);
	                    if(counter>=0){
	                    	alreadyWritten = true;
	                    }
	                }

	                strFileContents = new String(contents, 0, bytesRead);
	                tokenizer = new StringTokenizer(strFileContents, "\n", true);
	                while (tokenizer.hasMoreTokens()) {
	                	
	                  lastPartCounter = 0;
	                  temptoken = tokenizer.nextToken();
	                  skipOneLine = false;
	                  
	                  if (((temptoken.indexOf("HttpSession session = request.getSession(true);") >= 0) || (temptoken.indexOf("HttpSession session = request.getSession(true);") >= 0))) { 
	  	                    if(!alreadyWritten) {
	  	                    	fileOut.write(temptoken.getBytes());
		  	                    fileOut.write("\n".getBytes());
		  	                    fileOut.write(stringToInsert.getBytes());
		  	                    fileOut.write("\n".getBytes());
		  	                    instances++;
		  	                    alreadyWritten = true;
		  	                    skipOneLine = true;
	  	                    }    
  	                  }
	                  if(!skipOneLine) {
	                	  fileOut.write(temptoken.getBytes());
	                  }	  
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
