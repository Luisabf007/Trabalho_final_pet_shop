package com.petshop.model;

public class Agendamento {
    private int id;
    private int petId;
    private int servicoId;
    private String dataHora;
    private String status;

    public Agendamento(int id, int petId, int servicoId, String dataHora, String status) {
        setId(id);
        setPetId(petId);
        setServicoId(servicoId);
        setDataHora(dataHora);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do agendamento deve ser maior que zero.");
        }
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        if (petId <= 0) {
            throw new IllegalArgumentException("ID do pet deve ser maior que zero.");
        }
        this.petId = petId;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        if (servicoId <= 0) {
            throw new IllegalArgumentException("ID do serviço deve ser maior que zero.");
        }
        this.servicoId = servicoId;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        if (dataHora == null || dataHora.trim().isEmpty()) {
            throw new IllegalArgumentException("Data e hora são obrigatórias.");
        }
        this.dataHora = dataHora.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status é obrigatório.");
        }
        this.status = status.trim();
    }
}
