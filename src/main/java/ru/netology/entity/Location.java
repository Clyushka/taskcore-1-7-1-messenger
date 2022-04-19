package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Location location = (Location) obj;
        return this.builing == location.builing
                && (
                        this.city == location.city
                                || (this.city != null && this.city.equals(location.city))
                                || (location.city != null && location.city.equals(this.city)))
                && (
                        this.street == location.street
                                || (this.street != null && this.street.equals(location.street))
                                || (location.street != null && location.street.equals(this.street)))
                && (
                        this.country == location.country
                                || (this.country != null && this.country.equals(location.country))
                                || (location.country != null && location.country.equals(this.country)));
    }
}
