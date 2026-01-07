package net.dima.prac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private String roomId;
    private String title;
    private boolean hasPassword;
    private int userCount;
    private String status; // WAITING, PLAYING
}
