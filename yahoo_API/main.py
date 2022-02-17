# Script che esegue il parsing del file JSON ricevuto in output dalle API di yahoo finance.
# Autore : Matteo Sepa
# Progetto di gruppo pcto classe 4j

import requests
import os
import json
from time import time
import time as t
from os.path import exists
import datetime

# funzione che scrive su file
def writeToFile(path, content):
    
    file = open(path, 'w')
    file.write(content)
    file.close()

# funzione che restituisce l'orario di sistema in millisecondi
def getTimeStamp():
    
    milliseconds = int(time() * 1000)
    print("Orario di sistema in millisecondi", milliseconds)
    return milliseconds

# funzione che rimuove la parte iniziale e finale del file json resituito in output dal API che causavano problemi nel parsing
def removeJson(a):
    
    replaceString1 = '{"quoteResponse":{"result":'
    replaceString2 = ',"error":null}}'
    a = a.replace(replaceString1, "")
    a = a.replace(replaceString2, "")
    return a

# funzione che restituisce l'orario di sistema come un intero
def getSystemTime(a):
    a = datetime.datetime.now()
    a = int(a.strftime("%H%M%S"))
    return a

# funzione che controlla l'esistenza del file csv, in caso di non esistenza intesta la prima riga con i titoli
def checkFile(a, b):
    
    a = exists(b)
    
    if a == False:
                                    
        file = open (b, 'a')
        file.write("timestamp")
        file.write(";")
        file.write("Valore Attuale")
        file.write(";")
        file.write("Percentuale")
        file.write(";")
        file.write("Apertura")
        file.write(";")
        file.write("Ultima Chiusura")
        file.write(";")
        file.write("Range")
        file.write(";")
        file.write("\n")
        file.close()



# funzione che controlla l'esistenza delle cartelle necessarie al funzionamento del programma
def checkFolder(a, b, path1, path2):

    path1 = 'csv'
    path2 = 'json'

    a = exists(path1)
    b = exists(path2)

    if a == False:
        os.mkdir(path1)
    
    if b == False:
        os.mkdir(path2)





def main():
    
    esci = False
    
    system_time = 0

    csvExists = False
    folder1Exists = False
    folder2Exists = False

    j = 0
    
    
    while esci != True:

        system_time = getSystemTime(system_time)
        #print (system_time)
        checkFolder(folder1Exists, folder2Exists, 'json', 'csv')
        
        # se l'orario di sistema è compreso tra le 15:30 e le 22:00 (apertura-chiusura borsa americana)
        if system_time >= 000000 and system_time <= 220000:
            
            i = 0
            titolo = ["TSLA", "AAPL", "AMZN", "SPY", "FB"]
            name = ["TSLA", "AAPL", "AMZN", "SPY", "FB"]
            
            
            url = "https://yh-finance.p.rapidapi.com/market/v2/get-quotes"

            

            headers = {
                'x-rapidapi-host': "yh-finance.p.rapidapi.com",
                'x-rapidapi-key': "2f564ef340msh63781686c607c0fp1a8e2djsn348e1330ba97"
                }

            
            while i < 5:
                
                if i == 0:
                    path = "json/TSLA.json"
            
                if i == 1:
                    path = "json/AAPL.json"

                if i == 2:
                    path = "json/AMZN.json"

                if i == 3:
                    path = "json/SPY.json"

                if i == 4:
                    path = "json/META.json"
                
                # utilizzo del API
                querystring = {"region":"US","symbols":titolo[i]}
                response = requests.request("GET", url, headers=headers, params=querystring)
                status_code = response.status_code
                if status_code == 200:

                    correctJson = removeJson(response.text)
                    writeToFile(path, correctJson)

                    with open(path, "r") as json_file:
                        
                        fileName = name[i]
                        lockExtension = ".lock"
                        csvExtension = ".csv"
                        lockFile = fileName + lockExtension
                        csvFileName = fileName + csvExtension
                        csvFolderPath = "csv"
                        csvPath = csvFolderPath + "/" + csvFileName
                        writeLockFile = "write"+fileName+lockExtension

                        # controllo dell'esistenza del file .lock
                        file_exists = exists(writeLockFile)
                        csvExists = checkFile(csvExists, csvPath)
                        

                        
                        # se il file .lock non esiste
                        if file_exists == False:
                            
                            print("Scrittura in corso....")
                            # creazione del file di lock per la lettura
                            with open(lockFile, "xt") as f:
                                f.close()
                            
                            data = json.load(json_file)

                            t.sleep(1)
                            

                            file = open (csvPath, 'a')
                            print("\n")
                            
                            

                            # calcolo e scrittura del timestamp
                            timestamp = getTimeStamp()
                            timestamp = str(timestamp)
                            file.write(timestamp)
                            file.write(";")
                            
                            print("---------------------------------------\n")
                        
                            # scrittura del prezzo di mercato
                            actualValue = data[0]['regularMarketPrice']
                            print("Valore attuale: ", actualValue)
                            actualValue = str(actualValue)
                            file.write(actualValue)
                            file.write(";")

                            # scrittura della percentuale
                            perc = data[0]['regularMarketChangePercent']
                            print("Percentuale dal apertura: ", perc, "%")
                            perc = str(perc)
                            file.write(perc)
                            file.write(";")
                                    
                            # scrittura del valore di apertura
                            openValue = data[0]['regularMarketOpen']
                            print("Valore di apertura: ", openValue)
                            openValue = str(openValue)
                            file.write(openValue)
                            file.write(";")
                                    
                            # scrittura della chiusura precedente
                            previousClose = data[0]['regularMarketPreviousClose']
                            print("Valore precedente di chiusura: ", previousClose)
                            previousClose = str(previousClose)
                            file.write(previousClose)
                            file.write(";")

                            # scrittura del range odierno
                            todayRange = data[0]['regularMarketDayRange']
                            print("Range odierno: ", todayRange, "\n")
                            todayRange = str(todayRange)
                            file.write(todayRange)
                            file.write(";")
                            file.write("\n")
                            
                            # chiusura del flusso
                            file.close()

                            print("---------------------------------------\n")
                    
                            # rimozione del file di lock per la lettura
                            os.remove(lockFile)
                            i = i + 1
                        else:
                            print("File lock presente!")
                            i = i + 1
                    
            t.sleep(600) 

        else:
            print("La borsa e' chiusa!")
            t.sleep(600)   

    

            
if __name__ == "__main__":
    main()
