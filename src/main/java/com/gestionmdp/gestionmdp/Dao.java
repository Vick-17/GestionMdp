package com.gestionmdp.gestionmdp;

import java.util.ArrayList;

public abstract class Dao<T> {

    public abstract T find(int id);
    public abstract ArrayList<T> findAll();
}
