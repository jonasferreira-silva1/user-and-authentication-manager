package com.example.auth.service;

import com.example.auth.entity.User;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Cadastrar um novo usuário
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário com este e-mail já está registrado.");
        }
        return userRepository.save(user);
    }

    // Autenticar o usuário (login)
    public User authenticateUser(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos."));
    }

    // Buscar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar um usuário por e-mail
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário com este e-mail não encontrado."));
    }

    // Excluir um usuário por ID
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário com ID " + id + " não encontrado.");
        }
        userRepository.deleteById(id);
    }

    // Atualizar um usuário
    public User updateUser(Long id, User user) {
        // Verificar se o usuário existe
        Optional<User> existingUser = userRepository.findById(id);
        if (!existingUser.isPresent()) {
            throw new RuntimeException("Usuário com ID " + id + " não encontrado.");
        }

        // Verificar se o e-mail foi alterado e já existe no banco
        if (!existingUser.get().getEmail().equals(user.getEmail()) && userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já está em uso por outro usuário.");
        }

        // Atualiza os dados do usuário
        User updatedUser = existingUser.get();
        updatedUser.setName(user.getName());  // Atualiza o nome
        updatedUser.setEmail(user.getEmail()); // Atualiza o e-mail
        updatedUser.setPassword(user.getPassword()); // Atualiza a senha
        
        return userRepository.save(updatedUser);
    }
}
