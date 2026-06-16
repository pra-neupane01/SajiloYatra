package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.response.TicketResponseDto;
import in.neuprakash.SajiloYatra.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public List<TicketResponseDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDto getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
}
