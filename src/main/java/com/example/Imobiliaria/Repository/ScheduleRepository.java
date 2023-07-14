package com.example.Imobiliaria.Repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.Imobiliaria.Model.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule,UUID> {
    List<Schedule> findByRealEstateId(UUID id);
    @Query(value = "SELECT COUNT(*) FROM schedules WHERE (real_estate_id = :realEstateId) AND (date = :date AND hour = :hour)", nativeQuery = true)
    int countSchedulesByRealEstateAndDateTime(@Param("realEstateId") UUID realEstateId, @Param("date") LocalDate date, @Param("hour") LocalTime hour);
    @Query(value = "SELECT COUNT(*) FROM schedules WHERE user_id = :userId AND date = :date AND hour = :hour", nativeQuery = true)
    int countSchedulesByUserAndDateTime(@Param("userId") UUID userId, @Param("date") LocalDate date, @Param("hour") LocalTime hour);
}
