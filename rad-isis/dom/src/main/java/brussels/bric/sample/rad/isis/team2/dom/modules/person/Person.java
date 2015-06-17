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
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Optionality;
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
		@javax.jdo.annotations.Query(name = "findByName", language = "JDOQL", value = "SELECT "
				+ "FROM brussels.bric.sample.rad.isis.team2.dom.modules.person.Person "
				+ "WHERE name.indexOf(:name) >= 0") })
@javax.jdo.annotations.Unique(name = "Person_UNQ", members = { "name",
		"firstname" })
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
	private String title;
	private String email;
	private String phone;
	private String bankAccount;

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@MemberOrder(sequence = "0")
	@Property(editing = Editing.DISABLED)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@MemberOrder(sequence = "1")
	@Property(editing = Editing.DISABLED)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// region > updateName (action)

	@javax.jdo.annotations.Column(allowsNull = "true", length = 100)
	@MemberOrder(sequence = "2")
	@Property(editing = Editing.DISABLED)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@MemberOrder(sequence = "3")
	@Property(editing = Editing.DISABLED)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@MemberOrder(sequence = "4")
	@Property(editing = Editing.DISABLED)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@javax.jdo.annotations.Column(allowsNull = "false", length = 100)
	@MemberOrder(sequence = "5")
	@Property(editing = Editing.DISABLED)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public static class UpdatePersonDomainEvent extends
			ActionDomainEvent<Person> {
		public UpdatePersonDomainEvent(final Person source,
				final Identifier identifier, final Object... arguments) {
			super(source, identifier, arguments);
		}
	}

	public static class UpdateContactDomainEvent extends
			ActionDomainEvent<Person> {
		public UpdateContactDomainEvent(final Person source,
				final Identifier identifier, final Object... arguments) {
			super(source, identifier, arguments);
		}
	}

	public static class UpdateBankAccountDomainEvent extends
			ActionDomainEvent<Person> {
		public UpdateBankAccountDomainEvent(final Person source,
				final Identifier identifier, final Object... arguments) {
			super(source, identifier, arguments);
		}
	}

	@Action(domainEvent = UpdatePersonDomainEvent.class)
	@MemberOrder(sequence = "1")
	public Person updatePerson(
			@Parameter(maxLength = 100) @ParameterLayout(named = "New name") final String name,
			@Parameter(maxLength = 100) @ParameterLayout(named = "New firstname") final String firstname,
			@Parameter(maxLength = 100, optionality=Optionality.OPTIONAL) @ParameterLayout(named = "New title") final String title) {
		setName(name);
		setFirstname(firstname);
		setTitle(title);
		return this;
	}

	@Action(domainEvent = UpdateContactDomainEvent.class)
	@MemberOrder(sequence = "2")
	public Person updateContact(
			@Parameter(maxLength = 100) @ParameterLayout(named = "New email") final String email,
			@Parameter(maxLength = 100) @ParameterLayout(named = "New phone") final String phone) {
		setEmail(email);
		setPhone(phone);
		return this;
	}

	@Action(domainEvent = UpdateBankAccountDomainEvent.class)
	@MemberOrder(sequence = "3")
	public Person updateBankAccount(
			@Parameter(maxLength = 100) @ParameterLayout(named = "New bankAccount") final String bankAccount) {
		setBankAccount(bankAccount);
		return this;
	}

	public String default0UpdatePerson() {
		return getName();
	}

	public String default1UpdatePerson() {
		return getFirstname();
	}
	
	public String default2UpdatePerson() {
		return getTitle();
	}
	
	public String default0UpdateContact() {
		return getEmail();
	}
	
	public String default1UpdateContact() {
		return getPhone();
	}
	
	public String default0UpdateBankAccount() {
		return getBankAccount();
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
