package com.example.dining.restaurant;

import java.io.Serializable;
import java.util.Objects;

public class RestaurantId implements Serializable {
    private String name;
    private String zip;

    public RestaurantId() {}

    public RestaurantId(String name, String zip) {
        this.name = name;
        this.zip = zip;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass()) return false;
        RestaurantId restId = (RestaurantId) other;
        return this.name.equals(restId.name) &&
               this.zip.equals(restId.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.zip);
    }
}
