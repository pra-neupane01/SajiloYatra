package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.response.TicketResponseDto;
import in.neuprakash.SajiloYatra.entity.Booking;
import in.neuprakash.SajiloYatra.entity.Payment;
import in.neuprakash.SajiloYatra.entity.Ticket;
import in.neuprakash.SajiloYatra.entity.enums.BookingStatusEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.TicketMapper;
import in.neuprakash.SajiloYatra.repository.BookingRepository;
import in.neuprakash.SajiloYatra.repository.PaymentRepository;
import in.neuprakash.SajiloYatra.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public TicketResponseDto generateTicket(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BusinessException("Booking not found"));

        if (booking.getBookingStatusEnum() != BookingStatusEnum.CONFIRMED) {
            throw new BusinessException("Ticket can be generated only for confirmed booking");
        }

        if (ticketRepository.existsByBookingId(bookingId)) {
            throw new BusinessException("Ticket already generated for this booking");
        }

        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new BusinessException("Payment not found for this booking"));

        Ticket ticket = Ticket.builder()
                .booking(booking)
                .fare(payment.getAmount())
                .issueDate(LocalDateTime.now())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        return TicketMapper.toResponse(savedTicket);
    }

    public List<TicketResponseDto> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(TicketMapper::toResponse)
                .toList();
    }

    public TicketResponseDto getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Ticket not found"));

        return TicketMapper.toResponse(ticket);
    }
}