package entities.interfaces;

public interface DocDigital {
    
    // ATRIBUTO DE ESTADO (SE PODE SER EXECUTADO OU NÃO)
    Boolean getExecutavel();
    void setExecutavel(Boolean executavel);
    
    void executar();
}