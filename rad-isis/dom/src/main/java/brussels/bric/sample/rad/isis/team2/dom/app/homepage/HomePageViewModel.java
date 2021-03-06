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
package brussels.bric.sample.rad.isis.team2.dom.app.homepage;

import java.util.List;

import org.apache.isis.applib.annotation.ViewModel;

import brussels.bric.sample.rad.isis.team2.dom.modules.person.Person;
import brussels.bric.sample.rad.isis.team2.dom.modules.person.Persons;

@ViewModel
public class HomePageViewModel {

    //region > title
    public String title() {
        return getPersons().size() + " persons";
    }
    //endregion

    //region > object (collection)
    @org.apache.isis.applib.annotation.HomePage
    public List<Person> getPersons() {
        return persons.listAll();
    }
    //endregion

    //region > injected services

    @javax.inject.Inject
    Persons persons;

    //endregion
}
