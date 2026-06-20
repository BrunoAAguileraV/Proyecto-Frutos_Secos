package com.frutos.service_notification.service;

import com.frutos.service_notification.model.Notification;
import com.frutos.service_notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> listarTodas() {
        return notificationRepository.findAll();
    }

    public List<Notification> buscarPorCorreo(String correo) {
        return notificationRepository.findByCorreoDestino(correo);
    }

    public Notification registrarNotificacion(Notification notification) {
        // Aseguramos que la fecha sea la actual al momento de guardar
        notification.setFechaEnvio(LocalDateTime.now());
        return notificationRepository.save(notification);
    }
}