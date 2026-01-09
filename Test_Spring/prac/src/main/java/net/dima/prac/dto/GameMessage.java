package net.dima.prac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameMessage {
    public enum MessageType {
        ENTER, TALK, START, PROGRESS, FINISH, COUNTDOWN, END, ERROR
    }

    private MessageType type;
    private String roomId;
    private String sender; // username
    private Object content; // Chat message or Progress int or Winner name
}
