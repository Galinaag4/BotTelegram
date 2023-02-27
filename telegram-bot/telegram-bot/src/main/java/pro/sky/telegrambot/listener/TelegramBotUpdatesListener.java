package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    NotificationTask notificationTask;

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            if(message.text().equals("/start")) {
                SendMessage message1 = new SendMessage(message.chat().id(), "Hello!!!");
                telegramBot.execute(message1);

            }
            System.out.println(getTaskFromMessage(message.text()));
            System.out.println(getDateFromMessage(message.text()));
            System.out.println(getTimeFromMessage(message.text()));
            notificationTask = new NotificationTask();
            notificationTask.setText(getTaskFromMessage(message.text()));
            notificationTask.setDate(LocalDate.parse(getTaskFromMessage(message.text())));
            notificationTask.setTime(LocalTime.parse(getTaskFromMessage(message.text())));

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    public String getTaskFromMessage(String message){
        String[] strings = message.split(" ");
        String date = strings[0];
        String time = strings [1];
        String text="";
        for (int i = 2; i < strings.length ; i++) {
            text+=strings[i]+" ";
        }
        return text;

    }
    public String getTimeFromMessage(String message){
        String[] strings = message.split(" ");
        String time = strings [1];
        return time;

    }
    public String getDateFromMessage(String message){
        String[] strings = message.split(" ");
        String date = strings[0];
        return date;

    }

}
