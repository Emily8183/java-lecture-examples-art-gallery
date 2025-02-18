/*Class 8:
概述：
1）Query Parameter: key-value pair, Path Varialbe: int id => http://localhost:8080/artworks/details/6
2) Annotation: @Controller, @RequestMapping, @GetMapping, @PostMapping, @ResponseBody
3）form新提交的内容，attribute name需要和query parameter呼应
4）
 */

package org.launchcode.artgallery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// TODO fix bugs

@Controller
@RequestMapping("/artworks")
@ResponseBody // can add at class level since all methods require this
//它是后端 API 的响应机制，决定了服务器返回什么数据给前端。

public class ArtworkController {

    private static int nextId = 6;

    private static final Map<Integer, String> artworks = new HashMap<>() {{
        put(1, "Girl with a Pearl Earring");
        put(2, "Mona Lisa");
        put(3, "The Birth of Venus");
        put(4, "The Persistence of Memory");
        put(5, "The Starry Night");
    }};

    // TODO: 2/18/2025  

    // Corresponds to http://localhost:8080/artworks
    @GetMapping("")
    public String renderArtworksHomePage() {
        StringBuilder artworksList = new StringBuilder();
        for (int artworkId : artworks.keySet()) {
            String artwork = artworks.get(artworkId);
//            artworksList.append("<li><a href='/artworks/details/").append(artworkId).append("'>").append(artwork).append("</a></li>");
            artworksList.append(
                    "<li><a href='/details/artwork=").append(artwork).append("</a></li>"
            );
        }

//        System.out.println("1"+artworks);

        return "<html>" +
                "<body>" +
                "<h2>ARTWORKS</h2>" +
                "<ul>" +
                artworksList +
                "</ul>" +
                "<p>Click <a href='/artworks/add'>here</a> to add another artwork.</p>" +
                "</body>" +
                "</html>";
    }

    // Corresponds to http://localhost:8080/artworks/add
    @GetMapping("/add")
    public String renderAddArtworkForm() {
        return "<html>" +
                "<body>" +
                "<form action='/artworks/add' method='POST'>" +
                "<p>Enter the name of a new work of art:</p>" +
                "<input type='text' name='artwork' />" + //the name is corresponding to the Query parameter
                "<button type='submit'>Submit</button>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    // Use a query parameter for dynamic results
    // Corresponds to http://localhost:8080/artworks/add?artwork=The+Starry+Night (for example)
    // @RequestParam： key-value pairs
    @PostMapping("/add")
    public String processAddArtworkForm(@RequestParam String artwork) {
        //@RequestParam extract query parameters from the URL and pass them into a controller method.
        artworks.put(nextId, artwork);
        nextId++;
        return "<html>" +
                "<body>" +
                "<h3>ARTWORK ADDED</h3>" +
                "<p>You have successfully added " + artwork + " to the collection.</p>" +
                "<p><a href='/artworks/add'>Add another artwork</a> or <a href='/artworks'>view the updated list</a> of artworks.</p>" +
                "</body>" +
                "</html>";
    }

    // Use a path parameter for dynamic results
    // Corresponds to http://localhost:8080/artworks/details/3 (for example)
//    @GetMapping("/details/{artworkId}")
//    public String displayArtworkDetails(@PathVariable int artworkId) {
//        return "<html>" +
//                "<body>" +
//                "<h3>Artwork</h3>" +
//                "<p><b>ID:</b> " + artworkId + "</p>" +
//                "<p><b>Name:</b> " + artworks.get(artworkId) + "</p>" +
//                "</body>" +
//                "</html>";
//    }

      @GetMapping("/details/{artwork}") //artwork=The+Starry+Night
        public String displayArtworkDetails(@RequestParam String artwork) {
            return "<html>" +
                    "<body>" +
                    "<h3>Artwork</h3>" +
                    "<p><b>Artwork Name:</b> " + artwork + "</p>" +
//                    "<p><b>Name:</b> " + artworks.get(artworkId) + "</p>" +
                    "</body>" +
                    "</html>";
        }

}
