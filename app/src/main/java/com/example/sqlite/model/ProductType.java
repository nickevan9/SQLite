package com.example.sqlite.model;

import java.io.Serializable;

public class ProductType implements Serializable {
    private String typeName;
    private String typeID;

    public ProductType(String typeID, String typeName   ) {
        this.typeName = typeName;
        this.typeID = typeID;
    }

    public ProductType() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }
}
