package com.ipartek.formacion.spring.springjdbc.repositorios;

import com.ipartek.formacion.spring.springjdbc.entidades.Cliente;

public interface Dao<T> {

	Iterable<Cliente> obtenerTodos();

	T obtenerPorId(Long id);

	T agregar(T objeto);

	T modificar(T objeto);

	void borrar(Long id);

}
