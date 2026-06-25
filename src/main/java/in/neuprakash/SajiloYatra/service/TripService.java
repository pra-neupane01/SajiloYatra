package in.neuprakash.SajiloYatra.service;

import in.neuprakash.SajiloYatra.dto.request.PaginationRequest;
import in.neuprakash.SajiloYatra.dto.request.TripRequestDto;
import in.neuprakash.SajiloYatra.dto.request.TripSearchRequest;
import in.neuprakash.SajiloYatra.dto.response.PagedResponse;
import in.neuprakash.SajiloYatra.dto.response.TripResponseDto;
import in.neuprakash.SajiloYatra.entity.Bus;
import in.neuprakash.SajiloYatra.entity.Route;
import in.neuprakash.SajiloYatra.entity.Trip;
import in.neuprakash.SajiloYatra.entity.enums.BusStatusEnum;
import in.neuprakash.SajiloYatra.exception.BusinessException;
import in.neuprakash.SajiloYatra.mapper.TripMapper;
import in.neuprakash.SajiloYatra.repository.BusRepository;
import in.neuprakash.SajiloYatra.repository.RouteRepository;
import in.neuprakash.SajiloYatra.repository.TripRepository;
import in.neuprakash.SajiloYatra.specification.TripSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;

    public TripResponseDto saveTrip(TripRequestDto tripRequestDto) {
        Route route = routeRepository.findById(tripRequestDto.routeId())
                .orElseThrow(() -> new BusinessException("Route not found"));

        Bus bus = busRepository.findById(tripRequestDto.busId())
                .orElseThrow(() -> new BusinessException("Bus not found"));

        if (bus.getBusStatusEnum() != BusStatusEnum.AVAILABLE) {
            throw new BusinessException("Bus is not available for trip");
        }
        Trip trip = TripMapper.toEntity(tripRequestDto);
        trip.setRoute(getRouteById(tripRequestDto.routeId()));
        trip.setBus(getBusById(tripRequestDto.busId()));
        Trip savedTrip = tripRepository.save(trip);
        return TripMapper.toResponse(savedTrip);
    }

    public List<TripResponseDto> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(TripMapper::toResponse).toList();
    }

    public TripResponseDto getTripById(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new BusinessException("Trip not found with the provided id"));
        return TripMapper.toResponse(trip);
    }

    public TripResponseDto updateTrip(Long id, TripRequestDto tripRequestDto) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new BusinessException("Trip not found with the provided id"));

        if (tripRequestDto.tripDate() != null) {
            trip.setTripDate(tripRequestDto.tripDate());
        }
        if (tripRequestDto.departureTime() != null) {
            trip.setDepartureTime(tripRequestDto.departureTime());
        }
        if (tripRequestDto.arrivalTime() != null) {
            trip.setArrivalTime(tripRequestDto.arrivalTime());
        }
        if (tripRequestDto.tripStatusEnum() != null) {
            trip.setTripStatusEnum(tripRequestDto.tripStatusEnum());
        }
        if (tripRequestDto.routeId() != null) {
            trip.setRoute(getRouteById(tripRequestDto.routeId()));
        }
        if (tripRequestDto.busId() != null) {
            trip.setBus(getBusById(tripRequestDto.busId()));
        }

        return TripMapper.toResponse(tripRepository.save(trip));
    }

    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new BusinessException("Trip not found with the provided id"));
        tripRepository.deleteById(id);
    }

    private Route getRouteById(Long routeId) {
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new BusinessException("Route not found with the provided id"));
    }

    private Bus getBusById(Long busId) {
        return busRepository.findById(busId)
                .orElseThrow(() -> new BusinessException("Bus not found with the provided id"));
    }

    public PagedResponse<TripResponseDto> searchTrips(TripSearchRequest tripSearchRequest, PaginationRequest paginationRequest) {
        Pageable pageable = paginationRequest.toPageable();

        Specification<Trip> spec = Specification.allOf(
                TripSpecification.hasSource(tripSearchRequest.source()),
                TripSpecification.hasDestination(tripSearchRequest.destination()),
                TripSpecification.hasTripDate(tripSearchRequest.tripDate())
        );
        Page<Trip> tripPage = tripRepository.findAll(spec, pageable);
        Page<TripResponseDto> tripResponsePage = tripPage.map(TripMapper::toResponse);
        return PagedResponse.from(tripResponsePage);


    }
}
