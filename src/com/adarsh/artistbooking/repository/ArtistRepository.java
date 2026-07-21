package com.adarsh.artistbooking.repository;

import com.adarsh.artistbooking.entity.Artist;
import com.adarsh.artistbooking.entity.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistRepository {

        private final Map<Long,Artist> artists;
        private long nextId;

        public ArtistRepository() {
            this.artists =  new HashMap<>();
            this.nextId = 1;
        }
        public Artist saveArtist(Artist artist)
        {
            if(artist.getId() == 0){
                artist.assignId(nextId++);
            }
            artists.put(artist.getId(), artist);
            return artist ;
        }

        public Artist findById(long id)
        {
            Artist artist = artists.get(id);
            if(artist == null){
                throw new IllegalArgumentException(
                        "Event not found");
            }

            return artists.get(id);
        }
        public void deleteById(long id)
        {
            validateIsIdPresent(id);
            artists.remove(id);
        }
        public List<Artist> findAll()
        {
            return new ArrayList<>(artists.values());
        }
        private void validateIsIdPresent(long id)
        {
            if(!artists.containsKey(id))
            {
                throw new IllegalArgumentException(String.format("Event with id %d does not exist", id));
            }
        }
    }


