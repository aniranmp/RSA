package ir.aniranmp.web.model.transport;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseObject {
    private boolean result;
    private String message;

    public ResponseObject(boolean result) {
        this.result = result;
    }

    public ResponseObject(String message) {
        this.message = message;
    }

    public ResponseObject(boolean result, String message) {
        this.result = result;
        this.message = message;
    }
}
