package br.com.ehmf.AppContatos.AppContatos.repository;
import org.springframework.stereotype.Repository;
import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
