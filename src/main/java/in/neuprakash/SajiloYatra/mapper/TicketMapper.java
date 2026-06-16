package in.neuprakash.SajiloYatra.mapper;

import in.neuprakash.SajiloYatra.dto.response.TicketResponseDto;
import in.neuprakash.SajiloYatra.entity.Ticket;

public class TicketMapper {
    public static TicketResponseDto toResponse(Ticket ticket) {
        return TicketResponseDto.builder()
                .id(ticket.getId())
                .ticketNumber(ticket.getTicketNumber())
                .fare(ticket.getFare())
                .issueDate(ticket.getIssueDate())
                .build();
    }
}
