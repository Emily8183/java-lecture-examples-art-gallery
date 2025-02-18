package org.launchcode.artgallery.controllers;

import org.launchcode.artgallery.data.ArtworksData;
import org.launchcode.artgallery.models.Artwork;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artworks")
public class ArtworkController {

    // Corresponds to http://localhost:8080/artworks
    @GetMapping("")
    public String renderArtworksPage(Model model) {
        model.addAttribute("artworkList", ArtworksData.getAll());
        return "artworks/index";
    }

    // Corresponds to http://localhost:8080/artworks/add
    @GetMapping("/add")
    public String renderAddArtForm() {
        return "artworks/add";
    }

    // Corresponds to http://localhost:8080/artworks/add?title=SomeTitle&artist=SomeArtist
    // The @ModelAttribute annotation allows you to simply receive an object of the class, and Spring Boot automatically pulls in all the individual parameter values and applies them to the object
    @PostMapping("/add")
    public String processAddArtForm(@ModelAttribute Artwork artwork) {
        ArtworksData.add(artwork);
        return "redirect:/artworks";
        //增加一个artwork后，ArtworksData为true，因此redirect:/artworks，显示有内容
    }

    // Corresponds to http://localhost:8080/artworks/delete
    @GetMapping("/delete")
    public String renderDeleteArtForm(Model model) {
        model.addAttribute("artworkList", ArtworksData.getAll());
        return "artworks/delete";
    }

    // Corresponds to http://localhost:8080/artworks/delete?artworkIds=1&artworkIds=2 (etc)
    // 这里用到@RequestParam所以“artworkIds=1” 等号的左边是artworkIds
    // If the parameter is optional and the client doesn't provide it, can set a default value:  @RequestParam(defaultValue = "Guest")
    // If a request parameter is not always required, set required = false： （@RequestParam(required = false) String query）
    @PostMapping("/delete")
    public String processDeleteArtForm(@RequestParam(required = false) List<Integer> artworkIds) {
        // (required = false)，如果用户什么也没有选的话，不会报错
        // 但是这里仍然会报错，If no checkboxes are selected, Spring does not send artworkIds at all, and since int[] cannot be null, the application throws an error.
        // so I changed the artworrkIds' type from int[] to List<Integer>
        if (artworkIds != null && !artworkIds.isEmpty()) {
            for (int id : artworkIds) {
                ArtworksData.remove(id);
            }
        }
        return "redirect:/artworks";
    }

}
