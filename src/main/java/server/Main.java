package server;

import game.Game;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.logging.Logger;

public class Main {

    final static private String PROXY_HOST="tg.airpush.com";
    final static private int PROXY_PORT=1883;
    final static private String BOT_TOKEN="613804841:AAGNX64dc4_gs1f1AhgYMA3tI6Jxz1cPTDI";
    final static private String BOT_NAME="TestMUDgame";
//
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {

             Bot myBot = new Bot();
             telegramBotsApi.registerBot(myBot);
             Game game = new Game();
             myBot.setGame(game);
             game.setBot(myBot);

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
            }
