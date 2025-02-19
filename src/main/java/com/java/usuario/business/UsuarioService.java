package com.java.usuario.business;

import com.java.usuario.business.converter.UsuarioConverter;
import com.java.usuario.business.dto.EnderecoDTO;
import com.java.usuario.business.dto.TelefoneDTO;
import com.java.usuario.business.dto.UsuarioDTO;
import com.java.usuario.infrastructure.entity.Endereco;
import com.java.usuario.infrastructure.entity.Telefone;
import com.java.usuario.infrastructure.entity.Usuario;
import com.java.usuario.infrastructure.exceptions.ConflictException;
import com.java.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.java.usuario.infrastructure.repository.EnderecoRepository;
import com.java.usuario.infrastructure.repository.TelefoneRepository;
import com.java.usuario.infrastructure.repository.UsuarioRepository;
import com.java.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificarEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificarEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.findByEmail(email)
                            .orElseThrow(
                                    () -> new ResourceNotFoundException("Email não encontrado" + email))
            );
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado " + email);

        }
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}