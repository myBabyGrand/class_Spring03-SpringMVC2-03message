package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage(){
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕하세요");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = messageSource.getMessage("no_code", null, "default Message", null);
        assertThat(result).isEqualTo("default Message");
    }

    @Test
    void argumentMessage(){
        String result = messageSource.getMessage("hello.name", new Object[]{"Major TOM"}, null);
        assertThat(result).isEqualTo("안녕하세요 Major TOM님");
    }

    @Test
    void defaultLang(){
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕하세요");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕하세요");
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("Hello");
    }
}
