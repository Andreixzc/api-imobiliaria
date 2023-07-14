package com.example.Imobiliaria.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Imobiliaria.Model.Schedule;
import com.example.Imobiliaria.Repository.ScheduleRepository;

@Service
public class ScheduleService {

    static final LocalTime lt1 = LocalTime.of(8, 0);
    static final LocalTime lt2 = LocalTime.of(18, 0);
    static final String day1 = "SATURDAY";
    static final String day2 = "SUNDAY";

    //não deixar cadastrar em data passada
    @Autowired
    private ScheduleRepository scheduleRepository;
    public Schedule cadastraSchedule(Schedule schedule) {

       return scheduleRepository.save(schedule);
    }

    

    public boolean validateScheduleHour(LocalTime localTime) {
        if (localTime.compareTo(lt1) < 0 || localTime.compareTo(lt2) > 0) {
            return false;
        }
        return true;
    }

    public boolean existeRealEstate(UUID id){
        if (scheduleRepository.findByRealEstateId(id).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateScheduleDay(LocalDate localDate) {
        if (localDate.getDayOfWeek().name().equals(day1)
                || localDate.getDayOfWeek().name().equals(day2)) {
            return false;
        }
        return true;
    }

    public List<Schedule> listarAgendamentosPorImovel(UUID id){
        return scheduleRepository.findByRealEstateId(id);
    }
    public boolean validaConflito(UUID realEstateId, LocalDate date, LocalTime hour, UUID userId) {
        return scheduleRepository.countSchedulesByRealEstateAndDateTime(realEstateId, date, hour) == 0 &&
               scheduleRepository.countSchedulesByUserAndDateTime(userId, date, hour) == 0;
    }
    
}
