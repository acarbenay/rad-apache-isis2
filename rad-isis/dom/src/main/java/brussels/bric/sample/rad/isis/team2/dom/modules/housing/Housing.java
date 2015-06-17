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
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.Identifier;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.apache.isis.applib.util.ObjectContracts;

import java.util.List;

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
        objectType = "HOUSING"
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
    private int floor;
    private String box;
    private int surface;
    private Boolean elevator;
    private double price;

//    @Persistent(mappedBy = "housing")
//    private List<Room> rooms;


    @javax.jdo.annotations.Column(allowsNull="false", length = 100)
    @MemberOrder(sequence="0")
    @Property(
            editing = Editing.DISABLED
    )
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @javax.jdo.annotations.Column(allowsNull="false")
    @MemberOrder(sequence="1")
    @Property(
            editing = Editing.DISABLED
    )
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }


    @javax.jdo.annotations.Column(allowsNull="false", length=30)
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
    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    @javax.jdo.annotations.Column(allowsNull="false")
    @MemberOrder(sequence="4")
    @Property(
            editing = Editing.DISABLED
    )
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @javax.jdo.annotations.Column(allowsNull="true")
    @MemberOrder(sequence="5")
    @Property(
            editing = Editing.DISABLED
    )
    public Boolean getElevator() {
        return elevator;
    }

    public void setElevator(Boolean elevator) {
        this.elevator = elevator;
    }


    //region > updateName (action)

    public static class UpdateHousingDomainEvent extends ActionDomainEvent<Housing> {
        public UpdateHousingDomainEvent(final Housing source, final Identifier identifier, final Object... arguments) {
            super(source, identifier, arguments);
        }
    }

    @Action(
            domainEvent = UpdateHousingDomainEvent.class
    )
    public Housing updateHousing(
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "New description", multiLine=3)
            final String description,
            @Parameter(regexPattern = "^[0-9]+$")
            @ParameterLayout(named = "New Floor value")
            final Integer floor,
            @Parameter(maxLength = 10)
            @ParameterLayout(named = "New box value")
            final String box,
            @Parameter(regexPattern = "^[0-9]+$")
            @ParameterLayout(named = "New Surface value")
            final Integer surface,
            @Parameter(regexPattern = "^[0-9]+$")
            @ParameterLayout(named = "New Price value")
            final Double price,
            @Parameter(optionality=Optionality.OPTIONAL)
            @ParameterLayout(named = "New Elevator value")
            final Boolean elevator
    ) {
        setDescription(description);
        setFloor(floor);
        setBox(box);
        setSurface(surface);
        setElevator(elevator);
        setPrice(price);
        return this;
    }

    public String default0UpdateHousing() {
        return getDescription();
    }

    public Integer default1UpdateHousing() {
        return getFloor();
    }

    public String default2UpdateHousing() {
        return getBox();
    }

    public Integer default3UpdateHousing() {
        return getSurface();
    }

    public Double default4UpdateHousing() {
        return getPrice();
    }

    public Boolean  default5UpdateHousing() {
        return getElevator();
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
