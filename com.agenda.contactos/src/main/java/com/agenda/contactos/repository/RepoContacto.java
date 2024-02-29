package com.agenda.contactos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.contactos.entity.Contacto;

public interface RepoContacto extends JpaRepository<Contacto, Integer>
{
	
}