package brussels.bric.sample.rad.isis.team2.dom.modules.housing;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.util.ObjectContracts;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER,
        column="version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM brussels.bric.sample.rad.isis.team2.dom.modules.housing.Address "),
        @javax.jdo.annotations.Query(
                name = "findByLocality", language = "JDOQL",
                value = "SELECT "
                        + "FROM brussels.bric.sample.rad.isis.team2.dom.modules.housing.Address "
                        + "WHERE locality.indexOf(:locality) >= 0 ")
})
@javax.jdo.annotations.Unique(name="Address_UNQ", members = {"street,number,zipcode,locality,country"})
@DomainObject(
        objectType = "ADDRESS"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Address implements Comparable<Address> {

    //region > identificatiom
    public TranslatableString title() {
        return TranslatableString.tr("Object: {street}, {number} - {zipcode} {locality}", "street", getStreet(), "number", getNumber(), "zipcode", getZipcode(), "locality", getLocality());
    }
    //endregion

    //region > name (property)

    private String street;
    private int number;
    private String box;
    private int zipcode;
    private String locality;
    private String country;

    @javax.jdo.annotations.Column(allowsNull="false", length = 100)
    @MemberOrder(sequence="0")
    @Property(
            editing = Editing.DISABLED
    )
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    @javax.jdo.annotations.Column(allowsNull="false")
    @MemberOrder(sequence="1")
    @Property(
            editing = Editing.DISABLED
    )
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
		this.number = number;
	}
    
    @javax.jdo.annotations.Column(allowsNull="false", length = 10)
    @MemberOrder(sequence="2")
    @Property(
            editing = Editing.DISABLED
    )
    public String getBox() {
        return box;
    }
    
	public void setBox(String box) {
		this.box = box;
	}
	
	@javax.jdo.annotations.Column(allowsNull="false")
    @MemberOrder(sequence="3")
    @Property(
            editing = Editing.DISABLED
    )
    public int getZipcode() {
        return zipcode;
    }

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	@javax.jdo.annotations.Column(allowsNull="false", length = 100)
    @MemberOrder(sequence="4")
    @Property(
            editing = Editing.DISABLED
    )
    public String getLocality() {
        return locality;
    }

	public void setLocality(String locality) {
		this.locality = locality;
	}

	@javax.jdo.annotations.Column(allowsNull="false", length = 100)
    @MemberOrder(sequence="5")
    @Property(
            editing = Editing.DISABLED
    )
    public String getCountry() {
        return country;
    }

	public void setCountry(String country) {
		this.country = country;
	}

	public static class UpdateAddressDomainEvent extends ActionDomainEvent<Address> {
        public UpdateAddressDomainEvent(final Address source, final Identifier identifier, final Object... arguments) {
            super(source, identifier, arguments);
        }
    }

    @Action(
            domainEvent = UpdateAddressDomainEvent.class
    )
    public Address updateAddress(
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "New street")
            final String street,
            @ParameterLayout(named = "New number")
            final Integer number,
            @Parameter(maxLength = 10)
            @ParameterLayout(named = "New box")
            final String box,
            @ParameterLayout(named = "New zipcode")
            final Integer zipcode,
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "New locality")
            final String locality,
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "New country")
            final String country
    ) {
        setStreet(street);
        setNumber(number);
        setBox(box);
        setZipcode(zipcode);
        setLocality(locality);
        setCountry(country);
        return this;
    }

    public String default0UpdateAddress() {
        return getStreet();
    }

    public Integer default1UpdateAddress() {
        return getNumber();
    }

    public String default2UpdateAddress() {
        return getBox();
    }

    public Integer default3UpdateAddress() {
        return getZipcode();
    }

    public String default4UpdateAddress() {
        return getLocality();
    }
    
    public String default5UpdateAddress() {
        return getCountry();
    }

    //endregion

    //region > compareTo

    @Override
    public int compareTo(final Address other) {
        return ObjectContracts.compare(this, other, "street,number,zipcode,locality,country");
    }

    //endregion

    //region > injected services

    @javax.inject.Inject
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    //endregion

}
