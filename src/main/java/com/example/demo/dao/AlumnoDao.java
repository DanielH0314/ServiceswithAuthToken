package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Alumno;

@Repository
public interface AlumnoDao extends JpaRepository<Alumno,Integer>{

}
