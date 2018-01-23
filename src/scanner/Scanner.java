package scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;


public class Scanner {

    public static void main(String[] args) {

        
File fout = new File("scanner_output.txt");
	BufferedWriter bw;
       String Token = "";
          
            int state = 1;
        try {
           RandomAccessFile raf = new RandomAccessFile("tiny_sample_code.txt","rw");
 FileOutputStream fos = new FileOutputStream(fout);
 
	bw = new BufferedWriter(new OutputStreamWriter(fos));

                //for(int j=0; j<raf.length();j++){
                int j=0;               
                   
           

               
while((state==1||state==2||state==3||state==4||state==5)&&j<raf.length())
{     
     raf.seek(j);
     char current =(char)raf.readByte();
    
    switch (state) {
                    case 1:

                        if (current == ' ') {
                            state = 1;
                            break;
                        } else if (current == '{') {
                            state = 2;
                            break;
                        } else if (Character.isAlphabetic(current)) {
                               Token += current;
                            state = 4;
                           
                           break;
                        } else if (Character.isDigit(current)) {

                            state = 3;
                            Token += current;
                            break;
                        } 
                        else if (current == ':') {

                            state = 5;
                            Token += current;
                            break;
                        } 
                        else if(!Character.isWhitespace(current))
                            {
                            state= 1;
                         
                           bw.write("Special Symbol\t"+current);
                               bw.newLine();
                           
                            break;
                            }
                        else 
                        {
                            state=1;
                            break;
                        }

                        
                        
                    case 2:
                        if (current == '}') {
                            state = 1;
                            break;
                        } else {
                            state = 2;
                            break;
                        }
                    case 3:
                       
                        if (Character.isDigit(current)) {
                            state = 3;
                            Token += current;
                            break;

                        } else {
                            j--;
                            state = 1;
                            bw.write("Number  \t"+Token+"\n");
                            bw.newLine();
               
                            Token="";
                            break;
                        }
                    case 4:
                        
                        if (Character.isAlphabetic(current)) {
                            state = 4;
                            Token += current;

                            break;
                        } 
                        else {
                            j--;
                            if("if".equals(Token)||"then".equals(Token)||"else".equals(Token)||"end".equals(Token)||"repeat".equals(Token)||"until".equals(Token)||"read".equals(Token)||"write".equals(Token))
                            { 
                                state=1;
                                 bw.write("Reserved word\t"+Token);
                                     bw.newLine();
                                Token="";
                                break;
                    } else {                           
                                state = 1;
                                
                                bw.write("Identifier\t"+Token);
                                bw.newLine();
                                
                                
                                Token="";
                                break;
                    }
                            
                            
                           
                        }

                    case 5:
                        if (current == '=') {
                            Token += current;
                            state = 1;
                            bw.write("Assign   \t"+Token);
                            bw.newLine();
                           
                            Token="";
                            break;

                        }
                        else {
                            j--;
                            state=1;
                            break;
                        }
                    default:
                        state=1;
                       break;
                        
                    
                      
                        

                }
    j++;
}
bw.close();
            }
        
        

        catch (IOException e) {
            e.printStackTrace();
        }
///////////////////////////////

    }}