import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EchoBot extends TelegramLongPollingBot {
    private DB database;
    public EchoBot(){
        database = new DB();
    }


    public String getBotUsername() {
        return "Eserciziomariani";
    }
    @Override
    public String getBotToken() {
        // inserire qui il proprio token
        return "6801446456:AAHpRAuOfmau29ugkBAP6wXXjmL93GboMjE";
    }
    public void onUpdateReceived(Update update) {
        String txt=update.getMessage().getText();
        String message="";
        SendMessage sendMessage = new SendMessage();
        if (txt.startsWith("/add")){
            database.aggiungiFilm(update.getMessage().getChatId().toString(),txt.substring(5));
            message="film aggiunto";
        }
        else if (txt.startsWith("/remove")){
            database.Elimina(update.getMessage().getChatId().toString(),txt.substring(8));
            message="film rimosso";
        }
        else if (txt.startsWith("/best")){
            message=WebCrawler.CrawlBestMovies();
        }
        else if (txt.startsWith("/show")){
            message = String.valueOf(database.select());
        }
        else if (txt.startsWith("/start")) {
            message = "usare /add per aggiungere un film alla lista dei preferiti, usare /remove per rimuovere un film dalla lista dei preferiti, usare /show per mostrare la lista dei preferiti e infine con /best verrà mostrata la lista dei 10 film che hanno il maggior incasso.";
        }
        else message=txt;

        String chatId=update.getMessage().getChatId().toString();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            // gestione errore in invio
        }
    }
}
