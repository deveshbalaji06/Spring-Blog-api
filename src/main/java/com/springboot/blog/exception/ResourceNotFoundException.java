package com.springboot.blog.exception;

public class ResourceNotFoundException extends  RuntimeException{
    private String resourceName;
    private long fieldName;
    private String fieldValue;



    public ResourceNotFoundException(String resourceName, String fieldValue, long fieldName) {
        super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
        this.fieldName = fieldName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public long getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
