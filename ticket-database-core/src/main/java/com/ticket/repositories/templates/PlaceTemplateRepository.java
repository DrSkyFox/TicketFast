package com.ticket.repositories.templates;

import com.ticket.entities.organization.Organization;
import com.ticket.entities.templates.PlaceTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaceTemplateRepository extends JpaRepository<PlaceTemplate, Long> {

    Optional<PlaceTemplate> findByOrganizationAndIsActiveTrue(Organization organization);

    Optional<PlaceTemplate> findByOrganizationIdAndIsActiveTrue(Long organizationId);

    List<PlaceTemplate> findAllByIsActiveTrue();

    List<PlaceTemplate> findAll();

    @Query("select p from PlaceTemplate p " +
            "where " +
            "p.longitude between (:longitude - :offset) and (:longitude + :offset) "+
            "and p.latitude between (:latitude - :offset) and (:latitude + :offset)" +
            "and p.isActive = true")
    List<PlaceTemplate> findAllAroundGPSPosition(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("offset") double offset);


}
