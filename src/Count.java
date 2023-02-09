import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class Count {
    //Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем частоту
    // появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.
    public static void main(String[] args) throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        Map<String, Long> m = buf.lines().map(String::toLowerCase)
                .map(sc ->sc.split("[^a-zA-Zа-яА-Я0-9']+"))
                .flatMap(Arrays::stream)
                .collect(groupingBy(identity(), counting()));

        Map<String, Long> result = new LinkedHashMap<>();
        m.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(10)
                        .forEach(e -> result.put(e.getKey(), e.getValue()));

        for (Map.Entry<String, Long> entry : result.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
