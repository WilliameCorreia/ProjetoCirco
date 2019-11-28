package br.com.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.Dao.ArtistaDao;
import br.com.entidades.Artistas;
import br.com.entidades.Telefone;
import net.bytebuddy.build.Plugin.Engine.Source.Empty;

@ManagedBean(name = "artistaBean")
@ViewScoped
public class ArtistaBean {
	
	private Artistas artista = new Artistas();
	private Telefone telefone = new Telefone();

	private Artistas SelectdArtista = new Artistas();
	private Telefone Selectelefone = new Telefone();

	private List<Artistas> List_artistas = new ArrayList<Artistas>();
	private List<Telefone> list_telefone = new ArrayList<Telefone>();
	
	public List<Telefone> getList_telefone() {
		return list_telefone;
	}

	public void setList_telefone(List<Telefone> list_telefone) {
		this.list_telefone = list_telefone;
	}

	private ArtistaDao<Artistas> artistaDao = new ArtistaDao<Artistas>();
	
	public void NovoArtista() {
		this.artista = new Artistas();
		this.telefone = new Telefone();
	}
	
	@PostConstruct
	public void CarregarArtistas(){
		this.List_artistas = artistaDao.getListEntity(Artistas.class);
	}
	
	public String Salvar() {
		this.list_telefone.add(telefone);
		this.artista.setTelefone(this.list_telefone);
		this.telefone.setArtista(artista);
		this.artistaDao.salvar(artista);
		NovoArtista();
		CarregarArtistas();
		return null;
	}
	
	public String Remover() {
		this.artistaDao.delete(SelectdArtista);
		CarregarArtistas();
		return null;
	}
	
	public String Update() {
		this.artistaDao.update(SelectdArtista);
		CarregarArtistas();
		return null;
	}
	
	public Artistas getSelectdArtista() {
		return SelectdArtista;
	}

	public void setSelectdArtista(Artistas selectdArtista) {
		SelectdArtista = selectdArtista;
	}
		
	public List<Artistas> getList_artistas() {
		return List_artistas;
	}

	public void setList_artistas(List<Artistas> list_artistas) {
		List_artistas = list_artistas;
	}

	public ArtistaDao<Artistas> getArtistaDao() {
		return artistaDao;
	}

	public void setArtistaDao(ArtistaDao<Artistas> artistaDao) {
		this.artistaDao = artistaDao;
	}

	public Artistas getArtista() {
		return artista;
	}
	
	public void setArtista(Artistas artista) {
		this.artista = artista;
	}

	public void setArtistas(List<Artistas> artistas) {
		this.List_artistas = artistas;
	}

	public List<Artistas> getArtistas() {
		return List_artistas;
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
}
