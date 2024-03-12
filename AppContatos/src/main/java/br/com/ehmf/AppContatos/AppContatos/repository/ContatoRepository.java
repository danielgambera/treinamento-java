package br.com.ehmf.AppContatos.AppContatos.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ehmf.AppContatos.AppContatos.model.Contato;
import br.com.ehmf.AppContatos.AppContatos.model.Pessoa;


@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	List<Contato> findByPessoa(Pessoa pessoa);
}
