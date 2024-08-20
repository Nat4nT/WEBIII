package repositories;

import br.edu.ifpr.ifprstore.models.Seller;
import br.edu.ifpr.ifprstore.repositories.SellerRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class SellerRepositoryTest {
    @Test
    public void deveExibirUmaListaDeSellers() {

        SellerRepository repository = new SellerRepository();

        List<Seller> sellers = repository.getSellers();

        for (Seller s : sellers) {
            System.out.println(s);
        }
    }

    @Test
    public void deveInserirUmRegistroNaTabelaSeller(){
        SellerRepository repository = new SellerRepository();

        Seller sellerFake = new Seller();
        sellerFake.setName(("Frodo"));
        sellerFake.setEmail("Frodo@valinor.com");
        sellerFake.setBirthDate(LocalDate.of(2024,8,5));
        sellerFake.setBaseSalary(10000.0);


        repository.insert(sellerFake);

    }

    @Test
    public void deveAtualizarOSalarioDeUmSellerDeUmDepartamento(){

        SellerRepository repository = new SellerRepository();
        repository.updateSalary(1, 1500.00);

    }
    @Test
    public void deveDeletarUmSeller(){
        SellerRepository repository = new SellerRepository();
        repository.delete(3);

    }

    @Test
    public void deveRetornarUmSellerPeloId(){
        SellerRepository repository = new SellerRepository();
        Seller seller = repository.getById(1);

        System.out.println(seller);
        System.out.println("*******Departamento********");
        System.out.println(seller.getDepartment());
    }

    @Test
    public void deveRetornarUmaListaDeSellersPeloIdDoDepartment(){
        SellerRepository repository = new SellerRepository();
        List <Seller> sellerList = repository.findByDepartment(2);

        for(Seller seller : sellerList){
            System.out.println("*********** Lista de Vendedores por Departamento: *************");
            System.out.println(seller);
            System.out.println(seller.getDepartment());
            System.out.println("*************************************************************\n");
        }
    }
}