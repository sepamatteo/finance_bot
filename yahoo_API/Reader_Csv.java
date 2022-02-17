import java.util.Scanner;
import java.io.*;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class Reader_Csv{
    
    
    public void Tesla () 
    {
        try{
            Scanner scanner = new Scanner(new File("csv/TSLA.csv"));
            String parte;
            System.out.println("Tesla: ");
            
            String[] parts;
            String parte1=null, parte2=null,parte3=null,parte4=null,parte5=null,parte6=null;
            
            while (scanner.hasNextLine()) {
                
                parte = scanner.nextLine();
                parts =parte.split(";");
                parte1=parts[0];
                parte2=parts[1];
                parte3=parts[2];
                parte4=parts[3];
                parte5=parts[4];
                parte6=parts[5];
            }
            
            long data=0;
            try{
                data=Long.parseLong(parte1);
            }
            catch(NumberFormatException nfe){
                System.out.println("Conversione non riuscita!");
            }
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            Date result = new Date(data);
            System.out.println("Data: "+result);
            
            
    
            System.out.println("Valore attuale: "+parte2);
            System.out.println("Percentuale: "+parte3+"%");
            System.out.println("Apertura: "+parte4);
            System.out.println("Chiusura: "+parte5);
            System.out.println("Range: "+parte6);
            System.out.println("_________________________");
    
            
            
            scanner.close();
        } 
    
        catch(Exception e){
            System.out.println("Errore!");
        }
    }
    public void Apple () 
    {
        try{
            Scanner scanner = new Scanner(new File("csv/AAPL.csv"));
            String parte;
            System.out.println("Apple: ");
            
            String[] parts;
            String parte1=null, parte2=null,parte3=null,parte4=null,parte5=null,parte6=null;
            
            while (scanner.hasNextLine()) {
                
                parte = scanner.nextLine();
                parts =parte.split(";");
                parte1=parts[0];
                parte2=parts[1];
                parte3=parts[2];
                parte4=parts[3];
                parte5=parts[4];
                parte6=parts[5];
            }
            
            
            long data=0;
            try{
                data=Long.parseLong(parte1);
            }
            catch(NumberFormatException nfe){
                System.out.println("Conversione non riuscita!");
            }
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            Date result = new Date(data);
            System.out.println("Data: "+result);
            
    
            System.out.println("Valore attuale: "+parte2);
            System.out.println("Percentuale: "+parte3+"%");
            System.out.println("Apertura: "+parte4);
            System.out.println("Chiusura: "+parte5);
            System.out.println("Range: "+parte6);
            System.out.println("_________________________");
    
            
            
            scanner.close();
        } 
    
        catch(Exception e){
            System.out.println("Errore!");
        }
    }
    
    
    public void Amazon () 
    {
        try{
            Scanner scanner = new Scanner(new File("csv/AMZN.csv"));
            String parte;
            System.out.println("Amazon: ");
            
            String[] parts;
            String parte1=null, parte2=null,parte3=null,parte4=null,parte5=null,parte6=null;
            
            while (scanner.hasNextLine()) {
                
                parte = scanner.nextLine();
                parts =parte.split(";");
                parte1=parts[0];
                parte2=parts[1];
                parte3=parts[2];
                parte4=parts[3];
                parte5=parts[4];
                parte6=parts[5];
            }
            long data=0;
            try{
                data=Long.parseLong(parte1);
            }
            catch(NumberFormatException nfe){
                System.out.println("Conversione non riuscita!");
            }
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            Date result = new Date(data);
            System.out.println("Data: "+result);
            
            
            System.out.println("Valore attuale: "+parte2);
            System.out.println("Percentuale: "+parte3+"%");
            System.out.println("Apertura: "+parte4);
            System.out.println("Chiusura: "+parte5);
            System.out.println("Range: "+parte6);
            System.out.println("_________________________");
            
            
            scanner.close();
        }
    
        catch(Exception e){
                System.out.println("Errore!");
        }
    }
    public void Stati_Uniti () 
    {
        try{
            Scanner scanner = new Scanner(new File("csv/SPY.csv"));
            String parte;
            System.out.println("S&P 500: ");
    
            String[] parts;
            String parte1=null, parte2=null,parte3=null,parte4=null,parte5=null,parte6=null;
    
            while (scanner.hasNextLine()) {
    
                parte = scanner.nextLine();
                parts =parte.split(";");
                parte1=parts[0];
                parte2=parts[1];
                parte3=parts[2];
                parte4=parts[3];
                parte5=parts[4];
                parte6=parts[5];
            }
    
            long data=0;
            try{
                data=Long.parseLong(parte1);
            }
            catch(NumberFormatException nfe){
                System.out.println("Conversione non riuscita!");
            }
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            Date result = new Date(data);
            System.out.println("Data: "+result);
    
    
            System.out.println("Valore attuale: "+parte2);
            System.out.println("Percentuale: "+parte3+"%");
            System.out.println("Apertura: "+parte4);
            System.out.println("Chiusura: "+parte5);
            System.out.println("Range: "+parte6);
            System.out.println("_________________________");
    
    
            scanner.close();
        }
        catch(Exception e){
                System.out.println("Errore!");
        }
    }
    public void Meta () 
    {
            try{
                Scanner scanner = new Scanner(new File("csv/FB.csv"));
            String parte;
            System.out.println("Meta: ");
            
            String[] parts;
            String parte1=null, parte2=null,parte3=null,parte4=null,parte5=null,parte6=null;
            
            while (scanner.hasNextLine()) {
                
                parte = scanner.nextLine();
                parts =parte.split(";");
                parte1=parts[0];
                parte2=parts[1];
                parte3=parts[2];
                parte4=parts[3];
                parte5=parts[4];
                parte6=parts[5];
            }
        
            long data=0;
            try{
                data=Long.parseLong(parte1);
            }
            catch(NumberFormatException nfe){
                System.out.println("Conversione non riuscita!");
            }
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
            Date result = new Date(data);
            System.out.println("Data: "+result);
        
        
            System.out.println("Valore attuale: "+parte2);
            System.out.println("Percentuale: "+parte3+"%");
            System.out.println("Apertura: "+parte4);
            System.out.println("Chiusura: "+parte5);
            System.out.println("Range: "+parte6);
            System.out.println("_________________________");
        
        
            scanner.close();
        }
    
        catch(Exception e){
            System.out.println("Errore!");
        }
    }
};
