package com.luizacode.API.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizacode.API.Entity.Cliente;
import com.luizacode.API.Service.ClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController //Informa que é um controller
@RequestMapping(value = "api/cliente") //Define nome para chamada
@CrossOrigin(origins = "*") //Define que qualquer origem pode acessar essa API
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping() //Define caminho para chamada
    @ApiOperation(value = "Cadastra um cliente.") //Informa para Swagger a descrição do endpoint
    @ApiResponse(code = 200, message = "Cliente cadastrado!")
    public Object cadastraCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastraCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um cliente cadastrado.")
    @ApiResponse(code = 200, message = "Cliente deletado!")
    public Object deletaCliente(@PathVariable(value = "id") Long id) {
        return clienteService.deletaCliente(id);
    }

    @PutMapping()
    @ApiOperation(value = "Atualiza informações de um cliente.")
    @ApiResponse(code = 200, message = "Cliente atualizado!")
    public ResponseEntity<Cliente> atualizaCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<Cliente>(clienteService.atualizaCliente(cliente), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation(value = "Lista clientes cadastrados.")
    public ResponseEntity<List<Cliente>> listaCliente() {
        return ResponseEntity.ok(clienteService.buscaClientes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna cliente específico através do id.")
    public Object listaClienteUnico(@PathVariable(value = "id") Long id) {
        return clienteService.buscaUmCliente(id);
    }

}
