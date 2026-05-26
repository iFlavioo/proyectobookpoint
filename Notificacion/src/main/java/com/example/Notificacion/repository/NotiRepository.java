package com.example.Notificacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Notificacion.model.Noti;

@Repository
public interface NotiRepository extends JpaRepository<Noti,Long> {
    
}
