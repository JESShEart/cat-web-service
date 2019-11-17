package com.example.demo.cat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CatController {
    private Logger logger = LoggerFactory.getLogger(CatController.class);

    private CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/cat/{id}")
    @Secured("ROLE_CAT_USER")
    public Cat getCat(
            @PathVariable("id") int id,
            HttpServletRequest request
    ) {
        logger.info("{} GET /cat/{}",
                request.getRemoteAddr(), id);
        try {
            return catService.getCat(id);
        } catch (CatNotFoundException cnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cat Not Found",
                    cnfe
            );
        }
    }

    @GetMapping("/cat")
    @Secured("ROLE_CAT_USER")
    public List<Cat> getCats(
            @Nullable @RequestParam String name,
            @Nullable @RequestParam String color,
            HttpServletRequest request
    ) {
        logger.info("{} GET /cat?name={}&color={}",
                request.getRemoteAddr(), name, color);
        return catService.findCat(name, color);
    }

    @PostMapping("/cat")
    @Secured("ROLE_CAT_USER")
    public Cat createCat(
            @RequestBody Cat cat,
            HttpServletRequest request
    ) {
        logger.info("{} POST /cat name={}, color={}, age={}, and weight={}",
                request.getRemoteAddr(), cat.getName(), cat.getColor(), cat.getAge(), cat.getWeight());
        try {
            return catService.createCat(cat);
        } catch (InvalidCatException ice) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    ice.getMessage(),
                    ice
            );
        }
    }

    @DeleteMapping("/cat/{id}")
    @Secured("ROLE_CAT_ADMIN")
    public void deleteCat(
            @PathVariable("id") int id,
            HttpServletRequest request
    ) {
        logger.info("{} DELETE /cat/{}",  request.getRemoteAddr(), id);
        try {
            catService.deleteCat(id);
        } catch (CatNotFoundException cnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cat Not Found",
                    cnfe
            );
        }
    }
}
