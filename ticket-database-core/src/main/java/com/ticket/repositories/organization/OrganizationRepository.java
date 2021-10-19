package com.ticket.repositories.organization;

import com.ticket.entities.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganizationRepository  extends JpaRepository<Organization,Long> {

    @Query("select o from Organization o where o.isActive = true and o.name =:name")
    Optional<Organization> findByName(String name);

    @Query("select o from Organization o where o.isActive = true and o.id = :id")
    Optional<Organization> findByUserinfo_id(Long id);

    void deleteById(Long id);



}
