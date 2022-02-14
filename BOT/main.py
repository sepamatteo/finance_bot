import Const as keys 
from telegram.ext import *
import Responses as R
import os

esci = False

print ("BOT has started...")

def startCommand(update, context):
    update.message.reply_text('Scrivi un messaggio per avviare la conversazione')

def graphCommand(update, context):
    pic = 'https://www.teleborsa.it/Charts/QuickChart_Image.aspx?d=9A0100001F8B0800000000000400EDBD07601C499625262F6DCA7B7F4AF54AD7E074A10880601324D8904010ECC188CDE692EC1D69472329AB2A81CA6556655D661640CCED9DBCF7DE7BEFBDF7DE7BEFBDF7BA3B9D4E27F7DFFF3F5C6664016CF6CE4ADAC99E2180AAC81F3F7E7C1F3F227EF147571F3DBAB77B6FF4D1FCA347BB073BA38FA6D9478FBEF78B3FCADE7DF4E8177F74FED1A38F8EEB222B470F56ED47BF64441FEFC53FBF1EF838D6FC977C9FBA69B89B724A5FD517936CEBDEC3D1EEDEEE686F777FB47BE7A3D147E7B16F76C6F7EEE3CB86BA6BEB753EFAA8BD5EE584390D6056A0ABDF4D3EF8E84D5EE693AA6EB2F1C93CABDB66FC6672B66CE89D45BE6C9F666D76B63CAF4669B719C146BF676F767676EEEDEE1DDCFBF4017D54B4D405D1668681ECEDECED6EEF1C6CEFEEBFD9DB7DB4B3FB68777FBC7370EFFE273B7B8F7676A87535C7A8CEB3B221FC2EED6FD3594163FE7447865FD2F0E9E7C48E72EFFEFD91F93F11E097FC3F60553FA69A010000'
    context.bot.send_photo(chat_id=update.effective_chat.id, photo=pic)

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
    dp.add_handler(CommandHandler("graph", graphCommand))

    dp.add_handler(MessageHandler(Filters.text, handleMssg))
            

    dp.add_error_handler(error)

    updater.start_polling()
    updater.idle()
        

    
if __name__ == "__main__":
    main()
