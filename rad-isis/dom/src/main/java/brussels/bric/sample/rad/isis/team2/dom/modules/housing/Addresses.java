package brussels.bric.sample.rad.isis.team2.dom.modules.housing;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(repositoryFor = Address.class)
@DomainServiceLayout(menuOrder = "15")
public class Addresses {

    //region > listAll (action)
    @Action(
             semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Address> listAll() {
        return container.allInstances(Address.class);
    }
    //endregion

    //region > findByDescription (action)
    @Action(
            semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "2")
    public List<Address> findByLocality(
            @ParameterLayout(named="Locality")
            final String locality
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Address.class,
                        "findByLocality",
                        "locality", locality));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "3")
    public Address create(
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "Street")
            final String street,
            @ParameterLayout(named = "Number")
            final Integer number,
            @Parameter(maxLength = 10)
            @ParameterLayout(named = "Box")
            final String box,
            @ParameterLayout(named = "Zipcode")
            final Integer zipcode,
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "Locality")
            final String locality,
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "Country")
            final String country) {
        final Address obj = container.newTransientInstance(Address.class);
        obj.setStreet(street);
        obj.setNumber(number);
        obj.setBox(box);
        obj.setZipcode(zipcode);
        obj.setLocality(locality);
        obj.setCountry(country);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
}
