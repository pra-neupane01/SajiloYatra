package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.TicketResponseDto;
import in.neuprakash.SajiloYatra.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/generate/{bookingId}")
    public APIResponse<TicketResponseDto> generateTicket(@PathVariable Long bookingId) {
        return APIResponse.<TicketResponseDto>builder()
                .status(true)
                .message("Ticket generated successfully")
                .data(ticketService.generateTicket(bookingId))
                .build();
    }

    @GetMapping
    public List<TicketResponseDto> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public TicketResponseDto getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }
}