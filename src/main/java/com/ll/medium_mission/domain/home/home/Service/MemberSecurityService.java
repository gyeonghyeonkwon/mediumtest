package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Repository.MemberRepository;
import com.ll.medium_mission.global.Security.MemberRole;
import lombok.RequiredArgsConstructor;
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
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 스프링 시큐리티가 로그인을 하기 위해 사용자 아이디를 찾는다.
     */
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Optional<MemberUser> _memberUser = this.memberRepository.findByNickname(nickname);
        if (_memberUser.isEmpty()) {
            throw new UsernameNotFoundException("아이디를 찾을수 없습니다.");
        }
        MemberUser memberUser = _memberUser.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(nickname)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        return new User(memberUser.getNickname() , memberUser.getPassword() , authorities);
    }
}
