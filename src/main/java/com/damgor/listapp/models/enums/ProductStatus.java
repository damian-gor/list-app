package com.damgor.listapp.models.enums;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public enum ProductStatus implements Serializable {
    BOUGHT,
    NOT_AVAILABLE,
    IN_PROGRESS
}
