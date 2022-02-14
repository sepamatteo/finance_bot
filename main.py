import Const as keys 
from telegram.ext import *
import Responses as R

esci = False

print ("BOT has started...")

def startCommand(update, context):
    update.message.reply_text('Scrivi un messaggio per avviare la conversazione')



def handleMssg(update, context):
    
    text = str(update.message.text).lower()
    response = R.sampleResponse(text)

    update.message.reply_text(response)

def handleParam(update, context):
    text = str(update.message.text).lower()
    response = R.printParam(text)
    
    update.message.reply_text(response)



def error(update, context):
    print(f"Update {update} caused error {context.error}")



def main():


    updater = Updater(keys.API_KEY, use_context = True)
    dp = updater.dispatcher

    dp.add_handler(CommandHandler("start", startCommand))

    dp.add_handler(MessageHandler(Filters.text, handleMssg))
            

    dp.add_error_handler(error)

    updater.start_polling()
    updater.idle()
        

    
if __name__ == "__main__":
    main()