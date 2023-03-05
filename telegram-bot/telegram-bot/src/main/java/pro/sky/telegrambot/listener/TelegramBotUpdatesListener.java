package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationRepository;

import javax.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    @Autowired
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
            if (message.equals("/start")) {
                SendMessage message1 = new SendMessage(update.message().chat().id(), "Hello!!!");
                telegramBot.execute(message1);
                //System.out.println(update.message().chat().id());

            }
            Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
            Matcher matcher = pattern.matcher(message);
            if (matcher.matches()) try {
                LocalDateTime dateTime = LocalDateTime.parse(matcher.group(1), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                String text = matcher.group(3);

                NotificationTask notificationTask = new NotificationTask();
                notificationTask.setChatId(update.message().chat().id());
                notificationTask.setDateTime(dateTime);
                notificationTask.setText(text);
                notificationTask.setTextmessage("примечание");
                logger.info("Saving {} to db", notificationTask);
                notificationRepository.save(notificationTask);
            } catch (Exception e) {
                System.out.println("Не могу распознать строку");


            }


        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;

    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        logger.info("Метод run ");
        try {
            LocalDateTime tasktime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            NotificationTask dateTimes = notificationRepository.findByDateTime(tasktime);

            if (dateTimes != null) {
                SendMessage message = new SendMessage(dateTimes.getChatId(), "НАПОМИНАНИЕ");
                telegramBot.execute(message);

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void editMessage(Long id) {
       NotificationTask chatUser = notificationRepository.getAllByChatId(id);
        if (chatUser != null) {
            SendMessage message = new SendMessage(chatUser.getChatId(), "НАПОМИНАНИЕ");
            telegramBot.execute(message);
    }
}}
















