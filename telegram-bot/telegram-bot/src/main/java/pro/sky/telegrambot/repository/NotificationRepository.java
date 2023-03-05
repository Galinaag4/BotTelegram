package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationTask,Long> {

      NotificationTask findByDateTime(LocalDateTime dateTime);
     NotificationTask  getAllByChatId(Long ChatId);



}
