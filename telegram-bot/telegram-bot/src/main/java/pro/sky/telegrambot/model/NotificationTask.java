package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime dateTime;
    private String textmessage;
    private String text;

    public NotificationTask(LocalDateTime dateTime,  String textmessage, String text) {
        this.dateTime = dateTime;
        this.textmessage = textmessage;
        this.text = text;
    }

    public NotificationTask() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTextmessage() {
        return textmessage;
    }

    public void setTextmessage(String textmessage) {
        this.textmessage = textmessage;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationTask)) return false;
        NotificationTask that = (NotificationTask) o;
        return getId() == that.getId() && Objects.equals(getDateTime(), that.getDateTime()) && Objects.equals(getTextmessage(), that.getTextmessage()) && Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateTime(), getTextmessage(), getText());
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", textmessage='" + textmessage + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
