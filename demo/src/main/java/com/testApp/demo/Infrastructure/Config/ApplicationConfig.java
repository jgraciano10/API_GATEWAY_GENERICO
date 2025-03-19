package com.testApp.demo.Infrastructure.Config;


import com.testApp.demo.Infrastructure.Config.AuthModels.CustomOAuth2User;
import com.testApp.demo.Infrastructure.Config.AuthModels.CustomUserDetails;
import com.testApp.demo.Infrastructure.Repositories.Entities.UserEntity;
import com.testApp.demo.Infrastructure.Repositories.UserRepository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Objects;

@Configuration
public class ApplicationConfig {

    private final UserRepository userRepository;

    public ApplicationConfig(UserRepository userRepository){
        this.userRepository  = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(!Objects.isNull(userEntity)){
                return new CustomUserDetails(userEntity);
            }
            else{
                throw new UsernameNotFoundException("User not found");
            }
        };

    }

    @Bean
    public DefaultOAuth2UserService CustomOAuth2UserService(){
        return new DefaultOAuth2UserService() {
            @Override
            public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
                OAuth2User oauth2User = super.loadUser(userRequest);

                // Puedes manejar la información del usuario aquí
                String email = oauth2User.getAttribute("email");
                String name = oauth2User.getAttribute("name");

                // Devolver el usuario autenticado
                return new CustomOAuth2User(oauth2User);
            }
        };
    }
}
