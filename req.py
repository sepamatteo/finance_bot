# Programma che esegue il parsing di una pagina http e ne scrive il contenuto sia completo che a linea singola su file.
# Autore : Matteo Sepa

from bs4 import BeautifulSoup as BS
import requests


# funzione per scrivere tutta la stringa sulla stessa linea e senza spazi
def stringOnSameLine(a):
    a = a.replace(" ", "")
    a = str.join("", a.splitlines())
    return a

# funzione per rimuovere il tag html
def removeTag(a):
    a = a.replace("<p>", "")
    a = a.replace("</p>", "")
    return a


# funzione per scrivere su file
def writeToFile(path, content):
        
    file = open(path, 'w')
    file.write(content)
    file.close()

# funzione per estrarre un parametro
def extractParameter():

    with open('parse.html') as fp:
        
        soup = BS(fp, 'html5lib')
        parametro = soup.find('p')

        print ("parametro: ", parametro)
        return parametro


# main
def main():

    request = requests.get ('http://www.sepamatteo.com/error')
    status_code = request.status_code

    print('\n')
    print("------- http parser by matteo sepa -------\n")
    print("status code :", status_code, '\n')
    
    
    if status_code == 200:
        
        req_string = request.text
        print(req_string)

        # scrittura della pagina html sul file
        PATH_TO_FILE = './parse.html'
        CONTENT = req_string;
        writeToFile(PATH_TO_FILE, CONTENT)

        # scrittura del file html su singola linea
        string_with_no_spaces = stringOnSameLine(req_string)
        writeToFile("./parse_no_spaces.txt", string_with_no_spaces)

        # estrazione e scrittura del parametro
        parametro = extractParameter()
        stringa_parametro = str(parametro)
        parametro_estratto = removeTag(stringa_parametro)
        writeToFile("/home/matteo/MEGAsync/code/Python/Telegram_BOT/parametro.txt", parametro_estratto)
        #writeToFile("/home/matteo/MEGAsync/code/Python/Telegram_BOT/primo_parametro.txt", parametro_estratto)
    
        
    else:
        
        print("Errore : ", status_code)


    

if __name__ == '__main__':
    main()

