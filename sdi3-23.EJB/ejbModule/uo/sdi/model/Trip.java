package uo.sdi.model;

import uo.sdi.model.types.AddressPoint;
import uo.sdi.model.types.TripStatus;
import uo.sdi.model.util.Association;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TTRIPS")
public class Trip {

    @GeneratedValue
    @Id
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address",
                    column = @Column(name = "DEPARTURE_ADDRESS")),
            @AttributeOverride(name = "city",
                    column = @Column(name = "DEPARTURE_CITY")),
            @AttributeOverride(name = "country",
                    column = @Column(name = "DEPARTURE_COUNTRY")),
            @AttributeOverride(name = "state",
                    column = @Column(name = "DEPARTURE_STATE")),
            @AttributeOverride(name = "zipCode",
                    column = @Column(name = "DEPARTURE_ZIPCODE")),
            @AttributeOverride(name = "waypoint.lat",
                    column = @Column(name = "DEPARTURE_WPT_LAT")),
            @AttributeOverride(name = "waypoint.lon",
                    column = @Column(name = "DEPARTURE_WPT_LON")),
    })
    private AddressPoint departure = new AddressPoint();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address",
                    column = @Column(name = "DESTINATION_ADDRESS")),
            @AttributeOverride(name = "city",
                    column = @Column(name = "DESTINATION_CITY")),
            @AttributeOverride(name = "country",
                    column = @Column(name = "DESTINATION_COUNTRY")),
            @AttributeOverride(name = "state",
                    column = @Column(name = "DESTINATION_STATE")),
            @AttributeOverride(name = "zipCode",
                    column = @Column(name = "DESTINATION_ZIPCODE")),
            @AttributeOverride(name = "waypoint.lat",
                    column = @Column(name = "DESTINATION_WPT_LAT")),
            @AttributeOverride(name = "waypoint.lon",
                    column = @Column(name = "DESTINATION_WPT_LON")),
    })
    private AddressPoint destination = new AddressPoint();

    private Date arrivalDate;
    private Date departureDate;
    private Date closingDate;

    private Integer availablePax = 0;
    private Integer maxPax = 0;
    private Double estimatedCost = 0.0;
    private String comments;

    @Enumerated(EnumType.ORDINAL)
    private TripStatus status;

    @ManyToOne
    private User promoter;

    @ManyToMany
    @JoinTable(
            name = "TAPPLICATIONS",
            joinColumns = @JoinColumn(name = "APPLIEDTRIPS_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICANTS_ID")
    )
    private Set<User> applicants = new HashSet<>();

    @OneToMany(mappedBy = "trip")
    private Set<Seat> seats = new HashSet<>();


    public Trip() {
    }

    public Trip(User promoter) {
        addPromoter(promoter);
    }

    public Long getId() {
        return id;
    }

    public AddressPoint getDeparture() {
        return departure;
    }

    public void setDeparture(AddressPoint departure) {
        this.departure = departure;
    }

    public AddressPoint getDestination() {
        return destination;
    }

    public void setDestination(AddressPoint destination) {
        this.destination = destination;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getAvailablePax() {
        return availablePax;
    }

    public void setAvailablePax(Integer availablePax) {
        this.availablePax = availablePax;
    }

    public Integer getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(Integer maxPax) {
        this.maxPax = maxPax;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public User getPromoter() {
        return promoter;
    }

    public void _setPromoter(User promoter) {
        this.promoter = promoter;
    }

    public Set<User> _getApplicants() {
        return applicants;
    }

    public Set<Seat> _getSeats() {
        return seats;
    }

    public Set<Seat> getSeats() {
        return new HashSet<>(seats);
    }

    public Set<User> getApplicants() {
        return new HashSet<>(applicants);
    }

    public void addPromoter(User promoter) {
        Association.Promotes.link(promoter, this);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Trip other = (Trip) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
}
