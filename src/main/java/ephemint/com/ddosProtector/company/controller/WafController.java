package ephemint.com.ddosProtector.company.controller;

import ephemint.com.ddosProtector.company.dto.WafLogDto;
import ephemint.com.ddosProtector.company.proxy.ProxyService;
import ephemint.com.ddosProtector.company.service.WafLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/waf")
public class WafController {

    private final WafLogService wafLogService;
    private final ProxyService proxyService;


    @PostMapping("/logs")
    public ResponseEntity<String> processLog(@RequestBody WafLogDto wafLogDto, HttpServletRequest httpServletRequest){
        //DB 저장하기
        String savedIp = wafLogService.save(wafLogDto);

        //블록체인에 박제
        // mining한 결과를 다시 조회해서 DB에 저장

        //Ip 주소 변경
        //현재 이 함수 호출한 ip 추적
        String requestedIp = httpServletRequest.getRemoteAddr();
        String changedIp = proxyService.requestProxyChange();
        wafLogService.createServerIp(requestedIp,changedIp);

        return ResponseEntity.ok("Log for IP ["+savedIp+"] processed");
    }



}
