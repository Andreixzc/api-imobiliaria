package com.example.Imobiliaria.Controller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Imobiliaria.Model.Schedule;
import com.example.Imobiliaria.Service.ScheduleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("schedule")
public class ScheduleController {



    @Autowired
    private ScheduleService scheduleService;

    @PostMapping()
    public ResponseEntity<?> cadastraSchedule(@RequestBody @Valid Schedule schedule) {
        //passar id pelo token.
        //retorno bugado
        if (!scheduleService.validateScheduleDay(schedule.getDate())) {
            return ResponseEntity.badRequest().body("Data inválida!");
        }

        if (!scheduleService.validateScheduleHour(schedule.getHour())) {
            return ResponseEntity.badRequest().body("Horário inválido!");
        }

        if (!scheduleService.validaConflito(schedule.getRealEstate().getId(), schedule.getDate(),
                schedule.getHour(), schedule.getUser().getId())) {
            return ResponseEntity.badRequest().body("Conflito de agendamento!");
        }

        return ResponseEntity.ok(scheduleService.cadastraSchedule(schedule));
    }

    //validar se o token é valido
    @GetMapping("/{id}")
    public ResponseEntity<?> listaAgendamentosPorImovel(@PathVariable UUID id) {

        if (scheduleService.existeRealEstate(id)) {
            return ResponseEntity.ok(scheduleService.listarAgendamentosPorImovel(id));
        }
        return ResponseEntity.badRequest().body("Real estate não encontrada");
    }



}
