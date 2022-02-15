# Script che esegue il parsing del file JSON ricevuto in output dalle API di yahoo finance.
# Autore : Matteo Sepa
# Progetto di gruppo pcto classe 4j

import requests
import os
import json
from time import time

# funzione per scrivere su file
def writeToFile(path, content):
    
    file = open(path, 'w')
    file.write(content)
    file.close()

# funzione che restituisce l'orario di sistema in millisecondi
def getSysTime():
    
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

# funzione che stampa a schermo le scelte disponibili all'utente
def menu():
    
    print("---------------------------------------\n")
    print("1. TSLA\n")
    print("2. AAPL \n")
    print("3. AMZN \n")
    print("4. S&P 500 \n")
    print("5. META \n")
    print("---------------------------------------\n")

def main():
    
    choice = 0
    menu()
    choice = input()
    choice = int(choice)

    if choice == 1:
        titolo = "TSLA"
        path = "Json/TSLA.json"
    
    if choice == 2:
        titolo = "AAPL"
        path = "Json/AAPL.json"

    if choice == 3:
        titolo = "AMZN"
        path = "Json/AMZN.json"

    if choice == 4:
        titolo = "SPY"
        path = "Json/SPY.json"

    if choice == 5:
        titolo = "FB"
        path = "Json/META.json"

    
        

    # comunicazione tramite l'API
    url = "https://yh-finance.p.rapidapi.com/market/v2/get-quotes"

    querystring = {"region":"US","symbols":titolo}

    headers = {
        'x-rapidapi-host': "yh-finance.p.rapidapi.com",
        'x-rapidapi-key': "2f564ef340msh63781686c607c0fp1a8e2djsn348e1330ba97"
        }

    response = requests.request("GET", url, headers=headers, params=querystring)

    status_code = response.status_code

    # se non ci sono errori nella richiesta http
    if status_code == 200:

        correctJson = removeJson(response.text)

        #if choice == 1:
        writeToFile(path, correctJson)

        with open(path, "r") as json_file:
            data = json.load(json_file)

        print("\n")
        timestamp = getSysTime()
        
        print("---------------------------------------\n")
        
        actualValue = data[0]['regularMarketPrice']
        print("Valore attuale: ", actualValue)
        
        openValue = data[0]['regularMarketOpen']
        print("Valore di apertura: ", openValue)
        
        previousClose = data[0]['regularMarketPreviousClose']
        print("Valore precedente di chiusura: ", previousClose)

        todayRange = data[0]['regularMarketDayRange']
        print("Range odierno: ", todayRange, "\n")

        print("---------------------------------------\n")
        

    
if __name__ == "__main__":
    main()
