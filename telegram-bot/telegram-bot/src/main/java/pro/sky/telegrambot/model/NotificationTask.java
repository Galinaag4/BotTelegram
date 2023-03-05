package pro.sky.telegrambot.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity

public class NotificationTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTime;
    private Long chatId;


    private String textmessage;

    private String text;


    public NotificationTask(Long chatId,LocalDateTime dateTime,  String textmessage, String text) {
        this.chatId = chatId;
        this.dateTime = dateTime;
        this.textmessage = textmessage;
        this.text = text;
    }

    public NotificationTask() {
        //this.id = counter++;

    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return Objects.equals(getDateTime(), that.getDateTime()) && Objects.equals(getChatId(), that.getChatId()) && Objects.equals(getTextmessage(), that.getTextmessage()) && Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateTime(), getChatId(), getTextmessage(), getText());
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "dateTime=" + dateTime +
                ", chatId=" + chatId +
                ", textmessage='" + textmessage + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
