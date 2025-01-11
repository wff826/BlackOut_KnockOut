package ephemint.com.ddosProtector.front;

import ephemint.com.ddosProtector.company.service.WafLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/front")
@RequiredArgsConstructor
public class ClientController {

    private final WafLogService wafLogService;


    //api/front/proxy-ip?ipAddr={}
    @GetMapping("/proxy-ip")
    public ResponseEntity<?> getProxyIpAddress(@RequestParam("ipAddr") String original){
        String foundProxyIp = wafLogService.findProxyIpOfServer(original);

        return ResponseEntity.ok(foundProxyIp);
    }


}
