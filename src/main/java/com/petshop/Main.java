package com.petshop;

import com.petshop.model.Agendamento;
import com.petshop.model.Cliente;
import com.petshop.model.Pet;
import com.petshop.model.Servico;
import com.petshop.service.PetShopService;

public class Main {
    public static void main(String[] args) {
        PetShopService s = new PetShopService();
        Cliente c = s.cadastrarCliente(new Cliente(1, "Luísa", "31999990000"));
        Pet p = s.cadastrarPet(new Pet(1, "Mel", "Cachorro", c.getId()));
        Servico sv = s.cadastrarServico(new Servico(1, "Banho", 55.0));
        Agendamento a = s.cadastrarAgendamento(
                new Agendamento(1, p.getId(), sv.getId(), "2026-11-28 14:00", "AGENDADO")
        );
        System.out.println(s.consultarCliente(c.getId()).getNome());
        s.atualizarAgendamento(a.getId(), "2026-11-28 16:00", "CONFIRMADO");
        s.excluirAgendamento(a.getId());
        System.out.println(s.listarAgendamentos().size());
    }
}
