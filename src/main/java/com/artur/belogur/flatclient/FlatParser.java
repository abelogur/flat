package com.artur.belogur.flatclient;

import com.artur.belogur.Flat;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FlatParser {

    private static final Predicate<Element> IS_FLAT = element -> element.attributes().hasKey("onclick");

    private final String html;

    @SneakyThrows
    public List<Flat> parseFlats() {
        Document document = Jsoup.parse(html);
        return document.getElementsByClass("b-perebor__row").stream()
                .filter(IS_FLAT)
                .map(this::convertFlat)
                .collect(Collectors.toList());
    }

    private Flat convertFlat(Element element) {
        int number = Integer.valueOf(element.child(0).html());
        int price = Integer.valueOf(element.child(4).html().replace(" ", ""));
        return new Flat(number, price);
    }
}
