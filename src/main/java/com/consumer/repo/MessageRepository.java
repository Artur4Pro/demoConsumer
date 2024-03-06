package com.consumer.repo;


import com.consumer.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
}

