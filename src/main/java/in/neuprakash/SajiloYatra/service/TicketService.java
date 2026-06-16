package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.response.TicketResponseDto;
import in.neuprakash.SajiloYatra.entity.Ticket;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.TicketMapper;
import in.neuprakash.SajiloYatra.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<TicketResponseDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketMapper::toResponse).toList();
    }

    public TicketResponseDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new BusinessException("Ticket not found"));
        return TicketMapper.toResponse(ticket);
    }


}
