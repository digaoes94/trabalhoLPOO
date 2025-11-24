package entities.interfaces;

public interface DocFisico {
    
	// OBRIGA A CLASSE (LIVRO, REVISTA) A TER UM JEITO DE PEGAR/GRAVAR A LOCALIZAÇÃO.
    String getLocalizacao();
    void setLocalizacao(String localizacao);
    
    String localizar(); 
}