package com.finale.chat.repository;


import com.finale.chat.entity.ChatMessage;
import com.finale.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomOrderByTimestampAsc(ChatRoom chatRoom);

    void deleteByChatRoom(ChatRoom chatRoom);
}