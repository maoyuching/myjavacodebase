package v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Ticket {
    Station begin;
    Station end;
    Seat seat;

    LocalDateTime beginTime;

    float price;
}
