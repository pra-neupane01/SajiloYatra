package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.PaymentRequestDto;
import in.neuprakash.SajiloYatra.dto.response.TicketResponseDto;
import in.neuprakash.SajiloYatra.entity.Booking;
import in.neuprakash.SajiloYatra.entity.Ticket;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.TicketMapper;
import in.neuprakash.SajiloYatra.repository.BookingRepository;
import in.neuprakash.SajiloYatra.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;


    public TicketResponseDto generateTicket(PaymentRequestDto paymentRequestDto) {
        Booking booking = bookingRepository.findById(paymentRequestDto.bookingId()).orElseThrow(() -> new BusinessException("Booking not found"));

        if (booking.getBookingStatusEnum() != (BookingStatusEnum.CONFIRMED)) {
            throw new BusinessException("Booking not confirmed");
        }

        
    }

    public List<TicketResponseDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketMapper::toResponse).toList();
    }

    public TicketResponseDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new BusinessException("Ticket not found"));
        return TicketMapper.toResponse(ticket);
    }


}
