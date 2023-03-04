package sparat.spartaclone.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparat.spartaclone.common.entity.User;
import sparat.spartaclone.common.enums.ErrorMessage;
import sparat.spartaclone.common.jwt.JwtUtil;
import sparat.spartaclone.user.dto.UniquenessResponseDto;
import sparat.spartaclone.user.dto.LoginRequestDto;
import sparat.spartaclone.user.dto.SignupRequestDto;
import sparat.spartaclone.user.dto.UserResponseDto;
import sparat.spartaclone.user.enums.UserRoleEnum;
import sparat.spartaclone.user.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public UserResponseDto signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        // username 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException(ErrorMessage.USERNAME_DUPLICATION.getMessage());
        }

        // 사용자 ROLE 확인
//        if (signupRequestDto.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {
//            if (signupRequestDto.getAdminToken() == null && !signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
//                throw new EntityNotFoundException(ErrorMessage.WRONG_ADMIN_PASSWORD.getMessage());
//            }
//        }

        User newUser = new User(signupRequestDto, encodedPassword);
        userRepository.save(newUser);

        return UserResponseDto.of(newUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // 사용자 확인
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow(
                () -> new EntityNotFoundException(ErrorMessage.WRONG_USERNAME.getMessage())
        );

        // 비밀번호 확인
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(ErrorMessage.WRONG_PASSWORD.getMessage());
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), UserRoleEnum.USER));
        return UserResponseDto.of(user);
    }

    @Transactional
    public UniquenessResponseDto checkUsernameUniqueness(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return UniquenessResponseDto.of(true);
        }
        else {
            return UniquenessResponseDto.of(false);
        }
    }

    @Transactional
    public UniquenessResponseDto checkNicknameUniqueness(String nickname) {
        User user = userRepository.findByNickname(nickname).orElse(null);
        if (user == null) {
            return UniquenessResponseDto.of(true);
        }
        else {
            return UniquenessResponseDto.of(false);
        }
    }

    @Transactional
    public UniquenessResponseDto checkEmailUniqueness(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return UniquenessResponseDto.of(true);
        }
        else {
            return UniquenessResponseDto.of(false);
        }
    }
}
