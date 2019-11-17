package com.example.demo.cat;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class CatRepository {
    private static final String NEXT_ID = "next_id";

    private final ConcurrentHashMap<String, Integer> values;
    private final ConcurrentHashMap<Integer, Cat> catDatabase;

    private static Cat copyCat(Cat cat) {
        return new Cat(cat.getId(), cat.getName(), cat.getColor(), cat.getAge(), cat.getWeight());
    }

    private static boolean startsWith(String source, String matches) {
        return source.toUpperCase().startsWith(matches.toUpperCase());
    }

    public CatRepository() {
        values = new ConcurrentHashMap<>();
        values.put(NEXT_ID, 1);

        catDatabase = new ConcurrentHashMap<>();
        Arrays.asList(
                new Cat(getNextId(), "Fluffy",  "White", 2, 10.5f),
                new Cat(getNextId(), "Nibbles",  "Orange", 8, 14.0f),
                new Cat(getNextId(), "Lucky",  "Black", 1, 8.5f)
        ).forEach(cat -> catDatabase.put(cat.getId(), cat));
    }

    private int getNextId() {
        int nextId = values.get(NEXT_ID);
        values.put(NEXT_ID, nextId + 1);
        return nextId;
    }

    public Cat getCat(int id) {
        Cat cat = catDatabase.get(id);
        if (cat != null) {
            return copyCat(cat);
        } else {
            return null;
        }
    }

    public List<Cat> findCat(String name, String color) {
        return catDatabase.values().stream()
                .filter(cat -> startsWith(cat.getName(), name))
                .filter(cat -> color.isEmpty() || cat.getColor().equalsIgnoreCase(color))
                .map(CatRepository::copyCat)
                .collect(Collectors.toList());
    }

    public Cat createCat(Cat cat) {
        cat.setId(getNextId());
        Cat copiedCat = CatRepository.copyCat(cat);
        catDatabase.put(copiedCat.getId(), copiedCat);
        return cat;
    }

    public void deleteCat(int id) {
        catDatabase.remove(id);
    }
}
