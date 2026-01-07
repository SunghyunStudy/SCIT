package net.practice4.domian.dto;

import java.time.LocalDateTime;

import jakarta.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestBookDTO {
    Integer num;
    String name;
    String Password;
    String message;
    LocalDateTime inputdate;

    Integer recommendCnt;
}
