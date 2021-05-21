package com.luizacode.API.services.cliente;

import com.luizacode.API.Entity.Cliente;
import com.luizacode.API.Repository.ClienteRepository;
import com.luizacode.API.Service.ClienteService;
import com.luizacode.API.builders.cliente.ClienteBuilder;
import com.luizacode.API.builders.produto.ProdutoBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceUnitTesting {

    @InjectMocks
    private ClienteService clienteService; // oque será testado

    @Mock
    private ClienteRepository clienteRepository; // oque será mockado

    ClienteBuilder clienteBuilder = new ClienteBuilder();


    @Test
    @DisplayName("cadastraCliente - Sucesso")
    public void cadastraClienteSuccess() {
        Cliente cliente1 = clienteBuilder.criarClienteEntityComId();

        Mockito.when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Object cliente = clienteService.cadastraCliente(cliente1);

        Assertions.assertTrue(!Objects.isNull(cliente));
    }

    @Test
    @DisplayName("cadastraCliente - Exception")
    public void cadastraClienteException() {
        Cliente cliente1 = clienteBuilder.criarClienteEntityComId();

        Mockito.when(clienteRepository.findByCpf("12345678912")).thenReturn(Optional.of(cliente1));
        Mockito.when(clienteRepository.save(any())).thenThrow(RuntimeException.class);
    }

    @Test
    @DisplayName("buscaClientes - Sucesso")
    public void findAllClientesSuccess() {
        List<Cliente> clientes = clienteBuilder.criarListaClienteEntityComId();

        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> clienteList = clienteService.buscaClientes();

        Assertions.assertEquals(clienteList.size(), clientes.size());

        Assertions.assertEquals(clientes.get(0).getIdCliente(), clienteList.get(0).getIdCliente());
        Assertions.assertEquals(clientes.get(0).getNome(), clienteList.get(0).getNome());
        Assertions.assertEquals(clientes.get(0).getCpf(), clienteList.get(0).getCpf());
        Assertions.assertEquals(clientes.get(0).getEmail(), clienteList.get(0).getEmail());

        Assertions.assertEquals(clientes.get(1).getIdCliente(), clienteList.get(1).getIdCliente());
        Assertions.assertEquals(clientes.get(1).getNome(), clienteList.get(1).getNome());
        Assertions.assertEquals(clientes.get(1).getCpf(), clienteList.get(1).getCpf());
        Assertions.assertEquals(clientes.get(1).getEmail(), clienteList.get(1).getEmail());
    }

    @Test
    @DisplayName("atualizaCliente - Sucesso")
    public void atualizaClienteSuccess() {
        Cliente cliente = clienteBuilder.criarClienteEntityComId();


        given(clienteRepository.findById(1L)).willReturn(Optional.of(cliente));
        given(clienteRepository.save(any())).willReturn(cliente);

        Cliente response = clienteService.atualizaCliente(cliente);

        Assertions.assertEquals(cliente.getIdCliente(), response.getIdCliente());
        Assertions.assertEquals(cliente.getNome(), response.getNome());
        Assertions.assertEquals(cliente.getCpf(), response.getCpf());
        Assertions.assertEquals(cliente.getEmail(), response.getEmail());
    }

    @Test
    @DisplayName("deletaCliente - Sucesso")
    public void deletaClienteSuccess() {
        Cliente cliente1 = clienteBuilder.criarClienteEntityComId();

        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente1));

        Object clienteResult = clienteService.deletaCliente(1L);

        Mockito.verify(clienteRepository, Mockito.times(1)).deleteById(cliente1.getIdCliente());
    }

    @Test
    @DisplayName("buscaUmCliente - Sucesso")
    public void findClienteByIdSuccess() {
        Cliente cliente1 = clienteBuilder.criarClienteEntityComId();

        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente1));
        Mockito.when(clienteService.buscaUmCliente(1L)).thenReturn(Optional.of(cliente1));

        Object clienteResult = clienteService.buscaUmCliente(1L);

        Assertions.assertTrue(!Objects.isNull(clienteResult));
    }

}
