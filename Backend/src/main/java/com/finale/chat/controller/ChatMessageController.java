package com.finale.chat.controller;

import com.finale.chat.entity.ChatMessage;
import com.finale.chat.entity.ChatRoom;
import com.finale.chat.service.ChatMessageService;
import com.finale.chat.service.ChatRoomService;
import com.finale.chat.util.ImageStorageUtil;
import com.finale.global.ApiResponse.ApiResponse;
import com.finale.global.ApiResponse.SuccessCode;
import com.finale.global.exception.chat.ChatRoomIdNotFoundException;
import com.finale.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/chat")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;
    private final UserService userService;
    private final ImageStorageUtil imageStorageUtil;

    @GetMapping("/messages")
    public ResponseEntity<ApiResponse<List<ChatMessage>>> getAllMessages() {
        List<ChatMessage> messages = chatMessageService.getAllMessages();
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK, messages));
    }

    @GetMapping("/rooms/{chatRoomId}/messages")
    public ResponseEntity<ApiResponse<List<ChatMessage>>> getMessagesByChatRoom(@PathVariable Long chatRoomId) {
        ChatRoom chatRoom = chatRoomService.findById(chatRoomId)
                .orElseThrow(() -> new ChatRoomIdNotFoundException(chatRoomId));

        List<ChatMessage> messages = chatMessageService.getMessagesByChatRoom(chatRoom);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK, messages));
    }

    @PostMapping("/image/convert")
    public ResponseEntity<ApiResponse<byte[]>> convertImageUrlToBase64(@RequestParam String imagePath) throws IOException {
        byte[] imageBytes = chatMessageService.convertImageUrlToBytes(imagePath);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/jpeg");

        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK, imageBytes));
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMessage(@PathVariable Long id) {
        chatMessageService.deleteMessage(id);
        return ResponseEntity.ok(ApiResponse.success(SuccessCode.OK));
    }
}

