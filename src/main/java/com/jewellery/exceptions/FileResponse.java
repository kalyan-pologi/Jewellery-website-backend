package com.jewellery.exceptions;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private String fileName;
    private String message;

}
