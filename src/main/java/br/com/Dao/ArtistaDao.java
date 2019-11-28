package br.com.Dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.JpaUtil.JpaUtil;
import br.com.entidades.Artistas;
import br.com.entidades.Telefone;

public class ArtistaDao<E> {
	
	public void salvar(Artistas artista) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(artista);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
			System.out.println(e);
		}
		this.addMessageInfo("Dados Salvos com Sucesso !");
	}
	
	public void update(Artistas artista) {
		Artistas x = null;
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			x = entitymanager.merge(artista);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
		}
		this.addMessageInfo("Dados Atualizados com Sucesso !");
	}
	
	public void delete(Artistas artista) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			Artistas x = entitymanager.merge(artista);
			entitymanager.remove(x);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
		}
		this.addMessageInfo("Dados Excluidos com Sucesso !");
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
