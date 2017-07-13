package com.mygglo.jhipster.subscription.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A MySubscription.
 */
@Entity
@Table(name = "my_subscription")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MySubscription implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_label")
    private String label;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "personid")
    private Long personid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public MySubscription label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDate getDate() {
        return date;
    }

    public MySubscription date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getPersonid() {
        return personid;
    }

    public MySubscription personid(Long personid) {
        this.personid = personid;
        return this;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MySubscription mySubscription = (MySubscription) o;
        if (mySubscription.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mySubscription.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MySubscription{" +
            "id=" + getId() +
            ", label='" + getLabel() + "'" +
            ", date='" + getDate() + "'" +
            ", personid='" + getPersonid() + "'" +
            "}";
    }
}
