import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class RecordVenta {
    private int key;
    private String info;
    private LocalDate date;
    private double precio;
}
