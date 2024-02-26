package net.service.manager.home.generic.entity;

public interface GenericEntity<E, D> {
    // met à jour l'instance actuelle avec les données fournies
    void update(E source);

    boolean equalsToDto(D source);

    Long getId();

    void setId(Long id);

    Boolean isDeleted();

    void setIsDeleted(Boolean isDeleted);

    Boolean isVisible();

    void setIsVisibled(Boolean isVisible);

    String getNumEnrg();

    void setNumEnrg(String numEnrg);

    String getEntityName();

    String getModuleName();
}
