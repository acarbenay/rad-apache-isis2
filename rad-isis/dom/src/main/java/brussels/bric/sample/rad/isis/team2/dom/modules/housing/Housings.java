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

import java.util.List;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(repositoryFor = Housing.class)
@DomainServiceLayout(menuOrder = "10")
public class Housings {

    //region > listAll (action)
    @Action(
             semantics = SemanticsOf.SAFE
    )
    @ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @MemberOrder(sequence = "1")
    public List<Housing> listAll() {
        return container.allInstances(Housing.class);
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
    public List<Housing> findByDescription(
            @ParameterLayout(named="Description")
            final String description
    ) {
        return container.allMatches(
                new QueryDefault<>(
                        Housing.class,
                        "findByDescription",
                        "description", description));
    }
    //endregion

    //region > create (action)
    @MemberOrder(sequence = "3")
    public Housing create(
            @Parameter(maxLength = 100)
            @ParameterLayout(named = "Description", multiLine=3)
            final String description,
            @Parameter(regexPattern = "^[0-9]+$")
            @ParameterLayout(named = "Floor")
            final Integer floor,
            @Parameter(maxLength = 10)
            @ParameterLayout(named = "Box")
            final String box,
            @Parameter(regexPattern = "^[0-9]+$")
            @ParameterLayout(named = "Surface")
            final Integer surface,
            @Parameter
            @ParameterLayout(named = "Price")
            final Double price,
            @Parameter(optionality=Optionality.OPTIONAL)
            @ParameterLayout(named = "Elevator")
            final Boolean elevator) {
        final Housing obj = container.newTransientInstance(Housing.class);
        obj.setDescription(description);
        obj.setPrice(price);
        obj.setElevator(elevator);
        obj.setSurface(surface);
        obj.setFloor(floor);
        obj.setBox(box);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > injected services

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion
}
