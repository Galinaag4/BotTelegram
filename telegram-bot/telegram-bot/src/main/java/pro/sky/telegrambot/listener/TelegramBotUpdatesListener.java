package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationRepository;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final NotificationRepository notificationRepository;


    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;

    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String message = update.message().text();
            if(message.equals("/start")) {
                SendMessage message1 = new SendMessage(update.message().chat().id(), "Hello!!!");
                telegramBot.execute(message1);

            }
            Pattern pattern = Pattern.compile("([0-9\\.\\ \\:]+)([\\W]+)");
            Matcher matcher = pattern.matcher(message);
            if (matcher.matches()) {
                String dateTime = matcher.group(1);
                String text = matcher.group(2);


            }





        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {

    }
    public NotificationTask createTask(NotificationTask notificationTask) {
        logger.info("Метод createTask ");
        return notificationRepository.save(notificationTask);
    }


}
