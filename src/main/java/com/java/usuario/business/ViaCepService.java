package com.java.usuario.business;

import com.java.usuario.infrastructure.clients.ViaCepClient;
import com.java.usuario.infrastructure.clients.ViaCepDTO;
import com.java.usuario.infrastructure.exceptions.IllegalArgumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient client;

    public ViaCepDTO buscarDadosEndereco(String cep){
        try{
            return client.buscarDadosEndereco(processarCep(cep));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Erro: ", e);
        }
    }

    private String processarCep(String cep){
        String cepFormatado = cep.replace(" ", "").replace("-", "");

        if(!cepFormatado.matches("\\d+") || !Objects.equals(cepFormatado.length(), 8)){
            throw new IllegalArgumentException("O cep informado contém caracteres inválidos, verifique e tente novamente");
        }

        return cepFormatado;
    }

}
