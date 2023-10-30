package yurij.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
//2. создадим пакет .dto, в т.к. негоже из контроллера возвращать бизнес-сущности, всё-таки это разные совершенно слои приложения;
//3. для маппинга DTO <-> Entity давай заюзаем отдельный тип сервисов - Mapper-ы, их разместим в .dto.mapping
//4. маппинги инжектим в контроллер и вызываем перед передачей результата
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}