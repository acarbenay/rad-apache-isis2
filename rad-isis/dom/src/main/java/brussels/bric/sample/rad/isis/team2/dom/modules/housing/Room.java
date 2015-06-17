package brussels.bric.sample.rad.isis.team2.dom.modules.housing;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;

import javax.jdo.annotations.*;

/**
* Created by ahmed on 17/06/15.
*/

@javax.jdo.annotations.PersistenceCapable(identityType= IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column="id")
@javax.jdo.annotations.Version(
        strategy= VersionStrategy.VERSION_NUMBER,
        column="version")
@DomainObject(
        objectType = "ROOM"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_CHILD
)
public class Room {


    private Integer surface;

//    @Persistent
//    @Joina
//    private Housing housing;

    @javax.jdo.annotations.Column(allowsNull="false", length = 100)
    @Title(sequence="1")
    @Property(
            editing = Editing.DISABLED
    )
    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

//    public Housing getHousing() {
//        return housing;
//    }
//
//    public void setHousing(Housing housing) {
//        this.housing = housing;
//    }

    @javax.inject.Inject
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

}
