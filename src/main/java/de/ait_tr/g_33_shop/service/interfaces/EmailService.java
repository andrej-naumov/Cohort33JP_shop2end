package de.ait_tr.g_33_shop.service.interfaces;

import de.ait_tr.g_33_shop.domain.entity.User;

public interface EmailService {

    void sendConfirmationEmail(User user);
}