package in.neuprakash.SajiloYatra.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@NotBlank(message = "Full Name is required")
                             String fullName,

                             @NotBlank(message = "Email is required")
                             @Email(message = "Invalid Email")
                             String email,

                             @NotBlank(message = "Password is required")
                             String password,

                             @NotBlank(message = "Address is required")
                             String address
) {


}
