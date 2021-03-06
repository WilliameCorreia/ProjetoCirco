package br.com.Dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.JpaUtil.JpaUtil;
import br.com.entidades.Artistas;

public class ArtistaDao<E> {
	
	public void salvar(Artistas artista) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(artista);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			this.addMessageInfo("Dados Salvos com Sucesso !");
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Acessar o Banco de dados :", e);
			System.out.println(e);
		}
		
	}
	
	public void update(Artistas artista) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.merge(artista);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			this.addMessageInfo("Dados Atualizados com Sucesso !");
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Acessar o Banco de dados :", e);
		}
		
	}
	
	public void delete(Artistas artista) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			Artistas x = entitymanager.merge(artista);
			entitymanager.remove(x);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			this.addMessageInfo("Dados Excluidos com Sucesso !");
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Acessar o Banco de dados :", e);
		}
		
	}
	
	public List<Artistas> getListEntity(Class<E> entidade){
		
		List<Artistas> retorno = null;
		
		try {
			EntityManager entityManager = JpaUtil.getEntityManager();
			entityManager.getTransaction().begin();
			
			 retorno = entityManager.createQuery("from " + entidade.getName()).getResultList();
			
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Throwable e) {
			System.out.println(e);
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
		}
		System.out.println(retorno);
		return retorno;	
	}
	
	public void addMessageErro(String summary, Throwable detais) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detais.toString()));
    }
	
	public void addMessageInfo(String summary) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "info"));
    }
	
	public void addMessageFatal(String summary, Throwable detais) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detais.toString()));
    }
	
	public void addMessageWarn(String summary, Throwable detais) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detais.toString()));
    }
	
}
