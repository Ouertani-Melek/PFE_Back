package com.backend.guestnhouse.models.es;


import com.backend.guestnhouse.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Document(indexName = "guestnhouse",type = "house",shards = 2)
public class EsHouse {

    @Id
    private String id;

    private String houseName;

    private String description;

    private String addressLine;

    private String houseType;

    private int archived;

    private float latitude;

    private float longitude;

    private float priceHouse;

    private List<String> images;

    public EsHouse() {
    }
    /*
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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss +SSSS")
    private Date created;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss +SSSS")
    private Date modified;

    private String zipCode;



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
    private Set<Currency> currencies;*/
/*
    public EsHouse(House house){
        this.id = house.getId();
        this.houseName = house.getHouseName();
        this.description = house.getDescription();
        this.houseType = house.getDescription();
        this.houseCategory = house.getHouseCategory();
        this.roomsNumber = house.getRoomsNumber();
        this.priceHouse = house.getPriceHouse();
        this.languages = house.getLanguages();
        this.addressStreet = house.getAddressStreet();
        this.addressLine = house.getAddressLine();
        this.countryRegion = house.getCountryRegion();
        this.city = house.getCity();
        this.bedroomCount = house.getBedroomCount();
        this.betCount = house.getBetCount();
        this.availibility = house.getAvailibility();
        this.minmumStay = house.getMinmumStay();
        this.refundType = house.getRefundType();
        this.cancellation = house.getCancellation();
        this.houseFee = house.getHouseFee();
        this.instantBooking = house.isInstantBooking();
        this.created = house.getCreated();
        this.modified = house.getModified();
        this.zipCode = house.getZipCode();
        this.latitude = house.getLatitude();
        this.longitude = house.getLongitude();
        this.hostName = house.getHostName();
        this.hostBio = house.getHostBio();
        this.petPolicy = house.getPetPolicy();
        this.partyPolicy = house.getPartyPolicy();
        this.images = house.getImages();
        this.archived = house.getArchived();
        this.user = house.getUser();
        this.amenities = house.getAmenities();
        this.activities = house.getActivities();
        this.categories = house.getCategories();
        this.currencies = house.getCurrencies();
    }*/

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

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
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

    public float getPriceHouse() {
        return priceHouse;
    }

    public void setPriceHouse(float priceHouse) {
        this.priceHouse = priceHouse;
    }
/*
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

    public Boolean getPartyPolicy() {
        return partyPolicy;
    }

    public void setPartyPolicy(Boolean partyPolicy) {
        this.partyPolicy = partyPolicy;
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
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsHouse esHouse = (EsHouse) o;
        return Objects.equals(id, esHouse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
