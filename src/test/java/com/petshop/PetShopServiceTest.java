package com.petshop;

import com.petshop.model.*;
import com.petshop.service.PetShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class PetShopServiceTest {
    private PetShopService service;

    @BeforeEach
    void setUp() { service = new PetShopService(); }

    @Test void cadastrarCliente_normal() {
        assertEquals("Mariana", service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777")).getNome());
    }
    @Test void cadastrarCliente_limite8Digitos() {
        assertEquals("12345678", service.cadastrarCliente(new Cliente(1, "Lu", "12345678")).getTelefone());
    }
    @Test void cadastrarCliente_erroDuplicadoENulo() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        assertThrows(IllegalArgumentException.class, () -> service.cadastrarCliente(new Cliente(1, "X", "31988886666")));
        assertThrows(IllegalArgumentException.class, () -> service.cadastrarCliente(null));
    }
    @Test void consultarCliente_normalLimiteEErro() {
        service.cadastrarCliente(new Cliente(10, "Carlos", "31977776666"));
        assertEquals("Carlos", service.consultarCliente(10).getNome());
        service.cadastrarCliente(new Cliente(Integer.MAX_VALUE, "Max", "31977775555"));
        assertEquals(Integer.MAX_VALUE, service.consultarCliente(Integer.MAX_VALUE).getId());
        assertThrows(NoSuchElementException.class, () -> service.consultarCliente(999));
    }
    @Test void atualizarCliente_normalLimiteEErro() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        assertEquals("Mariana Silva", service.atualizarCliente(1, "Mariana Silva", "31988880000").getNome());
        assertEquals("Ana", service.atualizarCliente(1, "  Ana  ", "12345678").getNome());
        assertThrows(IllegalArgumentException.class, () -> service.atualizarCliente(1, "  ", "12345678"));
        assertThrows(NoSuchElementException.class, () -> service.atualizarCliente(88, "A", "12345678"));
    }
    @Test void excluirCliente_normalLimiteEErro() {
        service.cadastrarCliente(new Cliente(1, "Unico", "12345678"));
        assertTrue(service.excluirCliente(1));
        assertTrue(service.listarClientes().isEmpty());
        assertThrows(NoSuchElementException.class, () -> service.excluirCliente(404));
    }
    @Test void petServicoEAgendamento_casosELimites() {
        service.cadastrarCliente(new Cliente(1, "Luísa", "31999998888"));
        assertEquals("Bidu", service.cadastrarPet(new Pet(1, "Bidu", "Cachorro", 1)).getNome());
        assertThrows(NoSuchElementException.class, () -> service.cadastrarPet(new Pet(2, "Rex", "Cão", 99)));
        assertThrows(IllegalArgumentException.class, () -> new Pet(0, "Rex", "Cão", 1));
        assertEquals(0.01, service.cadastrarServico(new Servico(1, "Unha", 0.01)).getPreco());
        assertThrows(IllegalArgumentException.class, () -> new Servico(1, "Banho", 0.0));
        Agendamento ag = service.cadastrarAgendamento(new Agendamento(1, 1, 1, "2026-11-28 09:00", "ABERTO"));
        service.atualizarAgendamento(ag.getId(), "2026-11-28 10:00", "CONFIRMADO");
        assertEquals("CONFIRMADO", service.consultarAgendamento(1).getStatus());
        assertTrue(service.excluirAgendamento(1));
        assertThrows(NoSuchElementException.class, () -> service.consultarAgendamento(1));
    }
    @Test void main_executaSemErro() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
