package com.project.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.Entity.UserModal;


public interface UserRepo extends JpaRepository<UserModal, Integer>{

    UserModal findByUseremail(String useremail);
}
