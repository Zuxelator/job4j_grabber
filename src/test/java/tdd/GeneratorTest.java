package tdd;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Test
    public void generatorTest() {
        Generator sg = new StringGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        String rsl = "I am a Petr Arsentev, Who are you? ";
        assertThat(rsl, is(sg.produce(template, map)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void noKeysException() {
        Generator sg = new StringGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("surname", "Petr Arsentev");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        String rsl = "I am a Petr Arsentev, Who are you? ";
    }

    @Test (expected = IllegalArgumentException.class)
    public void excessKeysException() {
        Generator sg = new StringGenerator();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("surname", "Petrov");
        map.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        String rsl = "I am a Petr Arsentev, Who are you? ";
    }
}
