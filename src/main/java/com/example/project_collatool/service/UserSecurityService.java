package com.example.project_collatool.service;

import com.example.project_collatool.db.UserEntity;
import com.example.project_collatool.repository.UserRepository;
import com.example.project_collatool.sbb.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String uId) throws UsernameNotFoundException {
        log.info("@# security uId ===>"+uId);
        Optional<UserEntity> _siteUser = this.userRepository.findByuId(uId);
        log.info("@# security db user uId ======>"+_siteUser.get().getUId());
        log.info("@# security db user uPwd ======>"+_siteUser.get().getUPwd());
        if(_siteUser.isEmpty()){
            log.info("@# security fail ======");
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        UserEntity siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(uId)){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(siteUser.getUId(),siteUser.getUPwd(),authorities);
    }
}
