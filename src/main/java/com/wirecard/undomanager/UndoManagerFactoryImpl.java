package com.wirecard.undomanager;

public class UndoManagerFactoryImpl implements UndoManagerFactory {

    @Override
    public UndoManager createUndoManager(Document doc, int bufferSize) {
        return new UndoManagerImpl(doc, new Buffer(bufferSize));
    }
}
