// Autore: Alberto Iovino
// Licenza Pubblica Creative Commons Attribuzione-NonCommerciale-CondividiAlloStessoModo 4.0 Internazionale

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//classe per il bot
public class simpleBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // System.out.println(update.getMessage().getText());
        // System.out.println(update.getMessage().getFrom().getFirstName());

        String command = update.getMessage().getText();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        Date datecontroll = new Date();
        String orario = formatter.format(datecontroll).toString();
        long orarioc=0;
        try{
            orarioc=Long.parseLong(orario);
        }
        catch(NumberFormatException nfe){
            System.out.println("Conversione non riuscita!");
        }
        //String folder;
        //folder = System.getProperty("user.dir") + "/finance_bot/";

        if (command.equals("/start")) {
            String message = "Bot si running";
            SendMessage response = new SendMessage();
            response.setChatId(update.getMessage().getChatId().toString());
            response.setText(message);

            try {
                execute(response);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }

        }

        //comando per far vedere il listino di tutti i titoli
        if (command.equals("/list")) {
            String message = "List:" +
                    "\nStats Tesla (/tsla);" +
                    "\nStats Apple (/aapl);" +
                    "\nStats Amazon (/amzn);" +
                    "\nStats SPY (/spy);" +
                    "\nStats Meta (/meta);";
            SendMessage response1 = new SendMessage();
            response1.setChatId(update.getMessage().getChatId().toString());
            response1.setText(message);

            try {
                execute(response1);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
            //mostra tutti i titoli che si possono visualizare

        }

        //comando per vedere i titoli tesla
        if (command.equals("/tsla")&&orarioc>=153000&&orarioc<=220000)
        {
            LockFile lf = new LockFile("TSLA.lock", "writeTSLA.lock" );

            if(lf.f1.exists()==false)
            {
                lf.blockcreate();

                ReaderCsv lettore = new ReaderCsv();
                lettore.Tesla();

                long data = 0;

                try
                {
                    data=Long.parseLong(lettore.parte1);
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Conversione non riuscita!");
                }

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                Date result = new Date(data);

                String message0 = "Data: " + result;
                SendMessage response0 = new SendMessage();
                response0.setChatId(update.getMessage().getChatId().toString());
                response0.setText(message0);

                try {
                    execute(response0);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message = "Valore Attuale: " + lettore.parte2 +" $";
                SendMessage response2 = new SendMessage();
                response2.setChatId(update.getMessage().getChatId().toString());
                response2.setText(message);

                try {
                    execute(response2);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message1 = "Percentuale: " + lettore.parte3 + "%";
                SendMessage response3 = new SendMessage();
                response3.setChatId(update.getMessage().getChatId().toString());
                response3.setText(message1);

                try {
                    execute(response3);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message2 = "Apertura: " + lettore.parte4 +" $";
                SendMessage response4 = new SendMessage();
                response4.setChatId(update.getMessage().getChatId().toString());
                response4.setText(message2);

                try {
                    execute(response4);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message3 = "Chiusura: " + lettore.parte5 +" $";
                SendMessage response5 = new SendMessage();
                response5.setChatId(update.getMessage().getChatId().toString());
                response5.setText(message3);

                try {
                    execute(response5);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message4 = "Range: " + lettore.parte6 +" $";
                SendMessage response6 = new SendMessage();
                response6.setChatId(update.getMessage().getChatId().toString());
                response6.setText(message4);

                try {
                    execute(response6);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                lf.blockremove();
            }



        }
        else
        {
            String message00 = "La Borsa è attualmente chiusa. ";
            SendMessage response00 = new SendMessage();
            response00.setChatId(update.getMessage().getChatId().toString());
            response00.setText(message00);

            try {
                execute(response00);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/aapl")&&orarioc>=153000&&orarioc<=220000)
        {
            LockFile lf = new LockFile("AAPL.lock", "writeAAPL.lock" );

            if(lf.f1.exists()==false){

                lf.blockcreate();

                ReaderCsv lettore = new ReaderCsv();
                lettore.Apple();

                long data = 0;

                try
                {
                    data=Long.parseLong(lettore.parte1);
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Conversione non riuscita!");
                }

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                Date result = new Date(data);

                String message0 = "Data: " + result;
                SendMessage response0 = new SendMessage();
                response0.setChatId(update.getMessage().getChatId().toString());
                response0.setText(message0);

                try {
                    execute(response0);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message = "Valore Attuale: " + lettore.parte2 +" $";
                SendMessage response2 = new SendMessage();
                response2.setChatId(update.getMessage().getChatId().toString());
                response2.setText(message);

                try {
                    execute(response2);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message1 = "Percentuale: " + lettore.parte3 + "%";
                SendMessage response3 = new SendMessage();
                response3.setChatId(update.getMessage().getChatId().toString());
                response3.setText(message1);

                try {
                    execute(response3);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message2 = "Apertura: " + lettore.parte4 +" $";
                SendMessage response4 = new SendMessage();
                response4.setChatId(update.getMessage().getChatId().toString());
                response4.setText(message2);

                try {
                    execute(response4);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message3 = "Chiusura: " + lettore.parte5 +" $";
                SendMessage response5 = new SendMessage();
                response5.setChatId(update.getMessage().getChatId().toString());
                response5.setText(message3);

                try {
                    execute(response5);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message4 = "Range: " + lettore.parte6 +" $";
                SendMessage response6 = new SendMessage();
                response6.setChatId(update.getMessage().getChatId().toString());
                response6.setText(message4);

                try {
                    execute(response6);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                lf.blockremove();

            }


        }
        else
        {
            String message02 = "La Borsa è attualmente chiusa. ";
            SendMessage response02 = new SendMessage();
            response02.setChatId(update.getMessage().getChatId().toString());
            response02.setText(message02);

            try {
                execute(response02);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/amzn")&&orarioc>=153000&&orarioc<=220000)
        {
            LockFile lf = new LockFile("AMZN.lock", "writeAMZN.lock" );

            if(lf.f1.exists()==false){

                lf.blockcreate();

                ReaderCsv lettore = new ReaderCsv();
                lettore.Amazon();

                long data = 0;

                try
                {
                    data=Long.parseLong(lettore.parte1);
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Conversione non riuscita!");
                }

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                Date result = new Date(data);

                String message0 = "Data: " + result;
                SendMessage response0 = new SendMessage();
                response0.setChatId(update.getMessage().getChatId().toString());
                response0.setText(message0);

                try {
                    execute(response0);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message = "Valore Attuale: " + lettore.parte2 +" $";
                SendMessage response2 = new SendMessage();
                response2.setChatId(update.getMessage().getChatId().toString());
                response2.setText(message);

                try {
                    execute(response2);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message1 = "Percentuale: " + lettore.parte3 + "%";
                SendMessage response3 = new SendMessage();
                response3.setChatId(update.getMessage().getChatId().toString());
                response3.setText(message1);

                try {
                    execute(response3);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message2 = "Apertura: " + lettore.parte4 +" $";
                SendMessage response4 = new SendMessage();
                response4.setChatId(update.getMessage().getChatId().toString());
                response4.setText(message2);

                try {
                    execute(response4);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message3 = "Chiusura: " + lettore.parte5 +" $";
                SendMessage response5 = new SendMessage();
                response5.setChatId(update.getMessage().getChatId().toString());
                response5.setText(message3);

                try {
                    execute(response5);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message4 = "Range: " + lettore.parte6 +" $";
                SendMessage response6 = new SendMessage();
                response6.setChatId(update.getMessage().getChatId().toString());
                response6.setText(message4);

                try {
                    execute(response6);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                lf.blockremove();

            }

        }
        else
        {
            String message03 = "La Borsa è attualmente chiusa. ";
            SendMessage response03 = new SendMessage();
            response03.setChatId(update.getMessage().getChatId().toString());
            response03.setText(message03);

            try {
                execute(response03);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/spy")&&orarioc>=153000&&orarioc<=220000)
        {
            LockFile lf = new LockFile("SPY.lock", "writeSPY.lock" );

            if(lf.f1.exists()==false){

                lf.blockcreate();

                ReaderCsv lettore = new ReaderCsv();
                lettore.Stati_Uniti();

                long data = 0;

                try
                {
                    data=Long.parseLong(lettore.parte1);
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Conversione non riuscita!");
                }

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                Date result = new Date(data);

                String message0 = "Data: " + result;
                SendMessage response0 = new SendMessage();
                response0.setChatId(update.getMessage().getChatId().toString());
                response0.setText(message0);

                try {
                    execute(response0);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message = "Valore Attuale: " + lettore.parte2 +" $";
                SendMessage response2 = new SendMessage();
                response2.setChatId(update.getMessage().getChatId().toString());
                response2.setText(message);

                try {
                    execute(response2);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message1 = "Percentuale: " + lettore.parte3 + "%";
                SendMessage response3 = new SendMessage();
                response3.setChatId(update.getMessage().getChatId().toString());
                response3.setText(message1);

                try {
                    execute(response3);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message2 = "Apertura: " + lettore.parte4 +" $";
                SendMessage response4 = new SendMessage();
                response4.setChatId(update.getMessage().getChatId().toString());
                response4.setText(message2);

                try {
                    execute(response4);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message3 = "Chiusura: " + lettore.parte5 +" $";
                SendMessage response5 = new SendMessage();
                response5.setChatId(update.getMessage().getChatId().toString());
                response5.setText(message3);

                try {
                    execute(response5);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message4 = "Range: " + lettore.parte6 +" $";
                SendMessage response6 = new SendMessage();
                response6.setChatId(update.getMessage().getChatId().toString());
                response6.setText(message4);

                try {
                    execute(response6);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                lf.blockremove();

            }


        }
        else
        {
            String message04 = "La Borsa è attualmente chiusa. ";
            SendMessage response04 = new SendMessage();
            response04.setChatId(update.getMessage().getChatId().toString());
            response04.setText(message04);

            try {
                execute(response04);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }

        if (command.equals("/meta")&&orarioc>=153000&&orarioc<=220000)
        {
            LockFile lf = new LockFile("META.lock", "writeMETA.lock" );

            if(lf.f1.exists()==false){

                lf.blockcreate();

                ReaderCsv lettore = new ReaderCsv();
                lettore.Meta();

                long data = 0;

                try
                {
                    data=Long.parseLong(lettore.parte1);
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Conversione non riuscita!");
                }

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                Date result = new Date(data);

                String message0 = "Data: " + result;
                SendMessage response0 = new SendMessage();
                response0.setChatId(update.getMessage().getChatId().toString());
                response0.setText(message0);

                try {
                    execute(response0);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message = "Valore Attuale: " + lettore.parte2 +" $";
                SendMessage response2 = new SendMessage();
                response2.setChatId(update.getMessage().getChatId().toString());
                response2.setText(message);

                try {
                    execute(response2);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message1 = "Percentuale: " + lettore.parte3 + "%";
                SendMessage response3 = new SendMessage();
                response3.setChatId(update.getMessage().getChatId().toString());
                response3.setText(message1);

                try {
                    execute(response3);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message2 = "Apertura: " + lettore.parte4 +" $";
                SendMessage response4 = new SendMessage();
                response4.setChatId(update.getMessage().getChatId().toString());
                response4.setText(message2);

                try {
                    execute(response4);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message3 = "Chiusura: " + lettore.parte5 +" $";
                SendMessage response5 = new SendMessage();
                response5.setChatId(update.getMessage().getChatId().toString());
                response5.setText(message3);

                try {
                    execute(response5);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                String message4 = "Range: " + lettore.parte6 +" $";
                SendMessage response6 = new SendMessage();
                response6.setChatId(update.getMessage().getChatId().toString());
                response6.setText(message4);

                try {
                    execute(response6);
                } catch (TelegramApiException E) {
                    E.printStackTrace();
                }

                lf.blockremove();
            }


        }
        else
        {
            String message05 = "La Borsa è attualmente chiusa. ";
            SendMessage response05 = new SendMessage();
            response05.setChatId(update.getMessage().getChatId().toString());
            response05.setText(message05);

            try {
                execute(response05);
            } catch (TelegramApiException E) {
                E.printStackTrace();
            }
        }
    }



        @Override
        public String getBotUsername () {
            // TODO
            return "galileiPCTO_bot";
        }

        @Override
        public String getBotToken () {
            // TODO
            return "5293345120:AAGLlrUdauQMWPIBPMSrPS3Y0LV5QeF2g8Q";
        }
    }
