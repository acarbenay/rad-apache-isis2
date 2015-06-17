/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
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
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.Title;
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
                        + "FROM brussels.bric.sample.rad.isis.team2.dom.modules.housing.Housing "),
        @javax.jdo.annotations.Query(
                name = "findByDescription", language = "JDOQL",
                value = "SELECT "
                        + "FROM brussels.bric.sample.rad.isis.team2.dom.modules.housing.Housing "
                        + "WHERE description.indexOf(:description) >= 0 ")
})
@javax.jdo.annotations.Unique(name="Housing_description_UNQ", members = {"description"})
@DomainObject(
        objectType = "SIMPLE"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class Housing implements Comparable<Housing> {


    //region > identificatiom
    public TranslatableString title() {
        return TranslatableString.tr("Object: {description}", "description", getDescription());
    }
    //endregion

    //region > name (property)

    private String description;
//    private int floor;
//    private String box;
//    private int surface;
//    private int rooms;
//    private boolean elevator;
//    private double price;

    @javax.jdo.annotations.Column(allowsNull="false", length = 100)
    @Title(sequence="1")
    @Property(
            editing = Editing.DISABLED
    )
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@javax.jdo.annotations.Column(allowsNull="false")
//    @Title(sequence="2")
//    @Property(
//            editing = Editing.DISABLED
//    )
//	public int getFloor() {
//		return floor;
//	}
//
//	public void setFloor(int floor) {
//		this.floor = floor;
//	}
//	
//	@javax.jdo.annotations.Column(allowsNull="false", length=30)
//    @Title(sequence="3")
//    @Property(
//            editing = Editing.DISABLED
//    )
//	public String getBox() {
//		return box;
//	}
//
//	public void setBox(String box) {
//		this.box = box;
//	}
//
//	@javax.jdo.annotations.Column(allowsNull="false")
//    @Title(sequence="4")
//    @Property(
//            editing = Editing.DISABLED
//    )
//	public int getSurface() {
//		return surface;
//	}
//
//	public void setSurface(int surface) {
//		this.surface = surface;
//	}
//
//	@javax.jdo.annotations.Column(allowsNull="false")
//    @Title(sequence="5")
//    @Property(
//            editing = Editing.DISABLED
//    )
//	public int getRooms() {
//		return rooms;
//	}
//
//	public void setRooms(int rooms) {
//		this.rooms = rooms;
//	}
//
//	@javax.jdo.annotations.Column(allowsNull="false")
//    @Title(sequence="6")
//    @Property(
//            editing = Editing.DISABLED
//    )
//	public boolean isElevator() {
//		return elevator;
//	}
//
//	public void setElevator(boolean elevator) {
//		this.elevator = elevator;
//	}
//
//	@javax.jdo.annotations.Column(allowsNull="false")
//    @Title(sequence="7")
//    @Property(
//            editing = Editing.DISABLED
//    )
//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}
//
//	public DomainObjectContainer getContainer() {
//		return container;
//	}
//
//	public void setContainer(DomainObjectContainer container) {
//		this.container = container;
//	}

	//region > updateName (action)

	public static class UpdateDescriptionDomainEvent extends ActionDomainEvent<Housing> {
        public UpdateDescriptionDomainEvent(final Housing source, final Identifier identifier, final Object... arguments) {
            super(source, identifier, arguments);
        }
    }

    @Action(
            domainEvent = UpdateDescriptionDomainEvent.class
    )
    public Housing updateDescription(
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "New description")
            final String description) {
        setDescription(description);
        return this;
    }

    public String default0UpdateDescription() {
        return getDescription();
    }

    //endregion

    //region > compareTo

    @Override
    public int compareTo(final Housing other) {
        return ObjectContracts.compare(this, other, "description");
    }

    //endregion

    //region > injected services

    @javax.inject.Inject
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    //endregion

}