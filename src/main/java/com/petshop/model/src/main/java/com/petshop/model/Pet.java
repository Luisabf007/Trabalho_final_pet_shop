package com.petshop.model;

public class Pet {
    private int id;
    private String nome;
    private String especie;
    private int clienteId;

    public Pet(int id, String nome, String especie, int clienteId) {
        setId(id);
        setNome(nome);
        setEspecie(especie);
        setClienteId(clienteId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do pet deve ser maior que zero.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do pet é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie do pet é obrigatória.");
        }
        this.especie = especie.trim();
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        if (clienteId <= 0) {
            throw new IllegalArgumentException("ID do tutor deve ser maior que zero.");
        }
        this.clienteId = clienteId;
    }
}