package com.neidev.eventsms.repository;

import com.neidev.eventsms.domain.Event;
import feign.Param;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query(value = "SELECT * FROM TB_EVENT e WHERE PARSEDATETIME(e.date AS 'dd/MM/yyyy') > :currentDate",
            nativeQuery = true)
    List<Event> findUpcomingEvents(@Param("currentDate")LocalDateTime currentDate);

    @Nonnull
    Optional<Event> findById(@Nonnull String id);
}
