package com.clients;

import java.util.List;

public interface IClientDAO extends IDAO<Client> {
    public List<Client> getAll(String nom);
    public Client getOne(long n);
}
