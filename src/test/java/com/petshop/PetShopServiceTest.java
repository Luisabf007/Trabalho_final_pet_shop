package com.petshop;

import com.petshop.model.Agendamento;
import com.petshop.model.Cliente;
import com.petshop.model.Pet;
import com.petshop.model.Servico;
import com.petshop.service.PetShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PetShopServiceTest {

    private PetShopService service;

    @BeforeEach
    void setUp() {
        service = new PetShopService();
    }

    @Test
    void cadastrarCliente_casoNormal() {
        Cliente c = service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        assertEquals(1, c.getId());
        assertEquals("Mariana", c.getNome());
    }

    @Test
    void cadastrarCliente_valorLimiteTelefone8Digitos() {
        Cliente c = service.cadastrarCliente(new Cliente(1, "Lu", "12345678"));
        assertEquals("12345678", c.getTelefone());
    }

    @Test
    void cadastrarCliente_erroIdDuplicado() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        assertThrows(IllegalArgumentException.class,
                () -> service.cadastrarCliente(new Cliente(1, "Outro", "31988886666")));
    }

    @Test
    void consultarCliente_casoNormal() {
        service.cadastrarCliente(new Cliente(10, "Carlos", "31977776666"));
        assertEquals("Carlos", service.consultarCliente(10).getNome());
    }

    @Test
    void consultarCliente_valorLimiteIdMaximo() {
        service.cadastrarCliente(new Cliente(Integer.MAX_VALUE, "Limite", "31977775555"));
        assertEquals(Integer.MAX_VALUE, service.consultarCliente(Integer.MAX_VALUE).getId());
    }

    @Test
    void consultarCliente_erroInexistente() {
        assertThrows(NoSuchElementException.class, () -> service.consultarCliente(999));
    }

    @Test
    void atualizarCliente_casoNormal() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        Cliente atualizado = service.atualizarCliente(1, "Mariana Silva", "31988880000");
        assertEquals("Mariana Silva", atualizado.getNome());
    }

    @Test
    void atualizarCliente_valorLimiteEspacosNoNome() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        Cliente atualizado = service.atualizarCliente(1, "  Ana  ", "12345678");
        assertEquals("Ana", atualizado.getNome());
    }

    @Test
    void atualizarCliente_erroNomeVazio() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        assertThrows(IllegalArgumentException.class,
                () -> service.atualizarCliente(1, "   ", "31988887777"));
    }

    @Test
    void excluirCliente_casoNormal() {
        service.cadastrarCliente(new Cliente(1, "Mariana", "31988887777"));
        assertTrue(service.excluirCliente(1));
        assertEquals(0, service.listarClientes().size());
    }

    @Test
    void excluirCliente_valorLimiteUltimoCliente() {
        service.cadastrarCliente(new Cliente(1, "Unico", "12345678"));
        assertTrue(service.excluirCliente(1));
        assertTrue(service.listarClientes().isEmpty());
    }

    @Test
    void excluirCliente_erroIdInexistente() {
        assertThrows(NoSuchElementException.class, () -> service.excluirCliente(404));
    }

    @Test
    void cadastrarPet_casoNormal() {
        service.cadastrarCliente(new Cliente(1, "Luísa", "31999998888"));
        Pet pet = service.cadastrarPet(new Pet(1, "Bidu", "Cachorro", 1));
        assertEquals("Bidu", pet.getNome());
    }

    @Test
    void servico_valorLimitePrecoMinimo() {
        Servico servico = service.cadastrarServico(new Servico(1, "Unha", 0.01));
        assertEquals(0.01, servico.getPreco());
    }

    @Test
    void agendamento_cadastrarAtualizarEExcluir() {
        Agendamento ag = service.cadastrarAgendamento(
                new Agendamento(1, 1, 1, "2026-11-28 09:00", "ABERTO")
        );
        assertEquals(1, ag.getId());
        service.atualizarAgendamento(1, "2026-11-28 10:00", "CONFIRMADO");
        assertEquals("CONFIRMADO", service.consultarAgendamento(1).getStatus());
        assertTrue(service.excluirAgendamento(1));
    }
}
