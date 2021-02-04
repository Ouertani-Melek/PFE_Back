package com.backend.guestnhouse.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(collection = "houses")

public class House {

    @Id
    private String id;

    private String houseName;

    private String description;

    private String houseType;

    private String houseCategory;

    private int roomsNumber;

    private float priceHouse;

    private String languages;

    private String addressStreet;

    private String addressLine;

    private String countryRegion;

    private String city;

    private int bedroomCount;

    private int betCount;

    private int availibility;

    private int minmumStay;

    private int refundType;

    private int cancellation;

    private float houseFee;

    private boolean instantBooking;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date created;

    private Date modified;

    private String zipCode;

    private float latitude;

    private float longitude;

    private String hostName;

    private String hostBio;

    private Boolean petPolicy;

    private Boolean partyPolicy;

    private List<String> images;

    private int archived;

    @DBRef
    private User user;

    @DBRef
    private Set<Amenities> amenities;

    @DBRef
    private Set<Activities> activities;

    @DBRef
    private Set<Categories> categories;

    @DBRef
    private Set<Currency> currencies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public float getPriceHouse() {
        return priceHouse;
    }

    public void setPriceHouse(float priceHouse) {
        this.priceHouse = priceHouse;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostBio() {
        return hostBio;
    }

    public void setHostBio(String hostBio) {
        this.hostBio = hostBio;
    }

    public Boolean getPetPolicy() {
        return petPolicy;
    }

    public void setPetPolicy(Boolean petPolicy) {
        this.petPolicy = petPolicy;
    }

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Set<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<Amenities> amenities) {
        this.amenities = amenities;
    }

    public int getBedroomCount() {
        return bedroomCount;
    }

    public void setBedroomCount(int bedroomCount) {
        this.bedroomCount = bedroomCount;
    }

    public int getBetCount() {
        return betCount;
    }

    public void setBetCount(int betCount) {
        this.betCount = betCount;
    }

    public int getAvailibility() {
        return availibility;
    }

    public void setAvailibility(int availibility) {
        this.availibility = availibility;
    }

    public int getMinmumStay() {
        return minmumStay;
    }

    public void setMinmumStay(int minmumStay) {
        this.minmumStay = minmumStay;
    }

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

    public int getCancellation() {
        return cancellation;
    }

    public void setCancellation(int cancellation) {
        this.cancellation = cancellation;
    }

    public float getHouseFee() {
        return houseFee;
    }

    public void setHouseFee(float houseFee) {
        this.houseFee = houseFee;
    }

    public boolean isInstantBooking() {
        return instantBooking;
    }

    public void setInstantBooking(boolean instantBooking) {
        this.instantBooking = instantBooking;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Set<Activities> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activities> activities) {
        this.activities = activities;
    }

    public Set<Categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }

    public Set<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    public Boolean getPartyPolicy() {
        return partyPolicy;
    }

    public void setPartyPolicy(Boolean partyPolicy) {
        this.partyPolicy = partyPolicy;
    }


}
