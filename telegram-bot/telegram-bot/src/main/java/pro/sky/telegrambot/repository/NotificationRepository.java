package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.NotificationTask;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationTask,Long> {

    Collection<NotificationTask> save(LocalDate date);
    Collection<NotificationTask> save(LocalTime time);
    Collection<NotificationTask> save(String text);

}
