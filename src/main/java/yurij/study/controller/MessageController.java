package yurij.study.controller;

import org.springframework.web.bind.annotation.*;
import yurij.study.entity.Message;
import yurij.study.exceptions.NotFoundExeption;

import java.util.ArrayList;
import java.util.List;

/**
 * API message controller class.
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * Counter field for work with data stub.
     */
    private int counter = 4;
    /**
     * Base data stub.
     */
    private final List<Message> messages = new ArrayList<>() {{
        add(new Message("1", "First message."));
        add(new Message("2", "Second message."));
        add(new Message("3", "Third message."));
    }};

    /**
     * Get all messages.
     * @return messages list
     */
    @GetMapping
    public List<Message> list() {
        return messages;
    }

    /**
     * Get one message.
     * @param id - message String id
     * @return message Json object
     */
    @GetMapping("{id}")
    public Message getOne(@PathVariable String id) {
        return getMessage(id);
    }

    /**
     * Create new message.
     * @param message - user input message
     * @return new created message
     */
    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setId(String.valueOf(counter++));

        messages.add(message);

        return message;
    }

    /**
     * Update message
     * @param id - message String id
     * @param message - user input message
     * @return - updated message
     */
    @PutMapping("{id}")
    public Message update(@PathVariable String id,  @RequestBody Message message) {
        Message messageFromDb = getMessage(id);

        messageFromDb.setText(message.getText());
        messageFromDb.setId(id);

        return messageFromDb;
    }

    /**
     * Delete message
     * @param id - message String id for delete
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Message message = getMessage(id);

        messages.remove(message);
    }

    /**
     * Get one message by id
     * @param inputId - String id
     * @return - message object
     */
    private Message getMessage(String inputId) {
        return messages.stream()
                .filter(message -> message.getId().equals(inputId))
                .findFirst()
                .orElseThrow(NotFoundExeption::new);
    }
}
