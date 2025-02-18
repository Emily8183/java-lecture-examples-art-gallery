/*
1) 在Parameter添加Model model
2）去掉@ResponseBody，因为return的是thymeleaf的templates
3）addAttribute(variableName, value) to assign a value to the template variable

 */

package org.launchcode.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/artworks")
//Do not use the @ResponseBody annotation or the template file name will just appear as text on the page!
public class ArtworkController {

    private static int nextId = 6;

    private static final Map<Integer, String> artworks = new HashMap<>() {{
        put(1, "Girl with a Pearl Earring");
        put(2, "Mona Lisa");
        put(3, "The Birth of Venus");
        put(4, "The Persistence of Memory");
        put(5, "The Starry Night");
    }};

    // Corresponds to http://localhost:8080/artworks
    @GetMapping("")
    public String renderArtworksPage(Model model) {
        List<String> artworkList = new ArrayList<>(artworks.values());
        model.addAttribute("artworkList", artworkList); //.addAttribute(variableName, value) to assign a value to the template variable
        return "artworks/index";
    }

    // Corresponds to http://localhost:8080/artworks/add
    @GetMapping("/add")
    public String renderAddArtForm() {
        return "artworks/add";
    }

    // Corresponds to http://localhost:8080/artworks/add?artwork=someArtworkTitle
    @PostMapping("/add")
    public String processAddArtForm(@RequestParam String artwork) {
        artworks.put(nextId, artwork);
        nextId++;
        return "redirect:/artworks";
    }
}
