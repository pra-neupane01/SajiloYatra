package in.neuprakash.SajiloYatra.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
    private boolean status;
    private String message;
    private T data;

}


