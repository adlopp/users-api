package org.adlopp.usersapi.repositories;

import org.springframework.data.repository.CrudRepository;

import org.adlopp.usersapi.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
}
