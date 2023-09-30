package com.picpaysimplificado.exception;

public class RecordNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id) {
        super("Registro não encontrado com o id: " + id);
    }
}
