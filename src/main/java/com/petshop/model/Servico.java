package com.petshop.model;

public class Servico {
    private int id;
    private String nome;
    private double preco;

    public Servico(int id, String nome, double preco) {
        setId(id);
        setNome(nome);
        setPreco(preco);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do serviço deve ser maior que zero.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do serviço é obrigatório.");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço do serviço deve ser maior que zero.");
        }
        this.preco = preco;
    }
}
