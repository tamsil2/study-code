package com.appsdeveloperblog.ws.clients.photowebclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AlbumsController {

    @Autowired
    OAuth2AuthorizedClientService oAuth2ClientService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/albums")
    public String getAlbums(@AuthenticationPrincipal OidcUser principal) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        OAuth2AuthorizedClient oauth2Client = oAuth2ClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
        System.out.println("jetAccessToken = " + jwtAccessToken);

        System.out.println("principal = " + principal);

        OidcIdToken idToken = principal.getIdToken();
        String idTokenValue = idToken.getTokenValue();
        System.out.println("idTokenValue = " + idTokenValue);

        String url = "http://localhost:8082/albums";



//        AlbumRest album = new AlbumRest();
//        album.setAlbumId("albumOne");
//        album.setAlbumTitle("Album one title");
//        album.setAlbumUrl("http://localhost:8082/albums/1");
//
//        AlbumRest album2 = new AlbumRest();
//        album2.setAlbumId("albumTwo");
//        album2.setAlbumTitle("Album two title");
//        album2.setAlbumUrl("http://localhost:8082/albums/2");
//
//        model.addAttribute("albums", Arrays.asList(album, album2));
        return "albums";
    }
}
