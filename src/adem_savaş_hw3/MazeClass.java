/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adem_savaş_hw3;
import java.io.*;

import java.util.LinkedList;

import java.util.Scanner;

/**
 *
 * @author Adem
 */
public class MazeClass implements MazeInterface {
    public int array[][];
    int satır,sutun;
   
   LinkedList<Integer> object = new LinkedList<Integer>();/*Storing positions*/

    @Override
    public void InputMaze(String FileName) {
        int size []=new int[2];
        String[] deneme = null;
        FileInputStream fStream = null;
        try {
            String str;
            fStream = new FileInputStream(FileName);
            DataInputStream dStream = new DataInputStream(fStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
            str=bReader.readLine();
            deneme=str.split(" ");
            for(int i=0;i<2;i++){
                size[i]=Integer.parseInt(deneme[i]);
            }
            array=new int[size[0]+2][size[1]+2];/*dimensions determined*/
            satır=size[0]+2;
            sutun=size[1]+2;
            
            for(int i=1;i<size[0]+1;i++){
                str=bReader.readLine();
                deneme=str.split(" ");
                for(int j=1;j<size[1]+1;j++)  {
                array[i][j]= deneme[j-1].hashCode();/*String values hashcode 
                                                         to array*/ 
                }  
            }
            for(int i=0;i<sutun;i++){
            array[0][i]="A".hashCode(); 
            array[size[0]+1][i]="A".hashCode();/*surrounded by "A"*/
            }
            for(int i=0;i<satır;i++){
            array[i][0]="A".hashCode();
            array[i][size[1]+1]="A".hashCode();
            }
            
        } 
        
        catch (FileNotFoundException ex) {
            System.out.println(" İşlem yapılırken adress bulunamadı :" + ex.getMessage());
            
        } catch (IOException ex) {
            throw new UnsupportedOperationException("Not supported yet.");
           
        } 
        catch (NullPointerException ex) {
            System.out.println(" Dosya boş :" + ex.getMessage());
            
           
        } 
    }

    @Override
    public void FindLoops() {
        int  array1[][]=new int[satır][sutun];/*For cover array*/
        int loopcount=0;/*how many loops in maze*/
        int kontrolsatır,kontrolsutun;/*Control the other option*/
        for(int sayac=0;sayac<2;sayac++){/*write loopcount then write all loops*/
        for(int i=1;i<satır-1;i++){
            for(int j=1;j<sutun-1;j++){
                for(int m=0;m<satır;m++){/*array's elements to array1*/
                for(int n=0;n<sutun;n++){
                array1[m][n]=array[m][n];   
                }    
                }    
            kontrolsatır=0;
            kontrolsutun=0;
            int x=i;
            int y=j;
            object.add(x);/*First position to stored linkedlist*/
            object.add(y);
            while(1==1){   
                if(array1[x][y]+1==array1[x][y+1]){
                    if((array1[x][y]+1==array1[x][y-1])||(array1[x][y]+1==array1
                                   [x+1][y])||(array1[x][y]+1==array1[x-1][y])){
                        kontrolsatır=x;
                        kontrolsutun=y+1;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||
                                (x==i&y+1==j)||(x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x;
                        kontrolsutun=y+1;
                    }
                    y=y+1;
                    object.add(x);
                    object.add(y);
                }
                else if(array1[x][y]+1==array1[x][y-1]){
                    if((array1[x][y]+1==array1[x][y+1])||(array1[x][y]+1==array1
                                   [x+1][y])||(array1[x][y]+1==array1[x-1][y])){
                        kontrolsatır=x;
                        kontrolsutun=y-1;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||
                                               (x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x;
                        kontrolsutun=y-1;
                    }
                    y=y-1; 
                    object.add(x);
                    object.add(y);
        
                }
                else if(array1[x][y]+1==array1[x+1][y]){
                    if((array1[x][y]+1==array1[x][y+1])||(array1[x][y]+1==array1
                                   [x][y-1])||(array1[x][y]+1==array1[x-1][y])){
                        kontrolsatır=x+1;
                        kontrolsutun=y;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||
                                               (x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x+1;
                        kontrolsutun=y;
                    }
                    x=x+1;
                    object.add(x);
                    object.add(y);
                }
                else if(array1[x][y]+1==array1[x-1][y]){
                    if((array1[x][y]+1==array1[x][y+1])||(array1[x][y]+1==array1
                                   [x][y-1])||(array1[x][y]+1==array1[x+1][y])){
                        kontrolsatır=x-1;
                        kontrolsutun=y;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||
                                               (x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x-1;
                        kontrolsutun=y;
                    }
                    x=x-1; 
                    object.add(x);
                    object.add(y);
                }
                else if((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||(x==i&y-1==j)){
                    if(object.size()>4){
                        loopcount=loopcount+1;
                        if(sayac==1){
                            System.out.print("Loop "+loopcount+": ");
                            for(int m=0;m<object.size()-3;m=m+2)  
                            System.out.print(object.get(m)+","+object.get(m+1)+
                                                                           "-");
                            System.out.print(object.get(object.size()-2)+","+
                                                   object.get(object.size()-1));
                            System.out.println("");
                        }
                        if(kontrolsatır==0)
                        break;
                        else  {
                            array1[kontrolsatır][kontrolsutun]=65;
                            x=i;
                            y=j;
                            kontrolsatır=0;
                            kontrolsutun=0;
                            for(int l=object.size()-1;l>=2;l--){
                            object.remove(l);
                            }
                        }
                    }
                    else 
                        break;
                }
                else{
                    if(kontrolsatır==0)
                        break;
                    else{
                        array1[x][y]=65;
                        x=i;
                        y=j;
                        kontrolsatır=0;
                        kontrolsutun=0;
                        for(int l=object.size()-1;l>=2;l--){
                            object.remove(l);
                        }
                    }
                }
            }
            for(int l=object.size()-1;l>=0;l--){
                object.remove(l);
            }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    if(sayac==0){
         System.out.println("This program has been written by : Adem SAVAŞ");
        String []sayı1= {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine","ten","eleven","twelwe","thirteen", "fourteen", 
                               "fifteen", "sixteen",  
                               "seventeen", "eighteen", "nineteen"};
        String []sayı2= {"","","twenty","thirty","fourty","fifty","sixty","seventy","eighty","ninety"};
        if(loopcount<=20)
        System.out.println("The maze has "+sayı1[loopcount]+" loops");
        if(loopcount>20){
            int birler=loopcount%10;
            int onlar=loopcount/10;
            System.out.println("The maze has "+sayı2[onlar]+sayı1[birler]+" loops");
        }
        loopcount=0;
    }
        }
    }

    @Override
    public void FindLoops(String FileName) {/*override methods*/
        FileWriter yazici;
        BufferedWriter yaz;
        try {
            yazici = new FileWriter(FileName);
            yaz = new BufferedWriter(yazici);
            int  array1[][]=new int[satır][sutun];
        int loopcount=0;
        int kontrolsatır,kontrolsutun;
        for(int sayac=0;sayac<2;sayac++){
        for(int i=1;i<satır-1;i++){
            for(int j=1;j<sutun-1;j++){
                for(int m=0;m<satır;m++){
                for(int n=0;n<sutun;n++){
                array1[m][n]=array[m][n];   
                }    
                }    
            kontrolsatır=0;
            kontrolsutun=0;
            int x=i;
            int y=j;
            object.add(x);
            object.add(y);
            while(1==1){   
                if(array1[x][y]+1==array1[x][y+1]){
                    if((array1[x][y]+1==array1[x][y-1])||(array1[x][y]+1==array1
                                   [x+1][y])||(array1[x][y]+1==array1[x-1][y])){
                        kontrolsatır=x;
                        kontrolsutun=y+1;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||
                                (x==i&y+1==j)||(x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x;
                        kontrolsutun=y+1;
                    }
                    y=y+1;
                    object.add(x);
                    object.add(y);
                }
                else if(array1[x][y]+1==array1[x][y-1]){
                    if((array1[x][y]+1==array1[x][y+1])||(array1[x][y]+1==array1
                                   [x+1][y])||(array1[x][y]+1==array1[x-1][y])){
                        kontrolsatır=x;
                        kontrolsutun=y-1;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||
                                               (x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x;
                        kontrolsutun=y-1;
                    }
                    y=y-1; 
                    object.add(x);
                    object.add(y);
        
                }
                else if(array1[x][y]+1==array1[x+1][y]){
                    if((array1[x][y]+1==array1[x][y+1])||(array1[x][y]+1==array1
                                   [x][y-1])||(array1[x][y]+1==array1[x-1][y])){
                        kontrolsatır=x+1;
                        kontrolsutun=y;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||
                                               (x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x+1;
                        kontrolsutun=y;
                    }
                    x=x+1;
                    object.add(x);
                    object.add(y);
                }
                else if(array1[x][y]+1==array1[x-1][y]){
                    if((array1[x][y]+1==array1[x][y+1])||(array1[x][y]+1==array1
                                   [x][y-1])||(array1[x][y]+1==array1[x+1][y])){
                        kontrolsatır=x-1;
                        kontrolsutun=y;
                    }
                    else if(((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||
                                               (x==i&y-1==j))&&object.size()>4){
                        kontrolsatır=x-1;
                        kontrolsutun=y;
                    }
                    x=x-1; 
                    object.add(x);
                    object.add(y);
                }
                else if((x-1==i&y==j)||(x+1==i&y==j)||(x==i&y+1==j)||(x==i&y-1==j)){
                    if(object.size()>4){
                        loopcount=loopcount+1;
                        if(sayac==1){
                            yaz.write("Loop "+loopcount+": ");
                            
                            for(int m=0;m<object.size()-3;m=m+2)  
                            yaz.write(object.get(m)+","+object.get(m+1)+
                                                                           "-");
                            yaz.write(object.get(object.size()-2)+","+
                                                   object.get(object.size()-1));
                            yaz.write("");
                            yaz.newLine();
                        }
                        if(kontrolsatır==0)
                        break;
                        else  {
                            array1[kontrolsatır][kontrolsutun]=65;
                            x=i;
                            y=j;
                            kontrolsatır=0;
                            kontrolsutun=0;
                            for(int l=object.size()-1;l>=2;l--){
                            object.remove(l);
                            }
                        }
                    }
                    else 
                        break;
                }
                else{
                    if(kontrolsatır==0)
                        break;
                    else{
                        array1[x][y]=65;
                        x=i;
                        y=j;
                        kontrolsatır=0;
                        kontrolsutun=0;
                        for(int l=object.size()-1;l>=2;l--){
                            object.remove(l);
                        }
                    }
                }
            }
            for(int l=object.size()-1;l>=0;l--){
                object.remove(l);
            }
        
        }
    }
    if(sayac==0){
        String []sayı1= {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine","ten","eleven","twelwe","thirteen", "fourteen", 
                               "fifteen", "sixteen",  
                               "seventeen", "eighteen", "nineteen"};
        String []sayı2= {"","","twenty","thirty","fourty","fifty","sixty","seventy","eighty","ninety"};
        yaz.write("This program has been written by : Adem SAVAŞ");
        yaz.newLine();
        if(loopcount<=20)
        yaz.write("The maze has "+sayı1[loopcount]+" loops");
        if(loopcount>20){
            int birler=loopcount%=10;
            int onlar=loopcount/10;
            yaz.write("The maze has "+sayı2[onlar]+sayı1[birler]+" loops");
        }
        yaz.newLine();
        loopcount=0;
    }
        }
        yaz.close();
        } catch (IOException ex) {
            System.out.println(" İşlem yapılırken adress bulunamadı :" + ex.getMessage());
            ;
        }
        
        
    }
    
}
