package br.com.Dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.JpaUtil.JpaUtil;
import br.com.entidades.Telefone;

public class TelefoneDao<E> {
	
	public void salvar(Telefone telefone) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(telefone);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
			System.out.println(e);
		}
		this.addMessageInfo("Dados Salvos com Sucesso !");
	}
	
	public void update(Telefone telefone) {

		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.merge(telefone);
			entitymanager.getTransaction().commit();
			entitymanager.close();
			
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
		}
		this.addMessageInfo("Dados Atualizados com Sucesso !");
	}
	
	public void delete(Telefone telefone) {
		try {
			EntityManager entitymanager = JpaUtil.getEntityManager();
			entitymanager.getTransaction().begin();
			Telefone x = entitymanager.merge(telefone);
			entitymanager.remove(x);
			entitymanager.getTransaction().commit();
			entitymanager.close();
		} catch (Throwable e) {
			this.addMessageFatal("Erro ao Carregar lista do Banco de dados :", e);
		}
		this.addMessageInfo("Dados Excluidos com Sucesso !");
	}
	
	public List<Telefone> getListEntity(Class<E> entidade){
		
		List<Telefone> retorno = null;
		
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
