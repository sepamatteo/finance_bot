# Programma che esegue il parsing di una pagina http e ne scrive il contenuto sia completo che a linea singola su file.
# Autore : Matteo Sepa
# Team Sviluppo: Alessandro Dinatale, Alberto Iovino, Arianna Vaccaro, Bilal Niederegger.

from bs4 import BeautifulSoup as BS
import requests
import re

# funzione che sostituisce la virgola con il punto nel valore estratto per renderlo leggibile dal programma
def removeComma(a):
    
    a = a.replace(",", ".")
    return a


# funzione per scrivere su file
def writeToFile(path, opening, today, perc):
        
    file = open(path, 'w')
    file.write(opening)
    file.write("\n")
    file.write(today)
    file.write("\n")
    file.write(perc)
    file.close()

# funzione per estrarre un parametro
def extractParameterOpeningValue(link):

    with open('/home/matteo/MEGAsync/code/Python/http_req/parse.html') as fp:
                
        r = requests.get(link)
        soup = BS(r.content, 'html5lib')

        valore_apertura = soup.find("span", id="ctl00_phContents_ctlInfoTitolo_lblOpen").text
        valore_apertura = removeComma(valore_apertura)
        print ("valore di apertura: ", valore_apertura)


        return valore_apertura

def extractParameterNowValue(link):

    with open('/home/matteo/MEGAsync/code/Python/http_req/parse.html') as fp:
                
        r = requests.get(link)
        soup = BS(r.content, 'html5lib')

        valore_ora = soup.find("span", id="ctl00_phContents_ctlHeader_lblPrice").text
        valore_ora = removeComma(valore_ora)
        print ("valore di oggi: ", valore_ora)


        return valore_ora


def extractParameterPerc(link):
    with open('/home/matteo/MEGAsync/code/Python/http_req/parse.html') as fp:
                
        r = requests.get(link)
        soup = BS(r.content, 'html5lib')

        perc = soup.find("span", id="ctl00_phContents_ctlHeader_lblPercentChange").text
        perc = removeComma(perc)
        print ("percentuale: ", perc)


        return perc



# main
def main():

    request = requests.get ('https://www.teleborsa.it/azioni-estero/tesla-tsla-us88160r1014-MjQuVFNMQQ')
    status_code = request.status_code

    print('\n')
    print("------- http parser by 4J -------\n")
    print("status code :", status_code, '\n')
    
    
    if status_code == 200:
        
        req_string = request.text
        print(req_string)

        link = 'https://www.teleborsa.it/azioni-estero/tesla-tsla-us88160r1014-MjQuVFNMQQ'
        
        
        
        # estrazione e scrittura del parametro
        valore_apertura = extractParameterOpeningValue(link)
        valore_ora = extractParameterNowValue(link)
        perc = extractParameterPerc(link)
        
        stringaApertura = str(valore_apertura)
        valore_apertura = removeComma(valore_apertura)

        stringaOggi = str(valore_ora)
        valore_ora = removeComma(valore_ora)

        stringaPerc = str(perc)
        perc = removeComma(perc)

        writeToFile("/home/matteo/MEGAsync/code/Python/Telegram_BOT/TSLA.txt", valore_apertura, valore_ora, perc)        
        
    else:
        
        print("Errore : ", status_code)



if __name__ == '__main__':
    main()
