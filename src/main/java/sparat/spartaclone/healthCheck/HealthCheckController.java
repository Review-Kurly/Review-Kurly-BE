package sparat.spartaclone.healthCheck;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparat.spartaclone.common.ApiResponse;

@Tag(name = "health check")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health-check")
public class HealthCheckController {
    @GetMapping("/")
    @Operation(summary = "현재 instance가 제대로 동작하는 확인", description = "aws ec2 서비스를 위해 만듦")
    public ApiResponse<String> healthCheck() {
        return ApiResponse.successOf(HttpStatus.OK, "up");
    }
}
