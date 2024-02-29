package com.agenda.contactos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.contactos.entity.Contacto;
import com.agenda.contactos.repository.RepoContacto;

@Controller
public class ContactoController
{
	@Autowired
	private RepoContacto repoContacto;

	@GetMapping({"/",""})
	public String index(Model modelo)
	{
		List<Contacto> contactos = repoContacto.findAll();
		modelo.addAttribute("contactos",contactos);
		return "index";
	}

	@GetMapping("/nuevo")
	public String mostrarFormRegistroContacto(Model modelo)
	{
		modelo.addAttribute("contacto",new Contacto());
		return "nuevo";
	}

	@PostMapping("/nuevo")
	public String guardarContacto(@Validated Contacto c, BindingResult bindingResult, RedirectAttributes redirect, Model modelo)
	{
		if (bindingResult.hasErrors())
		{
			modelo.addAttribute("c",c);
			return "nuevo";
		}

		repoContacto.save(c);
		redirect.addFlashAttribute("msgExito","El contacto ha sido guardado con exito");
		return "redirect:/";
	}

	@GetMapping("/{id}/editar")
	public String mostrarFormEditarContacto(@PathVariable Integer id, Model modelo)
	{
		Contacto contacto = repoContacto.getReferenceById(id);
		System.out.println(contacto.getNombre());
		modelo.addAttribute("contacto",contacto);
		return "nuevo";
	}

	@PostMapping("/{id}/editar")
	public String actualizarContacto(@PathVariable Integer id, @Validated Contacto c, BindingResult bindingResult, RedirectAttributes redirect, Model modelo)
	{
		Contacto contactoDB = repoContacto.getReferenceById(id);
		System.out.println(contactoDB.getNombre());
		if (bindingResult.hasErrors())
		{
			modelo.addAttribute("c",c);
			return "nuevo";
		}

		contactoDB.setNombre(c.getNombre());
		contactoDB.setCelular(c.getCelular());
		contactoDB.setEmail(c.getEmail());
		contactoDB.setFechaNacimiento(c.getFechaNacimiento());

		repoContacto.save(contactoDB);
		redirect.addFlashAttribute("msgExito","El contacto ha sido actualizado correctamente");
		return "redirect:/";
	}

	@PostMapping("/{id}/eliminar")
	public String eliminarContacto(@PathVariable Integer id, RedirectAttributes redirectAttributes)
	{
		Contacto contacto = repoContacto.getReferenceById(id);
		repoContacto.delete(contacto);
		redirectAttributes.addFlashAttribute("msgExito","El contacto se ha eliminado correctamente");
		return "redirect:/";
	}

}

// INSERT INTO contacto (id,nombre,email,celular,fecha_nacimiento,fecha_registro) VALUES (2,"12311","1@111","11123","2024-01-18","2024-02-18 22:27:01.000000")