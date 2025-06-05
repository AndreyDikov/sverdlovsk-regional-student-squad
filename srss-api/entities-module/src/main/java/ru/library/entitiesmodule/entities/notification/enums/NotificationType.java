package ru.library.entitiesmodule.entities.notification.enums;

public enum NotificationType {
    /**
     * Приглашение в отряд
     * (Invite to squad)
     * "___ приглашает вас в отряд ___"
     */
    INVITE_TO_SQUAD,

    /**
     * Заявка на вступление
     * (Application to join)
     * "___ хочет вступить в отряд ___"
     */
    REQUEST_TO_JOIN,

    /**
     * Отказ от приглашения в отряд
     * (Refusal of the squad invitation)
     * "___ отказался от приглашения в ваш отряд"
     */
    REFUSE_INVITE_TO_SQUAD,

    /**
     * Согласие на вступление в отряд
     * (Agreement to join the squad)
     * "___ согласился вступить в ваш отряд"
     */
    AGREE_TO_JOIN_SQUAD,

    /**
     * Одобрение заявки на вступление в отряд
     * (Approval of the request to join the squad)
     * "вас приняли в отряд ___"
     */
    APPROVE_REQUEST_TO_JOIN_SQUAD,

    /**
     * Отклонение заявки на вступление в отряд
     * (Rejection of the request to join the squad)
     * "вас не приняли в отряд ___"
     */
    REJECT_REQUEST_TO_JOIN_SQUAD,

    /**
     * Запись на мероприятие
     * (Sign up for the event)
     * "___ записал вас на мероприятие ___"
     */
    SIGN_UP_FOR_EVENT,

    /**
     * Оценка за мероприятие
     * (Rating for the event)
     * "___ оценил вас за мероприятие ___: ___"
     */
    RATE_EVENT
}
