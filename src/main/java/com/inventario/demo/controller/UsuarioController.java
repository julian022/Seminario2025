package com.inventario.demo.controller;

import com.inventario.demo.model.Usuario;
import com.inventario.demo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/listarusuarios")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping(path = "/crearusuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping(path = "/listarusuarios/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping(path = "/actualizarusuario/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            usuario.setUsername(usuarioDetails.getUsername());
            usuario.setPassword(usuarioDetails.getPassword());
            usuario.setRole(usuarioDetails.getRole());
            return usuarioService.save(usuario);
        }
        return null;
    }

    @DeleteMapping(path = "/eliminarusuario/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
