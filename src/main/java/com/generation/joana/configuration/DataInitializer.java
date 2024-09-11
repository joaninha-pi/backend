package main.java.com.generation.joana.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.joana.model.Role;
import com.generation.joana.model.Usuario;
import com.generation.joana.repository.RoleRepository;
import com.generation.joana.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            Role adminRole = new Role();
            adminRole.setNome("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role vendedorRole = new Role();
            vendedorRole.setNome("ROLE_VENDEDOR");
            roleRepository.save(vendedorRole);

            Role clienteRole = new Role();
            clienteRole.setNome("ROLE_CLIENTE");
            roleRepository.save(clienteRole);

            Usuario adminUser = new Usuario();
            adminUser.setNome("Admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setSenha(new BCryptPasswordEncoder().encode("adminpassword"));
            adminUser.getRoles().add(adminRole);
            usuarioRepository.save(adminUser);
        };
    }
}