package in.neuprakash.SajiloYatra.controller;

import in.neuprakash.SajiloYatra.dto.response.APIResponse;
import in.neuprakash.SajiloYatra.dto.response.DashboardSummaryResponse;
import in.neuprakash.SajiloYatra.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;


    @GetMapping("/summary")
    public APIResponse<DashboardSummaryResponse> getSummary() {
        return APIResponse.<DashboardSummaryResponse>builder()
                .success(true)
                .message("Dashboard summary fetched successfully")
                .data(dashboardService.getDashboardSummary())
                .build();
    }
    /// //// test lines

}
