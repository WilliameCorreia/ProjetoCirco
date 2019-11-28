package br.com.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Dao.TelefoneDao;
import br.com.entidades.Telefone;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class TelefoneBean {
	
	private Telefone telefone = new Telefone();
	private Telefone Selectelefone = new Telefone();
	private List<Telefone> list_telefone = new ArrayList<Telefone>();
	private TelefoneDao<Telefone> telefoneDao = new TelefoneDao<Telefone>();
	
	public void NovoTelefone() {
		this.telefone = new Telefone();
	}
	
	@PostConstruct
	public void CarregarTelefone(){
		this.list_telefone = telefoneDao.getListEntity(Telefone.class);
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Telefone getSelectelefone() {
		return Selectelefone;
	}

	public void setSelectelefone(Telefone selectelefone) {
		Selectelefone = selectelefone;
	}

	public List<Telefone> getList_telefone() {
		return list_telefone;
	}

	public void setList_telefone(List<Telefone> list_telefone) {
		this.list_telefone = list_telefone;
	}

	public TelefoneDao<Telefone> getTelefoneDao() {
		return telefoneDao;
	}

	public void setTelefoneDao(TelefoneDao<Telefone> telefoneDao) {
		this.telefoneDao = telefoneDao;
	}

	public String Salvar() {
		this.list_telefone.add(telefone);
		this.telefoneDao.salvar(telefone);
		NovoTelefone();
		CarregarTelefone();
		return null;
	}
	
	public String Remover() {
		this.telefoneDao.delete(Selectelefone);
		CarregarTelefone();
		return null;
	}
	
	public String Update() {
		this.telefoneDao.update(Selectelefone);
		CarregarTelefone();
		return null;
	}

}
