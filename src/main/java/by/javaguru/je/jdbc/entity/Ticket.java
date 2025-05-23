package by.javaguru.je.jdbc.entity;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Ticket {
    private Long id;
    private Long passportNo;
    private String passengerName;
    private Flight flight;
    private String seatNo;
    private BigDecimal cost;

}
