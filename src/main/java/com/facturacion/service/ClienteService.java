package com.facturacion.service;

import com.facturacion.entity.Cliente;
import com.facturacion.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findByClienteId(id);
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findByClienteId(id).map(cliente -> {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setEmail(clienteActualizado.getEmail());
            return clienteRepository.save(cliente);
        });
    }
}