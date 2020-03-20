/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.pm73;

import br.com.caelum.pm73.dao.CriadorDeSessao;
import br.com.caelum.pm73.dao.LeilaoDao;
import br.com.caelum.pm73.dao.UsuarioDao;
import br.com.caelum.pm73.dominio.Leilao;
import br.com.caelum.pm73.dominio.Usuario;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author michael
 */
public class LeilaoDaoTest {
     
    private Session session;
    private UsuarioDao userDao;
    private LeilaoDao leilaoDao;
    
    @Before
    public void antes(){
    
        this.session = new CriadorDeSessao().getSession();
        this.session.getTransaction().begin();
        
        this.userDao = new UsuarioDao(session);
        this.leilaoDao = new LeilaoDao(session);
        
    }
    
    @After
    public void depois(){
    
        this.session.getTransaction().rollback();
        this.session.close();
    }
    
    @Test
    public void deveContarLeiloesNaoEncerrados(){
    
        Usuario user = new Usuario("Mauricio", "mauricio@domain.com");
        userDao.salvar(user);
        
        Leilao leilao1 = new Leilao("Geladeira", 1500.0, user, false);
        leilaoDao.salvar(leilao1);
        
        Leilao leilao2 = new Leilao("Xbox", 700.0, user, false);
        leilao2.encerra();
        leilaoDao.salvar(leilao2);
        
        //----------------------------------------------------------------------    
        long total = leilaoDao.total();
        
        assertEquals(1L, total);
        
        //----------------------------------------------------------------------
        List<Leilao> novos = leilaoDao.novos();
        
        if(!novos.isEmpty()){
            assertEquals(novos.get(0), leilao1);
        }
        //----------------------------------------------------------------------
 
        Calendar dt = Calendar.getInstance();
        dt.add(Calendar.DAY_OF_MONTH, -7);
        
        Leilao leilao3 = new Leilao("Teclado", 150.0, user, false);
        leilao3.setDataAbertura(dt);
        leilaoDao.salvar(leilao3);
        
        assertTrue(leilaoDao.antigos().size() > 0);
    }
    
    
    @Test
    public void deveTrazerLeiloesNaoEncerradosNoPeriodo(){
        
        Calendar dtBegin = Calendar.getInstance();
        dtBegin.add(Calendar.DAY_OF_MONTH, -10);

        Calendar dtEnd = Calendar.getInstance();

        Usuario user = new Usuario("Mauricio", "mauricio@domain.com");
        userDao.salvar(user);
        
        Leilao leilao1 = new Leilao("Xbox", 700.0, user, false);
        leilao1.setDataAbertura(dtBegin);
        leilaoDao.salvar(leilao1);
        
        
        // Fora do intervalo
        Calendar dtOut = Calendar.getInstance();
        dtOut.add(Calendar.DAY_OF_MONTH, -20);
        
        Leilao leilao2 = new Leilao("Geladeira", 1500.0, user, false);
        leilao2.setDataAbertura(dtOut);
        leilaoDao.salvar(leilao2);
        
        List<Leilao> leiloes = leilaoDao.porPeriodo(dtBegin, dtEnd);
        
        
        assertEquals(1, leiloes.size());
    }
    
    @Test
    public void naoDeveTrazerLeiloesNaoEncerradosNoPeriodo(){

        Calendar dtBegin = Calendar.getInstance();
        dtBegin.add(Calendar.DAY_OF_MONTH, -10);

        Calendar dtEnd = Calendar.getInstance();

        Usuario user = new Usuario("Mauricio", "mauricio@domain.com");
        userDao.salvar(user);
        
        
        Calendar dt = Calendar.getInstance();
        dt.add(Calendar.DAY_OF_MONTH, -2);
        
        Leilao leilao1 = new Leilao("Xbox", 700.0, user, false);
        leilao1.setDataAbertura(dt);
        leilao1.encerra();
        leilaoDao.salvar(leilao1);
        
        
        List<Leilao> leiloes = leilaoDao.porPeriodo(dtBegin, dtEnd);
        
        
        assertEquals(0, leiloes.size());

    }
}
