package repositories;

import br.edu.ifpr.ifprstore.models.Department;
import br.edu.ifpr.ifprstore.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;


import java.util.List;

public class DepartmentRepositoryTest {

    @Test
    public void deveInserirUmRegistroNaTabelaDepartment() {
        DepartmentRepository repository = new DepartmentRepository();

        Department departmentFake = new Department();
        departmentFake.setName("Child's Fashion");

        repository.insert(departmentFake);
    }

    @Test
    public void deveAtualizarONomeDeUmDepartamento() {

        DepartmentRepository repository = new DepartmentRepository();
        repository.updateDepartmentName(3, "Women's Fashion");
    }

    @Test
    public void deveDeletarUmDepartamento() {
        DepartmentRepository repository = new DepartmentRepository();
        repository.delete(5);
    }

    @Test
    public void deveRetornarUmaListaDeTodosOsDepartamentos() {
        DepartmentRepository repository = new DepartmentRepository();
        List<Department> departments = repository.listAllDepartments();

        System.out.println("*********** Lista de Todos os Departamentos: *************");

        for (Department department : departments) {
            System.out.println(department);
        }
        System.out.println("*************************************************************\n");
    }

    @Test
    public void deveRetornarUmDepartamentoPeloId() {
        DepartmentRepository repository = new DepartmentRepository();
        Department department = repository.getById(1);

        System.out.println("*******Departamento Selecionado********");
        System.out.println(department);

    }

    @Test
    public void deveRetornarUmaListaDeTodosOsDepartamentosContendoUmaPalavraInformada() {
        DepartmentRepository repository = new DepartmentRepository();
        String word = "fashion";
        List<Department> departments = repository.findByWord(word);


        System.out.println("*************** Lista de Departamentos **********************");
        System.out.println("************* Palavra buscada: " + word + " *******************");

        for (Department department : departments) {
            System.out.println(department);
        }
        System.out.println("*************************************************************");
    }

    @Test
    public void deveRetornarDepartamentosSemVendedoresAssociados() {
        DepartmentRepository repository = new DepartmentRepository();
        List<Department> departmentsList = repository.findDepartmentsWithoutSellers();

        System.out.println("*************** Lista de departamentos sem vendedores associados **********************");

        for (Department department : departmentsList) {
            System.out.println(department);
        }
        System.out.println("****************************************************************************************");

    }
}








