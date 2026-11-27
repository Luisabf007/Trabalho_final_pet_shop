package com.petshop;

import com.petshop.model.Agendamento;
import com.petshop.model.Cliente;
import com.petshop.model.Pet;
import com.petshop.model.Servico;
import com.petshop.service.PetShopService;

public class Main {
    public static void main(String[] args) {
        PetShopService service = new PetShopService();

        // 1. Cadastrar (Create)
        Cliente cliente = service.cadastrarCliente(new Cliente(1, "Luísa", "31999990000"));
        Pet pet = service.cadastrarPet(new Pet(1, "Mel", "Cachorro", cliente.getId()));
        Servico servico = service.cadastrarServico(new Servico(1, "Banho", 55.0));
        Agendamento agendamento = service.cadastrarAgendamento(
                new Agendamento(1, pet.getId(), servico.getId(), "2026-11-28 14:00", "AGENDADO")
        );

        // 2. Consultar (Read)
        System.out.println("Cliente: " + service.consultarCliente(cliente.getId()).getNome());
        System.out.println("Status inicial: " + service.consultarAgendamento(agendamento.getId()).getStatus());

        // 3. Alterar (Update)
        service.atualizarAgendamento(agendamento.getId(), "2026-11-28 16:00", "CONFIRMADO");
        System.out.println("Novo horário: " + service.consultarAgendamento(agendamento.getId()).getDataHora());

        // 4. Excluir (Delete)
        service.excluirAgendamento(agendamento.getId());
        System.out.println("Agendamentos restantes: " + service.listarAgendamentos().size());
    }
}