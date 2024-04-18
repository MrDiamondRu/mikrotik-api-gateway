package ru.rtk.service.mikrotikapigateway.rest;

import lombok.RequiredArgsConstructor;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.rtk.service.mikrotikapigateway.service.DataReceiveService;

/**
 * REST контроллер
 *
 * @author rnikonov
 */
@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final DataReceiveService dataReceiveService;

    @PostMapping("/send")
    private String postMsg(@RequestBody String message) {
        var info = message.split("#");
        if (info.length == 2) {
            try {
                dataReceiveService.receiveMsg(info[0], info[1]);
            } catch (MikrotikApiException e) {
                System.out.println("ERROR\n" + e.getMessage());
                return "ERROR";
            }
        }
        return "OK";
    }

    @GetMapping("/status")
    private String getStatus() {
        try {
            return dataReceiveService.getLteStatus();
        } catch (MikrotikApiException e) {
            System.out.println("ERROR\n" + e.getMessage());
            return "ERROR";
        }
    }
}
