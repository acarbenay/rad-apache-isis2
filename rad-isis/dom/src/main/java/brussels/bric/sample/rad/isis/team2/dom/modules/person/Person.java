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
package brussels.bric.sample.rad.isis.team2.dom.modules.person;

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

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "find", language = "JDOQL", value = "SELECT "
				+ "FROM brussels.bric.sample.rad.isis.team2.dom.modules.person.Person "),
		@javax.jdo.annotations.Query(name = "findByNameAndFirstname", language = "JDOQL", value = "SELECT "
				+ "FROM brussels.bric.sample.rad.isis.team2.dom.modules.person.Person "
				+ "WHERE name.indexOf(:name) >= 0 AND firstname.indexOf(:firstname)") })
@javax.jdo.annotations.Unique(name = "Person_UNQ", members = { "name","firstname" })
@DomainObject(objectType = "PERSON")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
public class Person implements Comparable<Person> {

	// region > identificatiom
	public TranslatableString title() {
		return TranslatableString.tr("Object: {name} {firstname}", "name",
				getName(), "firstname", getFirstname());
	}

	// endregion

	// region > name (property)

	private String name;
	private String firstname;

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@Title(sequence = "1")
	@Property(editing = Editing.DISABLED)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@Title(sequence = "1")
	@Property(editing = Editing.DISABLED)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// region > updateName (action)

	public static class UpdatePersonDomainEvent extends
			ActionDomainEvent<Person> {
		public UpdatePersonDomainEvent(final Person source,
				final Identifier identifier, final Object... arguments) {
			super(source, identifier, arguments);
		}
	}

	@Action(domainEvent = UpdatePersonDomainEvent.class)
	public Person updatePerson(
			@Parameter(maxLength = 100) @ParameterLayout(named = "New name") final String name,
			@Parameter(maxLength = 100) @ParameterLayout(named = "New firstname") final String firstname) {
		setName(name);
		setName(firstname);
		return this;
	}

	public String default0UpdatePerson() {
		return getName();
	}
	
	public String default1UpdatePerson() {
		return getFirstname();
	}

	// endregion

	// region > compareTo

	@Override
	public int compareTo(final Person other) {
		return ObjectContracts.compare(this, other, "name,firstname");
	}

	// endregion

	// region > injected services

	@javax.inject.Inject
	@SuppressWarnings("unused")
	private DomainObjectContainer container;

	// endregion

}
