from datetime import datetime 
import time

esci = False

f = open("parametro.txt","r+")
a = f.read()
firstParameter = float(a)
f.close()

def switchParam(a,b):
    a = b


def writeToFile(path, content):
        
    file = open(path, 'w')
    strContent = str(content)
    file.write(strContent)
    file.close()

def sampleResponse(input_txt):
    
    while esci != True:
        
        f = open("parametro.txt","r+")
        a = f.read()
        parametro = float(a)
        f.close()
        
        
        user_message = str(input_txt).lower()

        parametroString = str(parametro)
        
        
        if user_message in ("ciao", "hi", "hallo",):
            
            return "Ciao!"
        
        if user_message in ("chi sei?", "cosa sei?",):
            
            return "Sono il prototipo per il bot di telegram del programma di pcto"
        
        if user_message in ("che ore sono?", "ore", "orario",):
            
            now = datetime.now()
            date_time = now.strftime("%d/%m/%y, %H:%M:%S")
            return str(date_time)
        
        if user_message in ("parametro", "restituisci parametro",):
            
            return parametro
    
        if user_message == ("check"):
            #time.sleep(5)
            if parametro != firstParameter:
                
                if parametro > firstParameter:
                    
                    switchParam(firstParameter, parametro)
                    writeToFile("parametro.txt", parametro)
                    return("Il valore del parametro e' aumentato, valore attuale del parametro: ", parametro)
                else:
                    
                    switchParam(firstParameter, parametro)
                    writeToFile("parametro.txt", parametroString)
                    return("Il valore del parametro e' diminuito, valore attuale del parametro: ", parametro)
            else:
                return("Il valore del parametro non è variato")
        
        return "Non ho capito."
    






    

    

    
    
    
