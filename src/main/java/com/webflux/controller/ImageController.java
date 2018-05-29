package com.webflux.controller;

import com.webflux.domain.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ImageController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ImageController.class);

    @GetMapping(value = API_BASEPATH + "/images")
    public Flux<Image> getImages() {
        return Flux.just(
                new Image("1", "learning-spring-boot-cover.jpg"),
                new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"),
                new Image("3", "bazinga.png")
        );
    }

    @PostMapping(value = API_BASEPATH + "/images")
    public Mono<?> createImage(@RequestBody Flux<Image> images) {
        return images.map(image -> {
            logger.info("Will save this image later");
            return image;
        }).then(Mono.empty());
    }

}
