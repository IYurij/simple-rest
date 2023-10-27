package yurij.study.controller;

import org.springframework.web.bind.annotation.*;
import yurij.study.exceptions.NotFoundExeption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base API controller class.
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
    private final List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First message."); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second message."); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third message."); }});
    }};

    /**
     * Get all messages.
     * @return messages list
     */
    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    /**
     * Get one message.
     * @param id - message String id
     * @return message Json object
     */
    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    /**
     * Create new message.
     * @param message - user input message
     * @return new created message
     */
    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));

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
    public Map<String, String> update(@PathVariable String id,  @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(message);
        messageFromDb.put("id", id);

        return messageFromDb;
    }

    /**
     * Delete message
     * @param id - message String id for delete
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }

    /**
     * Get one message by id
     * @param id - String id
     * @return - message object
     */
    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExeption::new);
    }
}
