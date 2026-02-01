package com.project.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.Entity.Admin;


public interface AdminRepo extends JpaRepository<Admin, Integer> {

    Admin  findByAdminemail(String adminemail);
}
