package game;

import game.dataStorage.DataStorage;
import game.world.World;
import game.world.creatures.playable.classes.Mage;
import game.world.creatures.playable.classes.Rogue;
import game.world.creatures.playable.classes.Warrior;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import server.Bot;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private World world;
    public Game() {
        this.dataStorage = new DataStorage();
        this.world = new World();
        world.setGame(this);
    }

    private DataStorage dataStorage;

    public Bot getBot() {
        return bot;
    }

    private Bot bot;

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public void commandProcessor(Update update){
        if (update.hasMessage()){messageCommandProcessor(update.getMessage());}
        if (update.hasCallbackQuery()){callBackQueryProcessor(update.getCallbackQuery());}
    }


    public void messageCommandProcessor(Message message){
        if (!dataStorage.getPlayerIds().contains(message.getChatId().toString())){
            dataStorage.addPlayerId(message.getChatId().toString());
            dataStorage.addPlayer(message.getChatId().toString());
            bot.sendMsg(message.getChatId().toString(),"Добро пожаловать путник! Ты стоишь у порога мира невероятных приключений неожиданных открытий и чудес. Мир, где гранью является только твое вооброжение. Готов приступать? Тогда введи ответом, как к тебе можно обращаться?");

        }else if (dataStorage.getPlayerMap().get(message.getChatId().toString()).getPlayerName()==null){
            String playername = message.getText();
            System.out.println(playername+" joined the game");
            dataStorage.getPlayerMap().get(message.getChatId().toString()).setPlayerName(playername);
            bot.sendMsgWithInlineButtons(message.getChatId().toString(),"Теперь настало время определиться, кем ты хочешь быть?",heroButtons());
        }
    }

    public void callBackQueryProcessor(CallbackQuery callbackQuery){
        switch (callbackQuery.getData()){
            case "/warrior":{dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()).addChampion(new Warrior());
            bot.sendMsgWithInlineButtons(callbackQuery.getMessage().getChatId().toString(),"Воин? Отличный выбор "+ dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()).getPlayerName() + "! Сокрушите своих врагов, сожмите обагрите свою сталь кровью, ради чести, славы и богатств",joinworldbutton());}
            break;
            case "/rouge":{dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()).addChampion(new Rogue());
                bot.sendMsgWithInlineButtons(callbackQuery.getMessage().getChatId().toString(),"Разбойник? Отличный выбор "+ dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()).getPlayerName() + "! Всех ваших врагов постигнет смерть из ниоткуда, каждый кто перейдет вам дорогу - сгнинет при неизвестных обстоятельтвах, набивайте карманы золотишком, скоро вашем именем будут пугать детей могущественных королей",joinworldbutton());}
                break;
            case "/mage":{dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()).addChampion(new Mage());
                bot.sendMsgWithInlineButtons(callbackQuery.getMessage().getChatId().toString(),"Маг? Отличный выбор "+ dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()).getPlayerName() + "! Ваших знаний жаждут мейстеры всех королевств, вашему могуществу завидуют сами Боги, вперед к приключениям, и пусть каждый, вставший на вашем пути превратиться в горстку пепла.",joinworldbutton());}
                break;
            case "/joinworld": world.joinworld(dataStorage.getPlayerMap().get(callbackQuery.getMessage().getChatId().toString()));
            break;
        }
    }


    private List<List<InlineKeyboardButton>> heroButtons(){
                List<List<InlineKeyboardButton>> herobuttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("Воин").setCallbackData("/warrior"));
        buttons1.add(new InlineKeyboardButton().setText("Маг").setCallbackData("/mage"));
        buttons1.add(new InlineKeyboardButton().setText("Разбойник").setCallbackData("/rouge"));
        herobuttons.add(buttons1);
        return herobuttons;
    }

    private List<List<InlineKeyboardButton>> joinworldbutton(){
        List<List<InlineKeyboardButton>> herobuttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("Войти в мир").setCallbackData("/joinworld"));
        herobuttons.add(buttons1);
        return herobuttons;
    }






}
