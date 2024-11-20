package com.example.auth.controller;

import com.example.auth.entity.User;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Prefixo para todas as rotas
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para cadastrar um usuário (POST)
    @PostMapping("/cadastrar")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para login do usuário (POST)
    @PostMapping("/entrar")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
            return ResponseEntity.ok(authenticatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para listar todos os usuários (GET)
    @GetMapping("/listar")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.ok("Nenhum usuário encontrado.");
            }
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para buscar um usuário por e-mail (GET)
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            User user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para excluir um usuário por ID (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Usuário com ID " + id + " foi excluído com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para atualizar um usuário por ID (PUT)
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            // Atualizar o usuário com o ID e os dados fornecidos
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser); // Retorna o usuário atualizado
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retorna erro, caso ocorra
        }
    }
}
