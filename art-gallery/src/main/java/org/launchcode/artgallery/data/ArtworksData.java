package org.launchcode.artgallery.data;

import org.launchcode.artgallery.models.Artwork;

import java.util.*;

//all the CRUD operations

public class ArtworksData {

    private static final Map<Integer, Artwork> artworks = new HashMap<>();

    public static void add(Artwork artwork) {
        artworks.put(artwork.getId(), artwork);
    }

    public static Artwork findById(int id) {
        return artworks.get(id);
    }

    public static Collection<Artwork> getAll() {
        return artworks.values();
    }
//    According to Java documentation, the values() method of a Map<K, V> returns a Collection of all the values in the map.
//    so returning Collection<Artwork> avoids unnecessary conversions.

    public static void remove(int id) {
        artworks.remove(id);
    }

}
