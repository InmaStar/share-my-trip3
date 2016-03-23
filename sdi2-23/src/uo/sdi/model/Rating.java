package uo.sdi.model;

import uo.sdi.model.util.Association;

import javax.persistence.*;

@Entity
@Table(name = "TRATINGS")
public class Rating {
    @GeneratedValue
    @Id
    private Long id;
    private String comment;
    private Integer value = 0;

    @ManyToOne
    private Seat from;

    @ManyToOne
    private Seat about;

    public Rating() {
    }

    public Rating(Seat from, Seat about) {
        Association.Rate.link(this, from, about);
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Seat getFrom() {
        return from;
    }

    public void _setFrom(Seat from) {
        this.from = from;
    }

    public Seat getAbout() {
        return about;
    }

    public void _setAbout(Seat about) {
        this.about = about;
    }
}
