package ir.aniranmp.web.model.local;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportResult {
    boolean result;
    String message;

    public ImportResult(boolean result) {
        this.result = result;
    }
}
