package com.ipartek.formacion.spring.springjdbc;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ipartek.formacion.spring.springjdbc.entidades.Cliente;
import com.ipartek.formacion.spring.springjdbc.repositorios.Dao;

@SpringBootApplication
public class SpringjdbcApplication implements CommandLineRunner {

	static final protected String OP_LISTAR = "1";
	static final protected String OP_CREAR = "2";
	static final protected String OP_MODIFICAR = "3";
	static final protected String OP_BUSCAR_ID = "4";
	static final protected String OP_ELIMINAR = "5";

	static final protected String OP_SALIR = "s";

	private static boolean isValid = false;
	private static String opc = "";
	private static Scanner entrada = null;

	public static void main(String[] args) {
		SpringApplication.run(SpringjdbcApplication.class, args);
	}

	@Autowired
	private Dao<Cliente> dao;

	@Override
	public void run(String... args) throws Exception {

		entrada = new Scanner(System.in);

		do {

			menu();

			System.out.println("Introduzca una Opcion del menu: ");
			opc = entrada.nextLine();

			switch (opc) {

			case OP_LISTAR:

				listar();

				break;

			case OP_CREAR:
				crear();

				break;

			case OP_MODIFICAR:
				modificar();

				break;

			case OP_BUSCAR_ID:

				buscar();

				break;

			case OP_ELIMINAR:

				eliminar();

				break;

			case OP_SALIR:
				isValid = true;
				System.out.println("Has salido del programa, gracias...");

				break;

			default:
				System.out.println("no has introducido una Opcion Valida!!!!! Vuele a introducir el numero: \n");
				break;
			}

		} while (!isValid);
		entrada.close();

	}

	private void buscar() {

		System.out.println("----------Buscar Cliente Por ID------------\n");

		System.out.println("Introduzca la id Que Quiere buscar: ");
		Long ids = Long.parseLong(entrada.nextLine());
		System.out.println(dao.obtenerPorId(ids));

	}

	private void modificar() {

		Long id = null;

		System.out.println("----------Modificar Cliente----------");

		System.out.println("Introduzca el id a Modificar: ");
		id = Long.parseLong(entrada.nextLine());

		System.out.println("Introduzca su Nombre: ");
		String nombre = entrada.nextLine();

		System.out.println("-------------------------------------\n ");

		System.out.println("Introduzca sus apellidos: ");
		String apellidos = entrada.nextLine();

		System.out.println("-------------------------------------\n ");

		System.out.println("Introduzca su cif: ");
		String cif = entrada.nextLine();

		System.out.println("-------------------------------------\n ");

		System.out.println("Introduzca su fecha de Nacimiento: ");
		LocalDate fechaNacimiento = LocalDate.parse(entrada.nextLine());

		dao.modificar(new Cliente(id, nombre, apellidos, cif, fechaNacimiento));

		// dao.modificar(new Cliente(2L, "Marcos", "Juanes", "87654321A",
		// LocalDate.now()));

	}

	private void eliminar() {

		System.out.println("----------Eliminar Cliente----------\n");
		System.out.println("Introduzca la id Del Cliente a Eliminar: ");
		Long ids = Long.parseLong(entrada.nextLine());

		try {
			dao.borrar(ids);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void crear() {

		System.out.println("-----Crear Nuevo Cliente-----\n ");

		System.out.println("Introduzca su Nombre: ");
		String nombre = entrada.nextLine();

		System.out.println("-------------------------------------\n ");

		System.out.println("Introduzca sus apellidos: ");
		String apellidos = entrada.nextLine();

		System.out.println("-------------------------------------\n ");

		System.out.println("Introduzca su cif: ");
		String cif = entrada.nextLine();

		System.out.println("-------------------------------------\n ");

		System.out.println("Introduzca su fecha de Nacimiento: ");
		LocalDate fechaNacimiento = LocalDate.parse(entrada.nextLine());

		dao.agregar(new Cliente(null, nombre, apellidos, cif, fechaNacimiento));

		// dao.agregar(new Cliente(null, "Marcio", "de Souza", "13243545Z",
		// LocalDate.now()));

	}

	private void listar() {

		for (Cliente cliente : dao.obtenerTodos()) {
			System.out.println(cliente);
		}

	}

	// pintar menu
	private void menu() {

		System.out.println("************************************");
		System.out.println(" " + OP_LISTAR + ".- Listar todos los Clientes ");
		System.out.println(" " + OP_CREAR + ".- Crear un Cliente ");
		System.out.println(" " + OP_MODIFICAR + ".- Editar un Cliente");
		System.out.println(" " + OP_BUSCAR_ID + ".- Buscar Cliente por Id ");
		System.out.println(" " + OP_ELIMINAR + ".- Dar de baja un Cliente ");
		System.out.println(" ");
		System.out.println(" " + OP_SALIR + " - Salir");
		System.out.println("************************************");

	}

}
