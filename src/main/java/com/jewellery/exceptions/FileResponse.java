package com.jewellery.exceptions;


import lombok.*;
import org.springframework.validation.Errors;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String fileName;
    private String message;


}
