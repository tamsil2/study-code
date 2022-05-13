package com.appsdeveloperblog.ws.clients.photowebclient.controller;

import com.appsdeveloperblog.ws.clients.photowebclient.response.AlbumRest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

        System.out.println("principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        AlbumRest album = new AlbumRest();
        album.setAlbumId("albumOne");
        album.setAlbumTitle("Album one title");
        album.setAlbumUrl("http://localhost:8082/albums/1");

        AlbumRest album2 = new AlbumRest();
        album2.setAlbumId("albumTwo");
        album2.setAlbumTitle("Album two title");
        album2.setAlbumUrl("http://localhost:8082/albums/2");

        model.addAttribute("albums", Arrays.asList(album, album2));
        return "albums";
    }
}
