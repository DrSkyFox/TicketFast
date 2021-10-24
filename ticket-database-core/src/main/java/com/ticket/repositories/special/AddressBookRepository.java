package com.ticket.repositories.special;

import com.ticket.entities.organization.Organization;
import com.ticket.entities.special.AddressBook;
import com.ticket.entities.templates.PlaceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    Optional<AddressBook> findByIdAndIsActiveTrue(Long id);

    Optional<AddressBook> findAddressBookByAddressRegRefOrganizationId(Long id);

    Optional<AddressBook> findAddressBookByAddressRegRefOrganization(Organization o);

    Optional<AddressBook> findAddressBookByAddressRegRefPlaceTemplateId(Long id);

    Optional<AddressBook> findAddressBookByAddressRegRefPlaceTemplateId(PlaceTemplate placeTemplate);


}
