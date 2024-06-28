package de.ait_tr.g_33_shop.service;

import de.ait_tr.g_33_shop.domain.entity.Role;
import de.ait_tr.g_33_shop.domain.entity.User;
import de.ait_tr.g_33_shop.repository.UserRepository;
import de.ait_tr.g_33_shop.service.interfaces.EmailService;
import de.ait_tr.g_33_shop.service.interfaces.RoleService;
import de.ait_tr.g_33_shop.service.interfaces.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder encoder, RoleService roleService, EmailService emailService) {
        this.repository = repository;
        this.encoder = encoder;
        this.roleService = roleService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User %s not found", username))
        );
    }

    @Override
    public void register(User user) {
        // TODO *** домашнее задание - учесть, что пользователь
        // TODO может регистрироваться уже не первый раз
        // TODO например, если первый раз он просто не успел до истечения срока действия кода
        user.setId(null);
        user.setPassword(encoder.encode(user.getPassword()));

        Role userRole = roleService.getRoleUser();
        user.setRoles(Set.of(userRole));
        user.setActive(false);

        // TODO обработать все возможные ошибки - домашнее задание
        repository.save(user);
        emailService.sendConfirmationEmail(user);
    }
}