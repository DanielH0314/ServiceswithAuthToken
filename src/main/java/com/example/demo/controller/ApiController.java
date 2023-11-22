package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AlumnoDao;
import com.example.demo.entity.Alumno;

@RestController
@RequestMapping(value = "api")
public class ApiController {
	
	@Autowired
	private AlumnoDao objaDao;
	
	@GetMapping(value="/get")
	public List<Alumno> get()
	{
		System.out.println("----> Tercero ");
		return objaDao.findAll();
	}
	
	@PostMapping(value="/insert")
	void insert(@RequestBody Alumno objA)
	{
		try
		{
			objaDao.save(objA);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@PutMapping(value="/update")
	void update(@RequestBody Alumno objA)
	{
		try
		{
			objaDao.save(objA);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@DeleteMapping(value="/delete_a")
	void delete_a(@RequestParam int id_a)
	{
		try
		{
			objaDao.deleteById(id_a);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@DeleteMapping(value="/delete_b")
	void delete_b(@RequestBody Alumno objA)
	{
		try
		{
			objaDao.deleteById(objA.getId_a());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
