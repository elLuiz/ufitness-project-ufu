package com.ufitness.ufitness.service.dto;

import org.modelmapper.ModelMapper;

public abstract class DTOService<D, E> {
    protected ModelMapper modelMapper;

    protected DTOService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public abstract E convertToEntity(D modelDTO);
    public abstract D convertToDTO(E entity);
}
