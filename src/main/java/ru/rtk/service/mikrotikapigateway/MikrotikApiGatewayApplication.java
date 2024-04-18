package ru.rtk.service.mikrotikapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс для запуска сервиса.
 *
 * @author rnikonov
 */
@SpringBootApplication
public class MikrotikApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MikrotikApiGatewayApplication.class, args);
    }

}
