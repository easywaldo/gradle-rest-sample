package sample;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    protected static List<Greeting> greetingList = new ArrayList<>();

    @GetMapping
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(
            value="name",
            defaultValue="World") String name) {
        Greeting result = new Greeting(counter.incrementAndGet(),
                String.format(template, name));
        return result;
    }

    @GetMapping
    @RequestMapping("/add")
    public void Add(
            @RequestParam(value="id") Long id,
            @RequestParam(value="name") String name) {
        greetingList.add(
                new Greeting(id, String.format(template, name)));
    }

    @GetMapping
    @RequestMapping("/search")
    public Greeting Search(@RequestParam(
            value="id") long id) {

        return greetingList.stream()
                .filter(x -> x.getId() == id)
                .findFirst().get();

    }
}