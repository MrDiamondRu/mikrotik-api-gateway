package ru.rtk.service.mikrotikapigateway.service;

import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Сервис отправки команды на Mikrotik
 *
 * @author rnikonov
 */
@Service
public class DataReceiveService {

    /**
     * ip адрес подключения Mikrotik
     */
    @Value("${settings.mikrotik-ip}")
    private String mikrotikIp;

    /**
     * Интерфейс подключения модема
     */
    @Value("${settings.mikrotik-interface}")
    private String mikrotikInterface;

    /**
     * Имя пользователя Mikrotik
     */
    @Value("${settings.mikrotik-user}")
    private String mikrotikUser;

    /**
     * Пароль Mikrotik
     */
    @Value("${settings.mikrotik-password}")
    private String mikrotikPassword;

    /**
     * Метод отправки команды на отправку СМС через API Mikrotik
     *
     * @param phone номер телефона
     * @param text  текст сообщения
     */
    public void receiveMsg(String phone, String text) throws MikrotikApiException {
        var con = ApiConnection.connect(mikrotikIp);
        con.login(mikrotikUser, mikrotikPassword);
        var message = "/tool/sms/send port=\"" + mikrotikInterface + "\" message=\"" + text + "\" phone-number=\"" + phone + "\"";
        System.out.println(message);
        con.execute(message);
        con.close();
    }

    /**
     * Метод запроса состояния lte соединения
     *
     * @return строка состояния соединения
     * @throws MikrotikApiException ошибка выполнения запроса
     */
    public String getLteStatus() throws MikrotikApiException {
        var con = ApiConnection.connect(mikrotikIp);
        con.login(mikrotikUser, mikrotikPassword);
        var message = "/interface/lte/info number=lte1 once";
        //.prplist=rssi,session-uptime
        System.out.println(message);
        var result = con.execute(message);
        con.close();

        return result.get(0).toString();
    }

}
