package org.pmp.repository;

import org.pmp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

@SuppressWarnings("unused")
public interface ClientRepository extends JpaRepository<Client, Long> {
}