package com.backend.guestnhouse.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "currency")
public class Currency {

    @Id
    private String id;

    private String currenyName;

    private Date created;

    private Date modified;

    private int archived;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrenyName() {
        return currenyName;
    }

    public void setCurrenyName(String currenyName) {
        this.currenyName = currenyName;
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

    public int getArchived() {
        return archived;
    }

    public void setArchived(int archived) {
        this.archived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id.equals(currency.id) &&
                currenyName.equals(currency.currenyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
