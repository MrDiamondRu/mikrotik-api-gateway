package ru.rtk.service.mikrotikapigateway.model;

import lombok.Getter;

/**
 * Сообщение
 *
 * @author rnikonov
 */
@Getter
public class TextMessage {
    /**
     * Номер телефона
     */
    String[] phones;

    /**
     * Текст сообщения
     */
    String text;
}
