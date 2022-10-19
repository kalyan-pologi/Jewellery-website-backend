package com.jewellery.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    long fieldValue;

    String fieldName;
    public ResourceNotFoundException(String resourceName, String field, long fieldValue) {
        super(String.format("%s not found with %s : %s" , resourceName,field,fieldValue ));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldValue = fieldValue;
    }
    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s : %s" , resourceName,fieldName,fieldName ));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
}
