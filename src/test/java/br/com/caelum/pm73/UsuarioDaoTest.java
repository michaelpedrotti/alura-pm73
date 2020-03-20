package br.com.caelum.pm73;

import br.com.caelum.pm73.dao.CriadorDeSessao;
import static org.junit.Assert.assertEquals;
import br.com.caelum.pm73.dao.UsuarioDao;
import br.com.caelum.pm73.dominio.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.mockito.Mockito;

public class UsuarioDaoTest {

    @Test
    public void _deveEncontrarPeloNomeEEmailMockado(){
    
        Session session = Mockito.mock(Session.class);
        Query query = Mockito.mock(Query.class);
        UsuarioDao dao = new UsuarioDao(session);
        
        Usuario mockUser = new Usuario("João da silva", "joao@silvas.com.br");
        
        String sql = "from Usuario u where u.nome = :nome and u.email = :email";
        
        Mockito.when(session.createQuery(sql)).thenReturn(query);
        Mockito.when(query.uniqueResult()).thenReturn(mockUser);
        Mockito.when(query.setParameter("nome", "João da silva")).thenReturn(query);
        Mockito.when(query.setParameter("email", "joao@silvas.com.br")).thenReturn(query);
        
        Usuario dbUser = dao.porNomeEEmail("João da silva", "joao@silvas.com.br");
        
        assertEquals(dbUser.getNome(), mockUser.getNome());
        assertEquals(dbUser.getEmail(), mockUser.getEmail());
    }
    
    @Test
    public void deveEncontrarPeloNomeEEmailMockado(){
        
        Session session = new CriadorDeSessao().getSession();
        UsuarioDao dao = new UsuarioDao(session);
        
        Usuario dbUser = dao.porNomeEEmail("Joao da silva", "joao@silvas.com.br");
        
        assertEquals(dbUser.getNome(), "Joao da silva");
        assertEquals(dbUser.getEmail(), "joao@silvas.com.br");
    }
}
