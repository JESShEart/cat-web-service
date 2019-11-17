package com.example.demo.cat;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat getCat(int id) throws CatNotFoundException {
        Cat cat = catRepository.getCat(id);
        if (cat != null) {
            return cat;
        } else {
            throw new CatNotFoundException();
        }
    }

    public List<Cat> findCat(String name, String color) {
        if (name == null) {
            name = "";
        }
        if (color == null) {
            color = "";
        }
        return catRepository.findCat(name, color);
    }

    public Cat createCat(Cat cat) throws InvalidCatException {
        validate(cat);
        return catRepository.createCat(cat);
    }

    private void validate(Cat cat) throws InvalidCatException {
        if (cat == null) {
            throw new InvalidCatException("Cat must not be null!");
        }
        checkAge(cat);
        checkWeight(cat);
        checkColor(cat);
        checkName(cat);
    }

    private void checkAge(Cat cat) throws InvalidCatException {
        boolean valid = cat.getAge() != null
                && cat.getAge() >= 0
                && cat.getAge() < 30;
        if (!valid) {
            throw new InvalidCatException("Cat age must be >= 0 and < 30!");
        }
    }

    private void checkWeight(Cat cat) throws InvalidCatException {
        boolean valid = cat.getWeight() != null
                && cat.getWeight() > 0
                && cat.getWeight() <= 20;
        if (!valid) {
            throw new InvalidCatException("Cat weight must be > 0 and <= 20!");
        }
    }

    private void checkColor(Cat cat) throws InvalidCatException {
        boolean valid = cat.getColor() != null
                && cat.getColor().trim().length() > 0;
        if (!valid) {
            throw new InvalidCatException("Cat color is required!");
        }
    }

    private void checkName(Cat cat) throws InvalidCatException {
        if (cat.getName() == null) {
            throw new InvalidCatException("Cat name is required!");
        }

        if (catRepository.findCat(cat.getName(), "").size() > 0) {
            throw new InvalidCatException("Cat name must be unique!");
        }
    }

    public void deleteCat(int id) throws CatNotFoundException {
        if (catRepository.getCat(id) != null) {
            catRepository.deleteCat(id);
        } else {
            throw new CatNotFoundException();
        }
    }

}
