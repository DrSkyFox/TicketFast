package com.ticket.repositories.special;

import com.ticket.entities.special.reference.AddressRegRef;
import com.ticket.enums.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressRegRefRepository extends JpaRepository<AddressRegRef, Long> {

    List<AddressRegRef> findAll();

    @Query("select a from AddressRegRef a where a.organization = :id and a.isActive = true")
    Optional<AddressRegRef> findByOrganizationId(@Param("id") Long organization);

    @Query("select a from AddressRegRef a where a.placeTemplate = :id and a.isActive = true")
    Optional<AddressRegRef> findByPlaceTemplateId(@Param("id") Long placeTemplateId);

    @Query("select a from AddressRegRef a where a.addressType = :type and a.isActive = true")
    List<AddressRegRef> findByAddressType(@Param("type")AddressType addressType);




}
