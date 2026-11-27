package com.petshop.service;

import com.petshop.model.Agendamento;
import com.petshop.model.Cliente;
import com.petshop.model.Pet;
import com.petshop.model.Servico;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PetShopService {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pet> pets = new ArrayList<>();
    private final List<Servico> servicos = new ArrayList<>();
    private final List<Agendamento> agendamentos = new ArrayList<>();

    public Cliente cadastrarCliente(Cliente cliente) {
        validarNaoNulo(cliente, "Cliente não pode ser nulo.");
        if (existeCliente(cliente.getId())) {
            throw new IllegalArgumentException("Já existe cliente com ID " + cliente.getId());
        }
        clientes.add(cliente);
        return cliente;
    }

    public Cliente consultarCliente(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado: " + id));
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente atualizarCliente(int id, String novoNome, String novoTelefone) {
        Cliente cliente = consultarCliente(id);
        cliente.setNome(novoNome);
        cliente.setTelefone(novoTelefone);
        return cliente;
    }

    public boolean excluirCliente(int id) {
        Cliente cliente = consultarCliente(id);
        return clientes.remove(cliente);
    }

    public Pet cadastrarPet(Pet pet) {
        validarNaoNulo(pet, "Pet não pode ser nulo.");
        consultarCliente(pet.getClienteId());
        pets.add(pet);
        return pet;
    }

    public Servico cadastrarServico(Servico servico) {
        validarNaoNulo(servico, "Serviço não pode ser nulo.");
        servicos.add(servico);
        return servico;
    }

    public Agendamento cadastrarAgendamento(Agendamento agendamento) {
        validarNaoNulo(agendamento, "Agendamento não pode ser nulo.");
        if (existeAgendamento(agendamento.getId())) {
            throw new IllegalArgumentException("Já existe agendamento com ID " + agendamento.getId());
        }
        agendamentos.add(agendamento);
        return agendamento;
    }

    public Agendamento consultarAgendamento(int id) {
        return agendamentos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Agendamento não encontrado: " + id));
    }

    public List<Agendamento> listarAgendamentos() {
        return new ArrayList<>(agendamentos);
    }

    public Agendamento atualizarAgendamento(int id, String novaDataHora, String novoStatus) {
        Agendamento agendamento = consultarAgendamento(id);
        agendamento.setDataHora(novaDataHora);
        agendamento.setStatus(novoStatus);
        return agendamento;
    }

    public boolean excluirAgendamento(int id) {
        Agendamento agendamento = consultarAgendamento(id);
        return agendamentos.remove(agendamento);
    }

    private boolean existeCliente(int id) {
        return clientes.stream().anyMatch(c -> c.getId() == id);
    }

    private boolean existeAgendamento(int id) {
        return agendamentos.stream().anyMatch(a -> a.getId() == id);
    }

    private void validarNaoNulo(Object objeto, String mensagem) {
        if (objeto == null) {
            throw new IllegalArgumentException(mensagem);
        }
    }
}
